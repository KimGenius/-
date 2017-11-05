import os
from datetime import timedelta

PORT = 3002

API_VER = '0.1'
API_TITLE = 'TT API'
API_DESC = '''
[BASE URL] http://52.79.134.200:{0}

- Status Code 400 Bad Request : 요청 데이터를 모두 충족하지 않음
- Status Code 401 UNAUTHORIZED : JWT 토큰 만료됨, 또는 토큰이 정상적으로 전달되지 않음
- Status Code 403 Forbidden : 권한 없음
- Status Code 500 Internal Server Error : 서버 내부 오류
'''.format(PORT)

SECRET_KEY = os.getenv('SECRET_KEY')
JWT_ACCESS_TOKEN_EXPIRES = timedelta(days=30)
JWT_HEADER_TYPE = 'JWT'
# http://flask-jwt-extended.readthedocs.io/en/latest/options.html
