package org.devops

//回调节点日志
def flowcallback1(project_name,build_id,step_name,flow_name){
        sh """
           printf '{
             "project_name":"${project_name}",
               "build_id":"${build_id}",
               "step_name":"${step_name}",
               "flow_name":"${flow_name}"
           }'| http  --follow --timeout 3600 POST 2.2.2.104:32215/gitlab/api/integration/flowlog/flowcallback \
           Content-Type:'application/json'
        """
}

def flowcallback(project_name,build_id,step_name,flow_name,product_type,groupId,artifactId,version){
        sh """
          curl --location --request POST 'http://2.2.2.104:32215/gitlab/api/integration/flowlog/flowcallback' \
          --header 'Content-Type: application/json' \
          --data-raw '{
             "project_name":"${project_name}",
             "build_id":"${build_id}",
             "step_name":"${step_name}",
             "flow_name":"${flow_name}",
             "Product_type":"${product_type}",
             "groupId":"${groupId}",
             "artifactId":"${artifactId}",
             "version":"${version}"
          }'
        """
}

def flowcallback2(project_name,build_id,step_name,flow_name){
        sh """
          curl --location --request POST 'http://2.2.2.104:32215/gitlab/api/integration/flowlog/flowcallback' \
          --header 'Content-Type: application/json' \
          --data-raw '{
             "project_name":"${project_name}",
             "build_id":"${build_id}",
             "step_name":"${step_name}",
             "flow_name":"${flow_name}"
          }'
        """
}
