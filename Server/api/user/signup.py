from flask import Response
from flask_restful_swagger_2 import Resource, request, swagger

from api.user import signup_doc
from db.models.account import AccountModel


class EmailCheck(Resource):
    uri = '/check/email'

    @swagger.doc(signup_doc.EMAIL_CHECK_POST)
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

    @swagger.doc(signup_doc.ID_CHECK_POST)
    def post(self):
        id = request.form.get('id')

        if AccountModel.objects(id=id):
            # Exist
            return Response('', 204)
        else:
            # Not Exist
            return Response('', 201)


class Signup(Resource):
    uri = '/signup'

    @swagger.doc(signup_doc.SIGNUP_POST)
    def post(self):
        id = request.form.get('id')
        pw = request.form.get('pw')
        email = request.form.get('email')
        name = request.form.get('name')

        if AccountModel.objects(id=id):
            return Response('', 204)

        AccountModel(id=id, pw=pw, email=email, name=name).save()

        return Response('', 201)
