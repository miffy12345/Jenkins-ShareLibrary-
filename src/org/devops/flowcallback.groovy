package org.devops

//回调节点日志
def flowcallback(project_name,build_id,step_name,flow_name){
        sh """
           printf '{
             "project_name":"project-EK2kzr8N08jW/job/maven-test9",
               "build_id":"41",
               "step_name":"build",
               "flow_name":"测试流水线"
           }'| http  --follow --timeout 3600 POST 2.2.2.104:32215/gitlab/api/integration/flowlog/flowcallback \
           Content-Type:'application/json'
        """
}

