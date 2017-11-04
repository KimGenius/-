COMMENT_POST = {
    'tags': ['댓글'],
    'description': '댓글 업로드',
    'parameters': [
        {
            'name': 'Authorization',
            'description': 'JWT Token(JWT ***)',
            'in': 'header',
            'type': 'str',
            'required': True
        },
        {
            'name': 'id',
            'description': '댓글을 업로드할 뉴스 ID',
            'in': 'formData',
            'type': 'str',
            'required': True
        },
        {
            'name': 'content',
            'description': '댓글의 내용',
            'in': 'formData',
            'type': 'str',
            'required': True
        }
    ],
    'responses': {
        '201': {
            'description': '댓글 업로드 성공'
        }
    }
}

COMMENT_GET = {
    'tags': ['댓글'],
    'description': '댓글 리스트 조회',
    'parameters': [
        {
            'name': 'Authorization',
            'description': 'JWT Token(JWT ***)',
            'in': 'header',
            'type': 'str',
            'required': True
        },
        {
            'name': 'id',
            'description': '댓글 리스트를 조회할 뉴스 ID',
            'in': 'query',
            'type': 'str',
            'required': True
        }
    ],
    'responses': {
        '200': {
            'description': '댓글 리스트 조회 성공(array의 length가 0일 수 있음)',
            'examples': {
                'application/json': [
                    {
                        "id": "59fe0c9b6751804138329e15",
                        "writer": "조민규",
                        "content": "트럼프는 정말 밥도둑이에염",
                        "like_count": 3,
                        "liked": True
                    },
                    {
                        "id": "59fe0c9b6751804138321415",
                        "writer": "조민규",
                        "content": "앙 기모찌",
                        "like_count": 1,
                        "liked": False
                    }
                ]
            }
        }
    }
}

COMMENT_LIKE_POST = {
    'tags': ['좋아요'],
    'description': '댓글 좋아요',
    'parameters': [
        {
            'name': 'Authorization',
            'description': 'JWT Token(JWT ***)',
            'in': 'header',
            'type': 'str',
            'required': True
        },
        {
            'name': 'id',
            'description': '좋아요를 달 댓글 ID',
            'in': 'formData',
            'type': 'str',
            'required': True
        }
    ],
    'responses': {
        '201': {
            'description': '좋아요 성공'
        },
        '204': {
            'description': '좋아요 실패(이미 좋아요를 누름)'
        }
    }
}

COMMENT_LIKE_DELETE = {
    'tags': ['좋아요'],
    'description': '댓글 좋아요 취소',
    'parameters': [
        {
            'name': 'Authorization',
            'description': 'JWT Token(JWT ***)',
            'in': 'header',
            'type': 'str',
            'required': True
        },
        {
            'name': 'id',
            'description': '좋아요를 취소할 댓글 ID',
            'in': 'formData',
            'type': 'str',
            'required': True
        }
    ],
    'responses': {
        '201': {
            'description': '좋아요 취소 성공'
        },
        '204': {
            'description': '좋아요 취소 실패(좋아요를 누르지 않은 상태였음)'
        }
    }
}
