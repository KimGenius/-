from flask_jwt_extended import create_access_token
from flask_restful_swagger_2 import Resource, request, swagger

from api.user import auth_doc
from db.models.user import AccountModel


class BasicAuth(Resource):
    uri = '/auth/basic'

    @swagger.doc(auth_doc.BASIC_AUTH_POST)
    def post(self):
        """
        서비스 자체 로그인
        """
        id = request.form.get('id', None)
        pw = request.form.get('pw', None)

        if not id:
            return {
                'msg': 'Missing id parameter'
            }, 400

        if not pw:
            return {
                'msg': 'Missing pw parameter'
            }, 400

        elif id and pw and AccountModel.objects(id=id, pw=pw):
            return {
                'access_token': create_access_token(identity=id)
            }, 201

        else:
            return {
               'msg': 'Incorrect id or password'
            }, 401


class FacebookAuth(Resource):
    uri = '/auth/facebook'

    def post(self):
        """
        페이스북 로그인
        """
        token = request.form.get('token')
        email = request.form.get('email', None)
        name = request.form.get('name')

        if not AccountModel.objects(id=token):
            AccountModel(id=token, email=email, name=name).save()

        return {
            'access_token': create_access_token(identity=token)
        }, 201


class GoogleAuth(Resource):
    uri = '/auth/google'

    def post(self):
        """
        구글 로그인
        """
        token = request.form.get('token')
        email = request.form.get('email')
        name = request.form.get('name')

        if not AccountModel.objects(id=token):
            AccountModel(id=token, email=email, name=name).save()

        return {
            'access_token': create_access_token(identity=token)
        }, 201
