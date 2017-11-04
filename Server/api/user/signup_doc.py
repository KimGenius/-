EMAIL_CHECK_POST = {
    'tags': ['중복체크'],
    'description': '이메일 중복체크',
    'parameters': [
        {
            'name': 'email',
            'description': '사용자 이메일',
            'in': 'formData',
            'type': 'str',
            'required': True
        }
    ],
    'responses': {
        '201': {
            'description': '중복되지 않음'
        },
        '204': {
            'description': '중복됨'
        }
    }
}

ID_CHECK_POST = {
    'tags': ['중복체크'],
    'description': 'ID 중복체크',
    'parameters': [
        {
            'name': 'id',
            'description': '사용자 ID',
            'in': 'formData',
            'type': 'str',
            'required': True
        }
    ],
    'responses': {
        '201': {
            'description': '중복되지 않음'
        },
        '204': {
            'description': '중복됨'
        }
    }
}

SIGNUP_POST = {
    'tags': ['회원가입'],
    'description': '회원가입',
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
            'description': '회원가입 성공'
        },
        '204': {
            'description': 'ID 중복으로 인한 회원가입 실패'
        }
    }
}