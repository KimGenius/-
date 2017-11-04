from datetime import date, datetime

from db.mongo import *


class Notification(EmbeddedDocument):
    content = StringField(required=True)
    notification_time = StringField(str(datetime.now())[:-7])


class AccountModel(Document):
    id = StringField(primary_key=True)
    pw = StringField()
    email = StringField(unique=True)
    name = StringField(required=True, default='익명 사용자')

    notification_queue = ListField(EmbeddedDocumentField(Notification), default=[])
    signup_date = StringField(required=True, default=str(date.today()))
    contribution_score = IntField(required=True, default=0)
    comment_count = IntField(required=True, default=0)
    received_like_count = IntField(required=True, default=0)
