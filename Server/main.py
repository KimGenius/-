from flask import Flask
from flask_jwt_extended import JWTManager
from flask_cors import CORS

import logger as _logger


_jwt = JWTManager()
_cors = CORS()


def _factory():
    """
    Creates Flask instance & initialize

    Flask best practice : Application Factories
    - Use 'application factory', 'current_app'

    :rtype: Flask
    """
    app = Flask(__name__)
    app.config.from_pyfile('config.py')

    _jwt.init_app(app)
    _cors.init_app(app)
    _logger.decorate(app)

    from blueprints import all_blueprints
    for bp in all_blueprints:
        app.register_blueprint(bp)

    return app


_app = _factory()


if __name__ == '__main__':
    _app.run(host='localhost', port=_app.config['PORT'], threaded=True, debug=True)
