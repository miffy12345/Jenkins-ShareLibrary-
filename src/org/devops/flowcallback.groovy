package org.devops

//回调节点日志
def flowcallback(project_name,build_id,step_name,flow_name){
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

