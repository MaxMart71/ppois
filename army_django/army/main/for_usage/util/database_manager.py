import psycopg2

from main.for_usage.util.config import *


class DbManager:
    
   @staticmethod
   def connection():
        conn = psycopg2.connect(
           user=user,
           password=password,
           host=host,
           database=db_name,
           port=port
        )
        return conn



