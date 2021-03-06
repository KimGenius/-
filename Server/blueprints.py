import pkgutil

from flask import Blueprint
from flask_restful_swagger_2 import Api

import config as cf
import api


def _modules(packages):
    modules = set()

    def search(target):
        for loader, name, is_package in pkgutil.iter_modules(target.__path__):
            if is_package:
                search(loader.find_module(name).load_module(name))
            else:
                modules.add((loader, name))

    for pkg in packages:
        search(pkg)

    return modules

_global_resources = set()


def _factory(packages, bp_endpoint, url_prefix='', api_spec_url='/api/swagger', api_ver=cf.API_VER, api_title=cf.API_TITLE, api_desc=cf.API_DESC):
    bp = Blueprint(bp_endpoint, __name__, url_prefix=url_prefix)
    api = Api(bp, api_spec_url=api_spec_url, api_version=api_ver, title=api_title, description=api_desc)

    resources = set()

    for loader, name in _modules(packages):
        module_ = loader.find_module(name).load_module(name)
        try:
            for res in module_.Resource.__subclasses__():
                if res not in _global_resources:
                    resources.add(res)
                    _global_resources.add(res)
        except AttributeError:
            pass

    for res in resources:
        api.add_resource(res, res.uri)

    return bp

_bp_api = _factory([api], 'API')

all_blueprints = (_bp_api,)
