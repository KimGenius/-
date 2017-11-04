from datetime import date

from flask import Response
from flask_jwt_extended import get_jwt_identity, jwt_required
from flask_restful_swagger_2 import Resource, request, swagger

from db.models.news import NewsModel, Comment


class MainPage(Resource):
    uri = '/news/main'

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
