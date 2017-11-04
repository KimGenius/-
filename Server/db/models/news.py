from bson.objectid import ObjectId

from db.mongo import *
from db.models.user import AccountModel


class Comment(EmbeddedDocument):
    id = ObjectIdField(primary_key=True, default=ObjectId())
    writer = ReferenceField(AccountModel, required=True)
    content = StringField(required=True)
    liked_users = ListField(required=True, default=[])


class NewsModel(Document):
    title = StringField(required=True)
    description = StringField(required=True)
    link = StringField(required=True)
    pub_date = DateTimeField(required=True)
    like_count = IntField(required=True, default=0)
    unlike_count = IntField(required=True, default=0)

    comments = ListField(EmbeddedDocumentField(Comment), default=[])
