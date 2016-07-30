# -*- coding: utf-8 -*-
'''
Created on 2016. 7. 26.

@author: JongHyuk
'''
def errorCheck(re,dberror):
    
    print "DBERORR\n",re,dberror
    if "Syntax error (missing operator) in query expression" in dberror:
        return "sql Injection","1"
