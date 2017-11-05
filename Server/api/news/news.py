from datetime import date

from flask import Response
from flask_jwt_extended import get_jwt_identity, jwt_required
from flask_restful_swagger_2 import Resource, request, swagger
from googletrans import Translator

from api.news import news_doc
from db.models.news import NewsModel

_ts = Translator()


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

        language = request.args.get('language')

        if language != 'ko':
            hot_issue = [{
                'id': str(item.id),
                'title': _ts.translate(item.title, language, 'ko').text,
                'description': _ts.translate(item.description, language, 'ko').text,
                'like_count': len(list(item.liked_users)),
                'unlike_count': len(list(item.unliked_users))
            } for item in news]

            today_trump = [{
                'id': str(item.id),
                'title': _ts.translate(item.title, language, 'ko').text,
                'description': _ts.translate(item.description, language, 'ko').text,
                'like_count': len(list(item.liked_users)),
                'unlike_count': len(list(item.unliked_users))
            } for item in news if item.pub_date.date() == date.today()]
        else:
            hot_issue = [{
                'id': str(item.id),
                'title': item.title,
                'description': item.description,
                'like_count': len(list(item.liked_users)),
                'unlike_count': len(list(item.unliked_users))
            } for item in news]

            today_trump = [{
                'id': str(item.id),
                'title': item.title,
                'description': item.description,
                'like_count': len(list(item.liked_users)),
                'unlike_count': len(list(item.unliked_users))
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

        language = request.args.get('language')

        if language != 'ko':
            news = [{
                'id': str(item.id),
                'title': _ts.translate(item.title, language, 'ko').text,
                'description': _ts.translate(item.description, language, 'ko').text,
                'like_count': len(list(item.liked_users)),
                'unlike_count': len(list(item.unliked_users))
            } for item in news]
        else:
            news = [{
                'id': str(item.id),
                'title': item.title,
                'description': item.description,
                'like_count': len(list(item.liked_users)),
                'unlike_count': len(list(item.unliked_users))
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

        language = request.args.get('language')

        if language != 'ko':
            return {
                'title': _ts.translate(news.title, language, 'ko').text,
                'content': _ts.translate(news.content, language, 'ko').text,
                'author': _ts.translate(news.author, language, 'ko').text,
                'link': news.link,
                'pub_date': str(news.pub_date),
                'liked': get_jwt_identity() in list(news.liked_users)
            }, 200
        else:
            return {
                'title': news.title,
                'content': news.content,
                'author': news.author,
                'link': news.link,
                'pub_date': str(news.pub_date),
                'liked': get_jwt_identity() in list(news.liked_users)
            }, 200


class NewsLike(Resource):
    uri = '/news/like'

    @swagger.doc(news_doc.NEWS_LIKE_POST)
    @jwt_required
    def post(self):
        """
        뉴스 좋아요
        """
        news_id = request.form.get('id')

        news = NewsModel.objects(id=news_id)

        liked_users = list(news.liked_users)
        if get_jwt_identity() in liked_users:
            return Response('', 204)

        liked_users.append(get_jwt_identity())
        news.update(liked_users=liked_users)

        return Response('', 201)

    @swagger.doc(news_doc.NEWS_LIKE_DELETE)
    @jwt_required
    def delete(self):
        """
        뉴스 좋아요 취소
        """
        news_id = request.form.get('id')

        news = NewsModel.objects(id=news_id)

        liked_users = list(news.liked_users)
        if get_jwt_identity() not in liked_users:
            return Response('', 204)

        liked_users.remove(get_jwt_identity())
        news.update(liked_users=liked_users)

        return Response('', 200)
