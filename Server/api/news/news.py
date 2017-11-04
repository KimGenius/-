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


class NewsList(Resource):
    uri = '/news/list'

    @swagger.doc(news_doc.NEWS_LIST_GET)
    def get(self):
        """
        뉴스 리스트
        """
        page = request.args.get('page', type=int)

        news = NewsModel.objects

        if not news:
            return Response('', 204)

        news = [{
            'id': str(item.id),
            'title': item.title,
            'description': item.description,
            'like_count': item.like_count,
            'unlike_count': item.unlike_count
        } for item in news]

        return sorted(news, key=lambda k: k['like_count'], reverse=True)[(page - 1) * 8: page * 8], 200


class News(Resource):
    uri = '/news'

    @swagger.doc(news_doc.NEWS_GET)
    def get(self):
        """
        뉴스 세부 정보 조회
        """
        news_id = request.args.get('id')

        news = NewsModel.objects(id=news_id).first()

        return {
            'title': news.title,
            'content': news.content,
            'link': news.link,
            'pub_date': str(news.pub_date)
        }, 200


class NewsLike(Resource):
    uri = '/news/like'


class Comment(Resource):
    uri = '/news/comment'

    @swagger.doc(news_doc.COMMENT_POST)
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

    @swagger.doc(news_doc.COMMENT_GET)
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
        } for comment in CommentModel.objects(news=NewsModel.objects(id=news_id).first())], 200


class CommentLike(Resource):
    uri = '/news/comment/like'

    @swagger.doc(news_doc.COMMENT_LIKE_POST)
    @jwt_required
    def post(self):
        """
        댓글 좋아요
        """
        comment_id = request.form.get('id')

        comment = CommentModel.objects(id=comment_id).first()

        liked_users = list(comment.liked_users)
        if get_jwt_identity() in liked_users:
            return Response('', 204)

        liked_users.append(get_jwt_identity())
        comment.update(liked_users=liked_users)

        comment_writer = comment.writer
        comment_writer.update(received_like_count=comment_writer.received_like_count + 1)

        return Response('', 201)

    @swagger.doc(news_doc.COMMENT_LIKE_DELETE)
    @jwt_required
    def delete(self):
        """
        댓글 좋아요 취소
        """
        comment_id = request.form.get('id')

        comment = CommentModel.objects(id=comment_id).first()

        liked_users = list(comment.liked_users)
        if get_jwt_identity() not in liked_users:
            return Response('', 204)

        liked_users.remove(get_jwt_identity())
        comment.update(liked_users=liked_users)

        comment_writer = comment.writer
        comment_writer.update(received_like_count=comment_writer.received_like_count - 1)

        return Response('', 200)
