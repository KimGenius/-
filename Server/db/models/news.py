from bson.objectid import ObjectId

from db.mongo import *


class Comment(EmbeddedDocument):
    id = ObjectIdField(primary_key=True, default=ObjectId())
    writer = StringField(required=True)
    content = StringField(required=True)
    like_count = IntField(required=True, default=0)


class NewsModel(Document):
    title = StringField(required=True)
    description = StringField(required=True)
    link = StringField(required=True)
    pub_date = StringField(required=True)
    like_count = IntField(required=True, default=0)
    unlike_count = IntField(required=True, default=0)

    comments = ListField(EmbeddedDocumentField(Comment), default=[])
