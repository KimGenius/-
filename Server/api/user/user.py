from datetime import date

from flask_jwt_extended import get_jwt_identity, jwt_required
from flask_restful_swagger_2 import Resource, swagger

from api.user import user_doc
from db.models.user import AccountModel


class ActivityLog(Resource):
    uri = '/activity-log'

    @swagger.doc(user_doc.ACTIVITY_LOG_GET)
    @jwt_required
    def get(self):
        """
        활동 기록
        """
        user = AccountModel.objects(id=get_jwt_identity()).first()

        after_signup = date.today() - user.signup_date.date()

        return {
            'after_signup': after_signup,
            'contribution_score': user.comment_count * 5 + user.received_like_count * 2,
            'comment_count': user.comment_count,
            'received_like_count': user.received_like_count,
            'comment_avg': user.comment_count / after_signup,
            'received_like_avg': user.received_like_count / after_signup
        }, 200
