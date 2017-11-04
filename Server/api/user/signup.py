from flask import Response
from flask_restful_swagger_2 import Resource, request, swagger

from db.models.account import AccountModel


class EmailCheck(Resource):
    uri = '/check/email'

    def post(self):
        email = request.form.get('email')

        if AccountModel.objects(email=email):
            # Exist
            return Response('', 204)
        else:
            # Not Exist
            return Response('', 201)


class IDCheck(Resource):
    uri = '/check/id'


class Signup(Resource):
    uri = '/signup'

