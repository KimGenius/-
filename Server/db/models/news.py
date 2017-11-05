from db.mongo import *
from db.models.user import AccountModel


class NewsModel(Document):
    title = StringField(required=True)
    description = StringField(required=True)
    content = StringField(required=True)
    author = StringField()
    link = StringField(required=True)
    pub_date = DateTimeField(required=True)
    liked_users = ListField(default=[])
    unliked_users = ListField(default=[])


class CommentModel(Document):
    news = ReferenceField(NewsModel)
    writer = ReferenceField(AccountModel, required=True)
    content = StringField(required=True)
    liked_users = ListField(default=[])
