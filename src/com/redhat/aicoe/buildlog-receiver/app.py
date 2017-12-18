#!/usr/bin/env python

from pymongo import MongoClient
from pprint import pprint

client = MongoClient('mongodb://thoth:thoth@mongodb/thoth')
db = client.admin

serverStatusResult=db.command("serverStatus")
pprint(serverStatusResult)
