from datetime import date, datetime

from db.mongo import *


class NotificationModel(EmbeddedDocument):
    target_news = StringField(required=True)
    content = StringField(required=True)
    notification_time = DateTimeField(required=True, default=datetime.now())


class AccountModel(Document):
    id = StringField(primary_key=True)
    pw = StringField()
    email = StringField(unique=True)
    name = StringField(required=True, default='익명 사용자')

    notification_queue = ListField(EmbeddedDocumentField(NotificationModel), default=[])
    signup_date = DateTimeField(required=True, default=date.today())
    contribution_score = IntField(required=True, default=0)
    comment_count = IntField(required=True, default=0)
    received_like_count = IntField(required=True, default=0)
