from datetime import date

from db.mongo import *


class AccountModel(Document):
    id = StringField(primary_key=True)
    pw = StringField()
    email = StringField(required=True, unique=True)
    name = StringField(required=True, default='익명 사용자')

    signup_date = StringField(required=True, default=str(date.today()))
    contribution_score = IntField(required=True, default=0)
    comment_count = IntField(required=True, default=0)
    received_like_count = IntField(required=True, default=0)
