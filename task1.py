import psycopg2
import numpy as np
import psycopg2
from psycopg2 import sql
from psycopg2.sql import SQL, Identifier

conn = psycopg2.connect(dbname='postgres', user='postgres',
                        password='admin', host='localhost', port=5432)
cursor = conn.cursor()
conn.autocommit = True




def get_roots(cursor):
    cursor.execute("SELECT source FROM graph UNION SELECT destination FROM graph;")
    rows = cursor.fetchall()
    roots = []
    for row in rows:
        roots.append(row[0])
    return roots



def make_graphs(cursor, roots):
    graphs = np.zeros((len(roots), len(roots)))
    for root in roots:

        cursor.execute("SELECT source FROM graph WHERE destination = '%s';" % root)
        rows = cursor.fetchall()
        for row in rows:
            graphs[roots.index(row[0]), roots.index(root)]= 1
    return graphs

def hits(h,graphs1):
    graphs_trans = graphs1.transpose()
    h = np.dot(graphs_trans, h)
    b = [1/max(h)]
    h = b*h
    h = np.dot(graphs1, h)
    b = [1 / max(h)]
    h = b * h
    return h

graphs1 = make_graphs(cursor,get_roots(cursor))

h = np.ones((len(graphs1[0]), 1))

for i in range(10):
    h = hits(h, graphs1)
print(h)