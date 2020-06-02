#!/usr/bin/python
#coding=utf-8
#author：laijiaming
#desc:获取pom文件参数

import  xml.dom.minidom
import os
import sys
import importlib
importlib.reload(sys)



#job_name = os.getenv("job_name")
job_name = sys.argv[1]
dom = xml.dom.minidom.parse("pom.xml")
root = dom.documentElement

with open("/home/docker-sh/read_pom/" + job_name + ".txt", "w") as file:
     info_list = dom.getElementsByTagName('groupId')
     file.write(u'libary_name=%s'"\n"% info_list[0].firstChild.data.lower()) 
     images_list = dom.getElementsByTagName('artifactId')
     file.write(u'images_name=%s'"\n"% images_list[0].firstChild.data.lower()) 
     tag_list = dom.getElementsByTagName('version')
     file.write(u'tag_name=%s'"\n"% tag_list[0].firstChild.data.lower())
     file.write(u'EVN_WEBAPP=%s-%s'% (images_list[0].firstChild.data,tag_list[0].firstChild.data))

