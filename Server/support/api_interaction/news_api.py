import json
import re

from bs4 import BeautifulSoup as Soup
from requests import get

_HEADERS = {
    'X-Naver-Client-Id': 'ixbtTAgetTMK_pNRfvh9',
    'X-Naver-Client-Secret': 'PYVMBVVwta'
}
# Don't Hard Coding

_API_BASE = 'https://openapi.naver.com/v1/search/news.json?query=트럼프&display=100'


def parse():
    for start in range(100, 1001, 100):
        if start == 0:
            resp = json.loads(get(_API_BASE, headers=_HEADERS).text)
        else:
            resp = json.loads(get(_API_BASE, params={'start': start, 'display': 100}, headers=_HEADERS).text)

        for item in resp['items']:
            soup = Soup(get(item['link']).text, 'html.parser')
            article = soup.find(id='articleBodyContents')

            if article is None:
                continue

            content = str(article).split('</script>\n')[1].split('<!-- //')[0].replace('<br/>', '\n').replace('&lt;', '<').replace('&gt;', '>')
            content = re.sub('<a.+</a>', '', content)

            if '이재명 기자' in content or '조성봉 기자' in content or '이지은 기자' in content:
                continue

            title = item['title'].replace('<b>', '').replace('</b>', '').replace('&quot;', "'")
            description = item['description'].replace('<b>', '').replace('</b>', '').replace('&quot;', "'")
            link = item['link']
            pub_date = item['pubDate']
