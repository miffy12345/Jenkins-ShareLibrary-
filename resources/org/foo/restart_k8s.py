import requests
import json
import sys
import importlib
import os

token_name = os.getenv("access_token")
libary_name = os.getenv("libary_name")
images_name = os.getenv("images_name")
tag_name = os.getenv("tag_name")
build_environment = os.getenv("giturl_branch")
space_name = os.getenv("space_name")
pack_name =  os.getenv("pack_name")
url =  os.getenv("k8s_address")
print (token_name)
print (libary_name)
print (pack_name)


headers = {
    'Authorization': "Bearer %s" %token_name ,
    #'Host': "2.2.2.104:30881",
    'Host': "" + url + "",
    }
    
url = "http://" + url + "/apis/apps/v1/namespaces/" + space_name + "/statefulsets/" + pack_name + ""
print (url)

response = requests.request("GET", url, headers=headers)
print (response.text)
result = json.loads(response.text)  
del result["metadata"]["selfLink"]
del result["metadata"]["uid"]
del result["metadata"]["resourceVersion"]
del result["metadata"]["generation"]
del result["metadata"]["creationTimestamp"]
del result["status"]
if build_environment != "master":
   result["spec"]["template"]["spec"]["containers"][0]["image"] = "harbor.powerdata.com.cn:30280/" + libary_name + "/" + images_name + ":" + build_environment + ""
else:
   result["spec"]["template"]["spec"]["containers"][0]["image"] = "harbor.powerdata.com.cn:30280/"+ libary_name +"/"+ images_name +":"+ tag_name +""
cpu_name = result["spec"]["template"]["spec"]["containers"][0]["resources"]["requests"]["cpu"]
if cpu_name == "10m" :
   result["spec"]["template"]["spec"]["containers"][0]["resources"]["requests"]["cpu"] = "11m"
else:
   result["spec"]["template"]["spec"]["containers"][0]["resources"]["requests"]["cpu"] = "10m"
c = requests.request("PUT", url, data=json.dumps(result) , headers=headers)
print(response.text)
