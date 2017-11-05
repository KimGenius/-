ACTIVITY_LOG_GET = {
    'tags': ['마이페이지'],
    'description': '활동 로그',
    'parameters': [
        {
            'name': 'Authorization',
            'description': 'JWT Token(JWT ***)',
            'in': 'header',
            'type': 'str',
            'required': True
        }
    ],
    'responses': {
        '200': {
            'description': '활동 로그 불러오기 성공',
            'examples': {
                'application/json': {
                    'after_signup': 341,
                    'contribution_score': 6155,
                    'comment_count': 15,
                    'received_like_count': 51,
                    'comment_avg': 0.13,
                    'received_like_avg': 0.3
                }
            }
        }
    }
}

INFO_GET = {
    'tags': ['마이페이지'],
    'description': '내 정보 불러오기',
    'parameters': [
        {
            'name': 'Authorization',
            'description': 'JWT Token(JWT ***)',
            'in': 'header',
            'type': 'str',
            'required': True
        }
    ],
    'responses': {
        '200': {
            'description': '내 정보 불러오기 성공',
            'examples': {
                'application/json': {
                    'email': 'yjyj@naver.com',
                    'name': '김영배'
                }
            }
        }
    }
}

NOTIFICATION_GET = {
    'tags': ['마이페이지'],
    'description': '알림 목록',
    'parameters': [
        {
            'name': 'Authorization',
            'description': 'JWT Token(JWT ***)',
            'in': 'header',
            'type': 'str',
            'required': True
        }
    ],
    'responses': {
        '200': {
            'description': '알림 불러오기 성공(Array length가 0일 수 있음)',
            'examples': {
                'application/json': [
                    {
                        'target_news': '59fe0c9b67518016a82f60b6',
                        'content': '조민규님이 회원님의 댓글을 좋아합니다',
                        'notification_time': '2017-11-04'
                    },
                    {
                        'target_news': '59fe0c9b67518016a821f410',
                        'content': '김영배님이 회원님의 댓글을 좋아합니다',
                        'notification_time': '2017-11-03'
                    }
                ]
            }
        }
    }
}
