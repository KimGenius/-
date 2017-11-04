BASIC_AUTH_POST = {
    'tags': ['로그인'],
    'description': '서비스 자체 로그인',
    'parameters': [
        {
            'name': 'id',
            'description': '사용자 ID',
            'in': 'formData',
            'type': 'str',
            'required': True
        },
        {
            'name': 'pw',
            'description': '사용자 PW',
            'in': 'formData',
            'type': 'str',
            'required': True
        }
    ],
    'responses': {
        '201': {
            'description': '로그인 성공. Access Token 반환',
            'examples': {
                'application/json': {
                    'access_token': 'zxcvkorkrporkpgkpo1.dovksgfpkp1rokp.qwpeqwlpdflpsd'
                }
            }
        },
        '400': {
            'description': '비정상 요청',
            'examples': {
                'application/json : Missing ID': {
                    'msg': 'Missing id parameter'
                },
                'application/json : Missing PW': {
                    'msg': 'Missing pw parameter'
                }
            }
        },
        '401': {
            'description': '등록되지 않은 ID/PW',
            'examples': {
                'application/json': {
                    'msg': 'Incorrect id or password'
                }
            }
        }
    }
}

FACEBOOK_AUTH_POST = {
    'tags': ['로그인'],
    'description': '페이스북 로그인',
    'parameters': [
        {
            'name': 'token',
            'description': '페이스북 계정의 Token',
            'in': 'formData',
            'type': 'str',
            'required': True
        },
        {
            'name': 'email',
            'description': '사용자 이메일',
            'in': 'formData',
            'type': 'str',
            'required': False
        },
        {
            'name': 'name',
            'description': '사용자 이름',
            'in': 'formData',
            'type': 'str',
            'required': True
        }
    ],
    'responses': {
        '201': {
            'description': '로그인 성공. Access Token 반환',
            'examples': {
                'application/json': {
                    'access_token': 'zxcvkorkrporkpgkpo1.dovksgfpkp1rokp.qwpeqwlpdflpsd'
                }
            }
        }
    }
}

GOOGLE_AUTH_POST = {
    'tags': ['로그인'],
    'description': '구글 로그인',
    'parameters': [
        {
            'name': 'token',
            'description': '페이스북 계정의 Token',
            'in': 'formData',
            'type': 'str',
            'required': True
        },
        {
            'name': 'email',
            'description': '사용자 이메일',
            'in': 'formData',
            'type': 'str',
            'required': True
        },
        {
            'name': 'name',
            'description': '사용자 이름',
            'in': 'formData',
            'type': 'str',
            'required': True
        }
    ],
    'responses': {
        '201': {
            'description': '로그인 성공. Access Token 반환',
            'examples': {
                'application/json': {
                    'access_token': 'zxcvkorkrporkpgkpo1.dovksgfpkp1rokp.qwpeqwlpdflpsd'
                }
            }
        }
    }
}
