from datetime import date

from flask import Response
from flask_jwt_extended import get_jwt_identity, jwt_required
from flask_restful_swagger_2 import Resource, request, swagger

from api.news import news_doc
from db.models.user import AccountModel
from db.models.news import NewsModel, CommentModel


class MainPage(Resource):
    uri = '/news/main'

    @swagger.doc(news_doc.MAIN_PAGE_GET)
    def get(self):
        """
        TT 메인페이지
        """
        news = NewsModel.objects

        if not news:
            return Response('', 204)

        hot_issue = [{
            'id': str(item.id),
            'title': item.title,
            'description': item.description,
            'like_count': item.like_count,
            'unlike_count': item.unlike_count
        } for item in news]

        today_trump = [{
            'id': str(item.id),
            'title': item.title,
            'description': item.description,
            'like_count': item.like_count,
            'unlike_count': item.unlike_count
        } for item in news if item.pub_date.date() == date.today()]

        return {
            'hot_issue': sorted(hot_issue, key=lambda k: k['like_count'], reverse=True)[:11],
            'today_trump': sorted(today_trump, key=lambda k: k['like_count'], reverse=True)[:11]
        }, 200


# class NewsList(Resource):
#     uri = '/news/list'
#
#
# class News(Resource):
#     uri = '/news'


class Comment(Resource):
    uri = '/news/comment'

    @jwt_required
    def post(self):
        """
        댓글 업로드
        """
        news_id = request.form.get('id')
        content = request.form.get('content')

        user = AccountModel.objects(id=get_jwt_identity()).first()
        user.update(comment_count=user.comment_count + 1)

        CommentModel(news=NewsModel.objects(id=news_id).first(), writer=AccountModel.objects(id=get_jwt_identity()).first(), content=content).save()

        return Response('', 201)

    @jwt_required
    def get(self):
        """
        댓글 리스트 조회
        """
        news_id = request.args.get('id')

        return [{
            'id': str(comment.id),
            'writer': comment.writer.name,
            'content': comment.content,
            'like_count': len(list(comment.liked_users)),
            'liked': get_jwt_identity() in list(comment.liked_users)
        } for comment in CommentModel.objects(news=NewsModel.objects(id=news_id).first())]


class Like(Resource):
    uri = '/news/comment/like'

    @jwt_required
    def post(self):
        """
        좋아요
        """
        comment_id = request.form.get('id')

        comment = CommentModel.objects(id=comment_id).first()

        liked_users = list(comment.liked_users)
        liked_users.append(get_jwt_identity())
        comment.update(liked_users=liked_users)

        comment_writer = comment.writer
        comment_writer.update(received_like_count=comment_writer.received_like_count + 1)

        return Response('', 201)

    @jwt_required
    def delete(self):
        """
        좋아요 취소
        """
        comment_id = request.form.get('id')

        comment = CommentModel.objects(id=comment_id).first()

        liked_users = list(comment.liked_users)
        liked_users.remove(get_jwt_identity())
        comment.update(liked_users=liked_users)

        comment_writer = comment.writer
        comment_writer.update(received_like_count=comment_writer.received_like_count - 1)

        return Response('', 200)
