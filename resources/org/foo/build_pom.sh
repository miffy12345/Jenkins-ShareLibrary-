#!/bin/sh
#date:2019-12-12
#author:laijiaming
source /etc/profile

#初始化
  libary_name=$1
  image_name=$2
  tag_name=$3
  ENV_WEBAPP=$4 
  git_branch=$5
  
if [ "${git_branch}" != "master" ]
   then
       echo "----- 构建镜像-------"
       #构建镜像
       docker build --build-arg ENV_WEBAPP=${ENV_WEBAPP} -t ${image_name}:${git_branch} . 
       
       echo "----- 检测harbor上是否存在项目-------"
       response=$(curl -X GET --header 'Accept: application/json' 'http://harbor.powerdata.com.cn:30280/api/search?q='${libary_name} | jq '.project[0].project_id')
       if [ "${response}" == "null" ]
          then
              echo "----- 创建${libary_name}项目-------"
              curl -u "jenkins_harbor:2020Jenkins" -X POST -H "Content-Type:application/json" "http://harbor.powerdata.com.cn:30280/api/projects"  --insecure   -d '{"project_name": "'${libary_name}'","public": 1}'
          else
              echo "----- harbor已存在${libary_name}项目-------"
       fi
       echo "----- 上传镜像到harbor-------"
       #上传镜像到harbor
       docker tag ${image_name}:${git_branch} harbor.powerdata.com.cn:30280/${libary_name}/${image_name}:${git_branch}
       docker push harbor.powerdata.com.cn:30280/${libary_name}/${image_name}:${git_branch}

       echo "------删除本地镜像-------"
      #删除镜像
      docker rmi -f harbor.powerdata.com.cn:30280/${libary_name}/${image_name}:${git_branch}
      docker rmi -f ${image_name}:${git_branch}
   else
      echo "----- 构建镜像-------"
       #构建镜像
       docker build --build-arg ENV_WEBAPP=${ENV_WEBAPP} -t ${image_name}:${git_branch} . 
       echo "----- 检测harbor上是否存在项目-------"
       response=$(curl -X GET --header 'Accept: application/json' 'http://harbor.powerdata.com.cn:30280/api/search?q='${libary_name} | jq '.project[0].project_id')
       if [ "${response}" == "null" ]
          then
              echo "----- 创建${libary_name}项目-------"
              curl -u "jenkins_harbor:2020Jenkins" -X POST -H "Content-Type:application/json" "http://harbor.powerdata.com.cn:30280/api/projects"  --insecure   -d '{"project_name": "'${libary_name}'","public": 1}'
          else
              echo "----- harbor已存在${libary_name}项目-------"
       fi
       echo "----- 上传镜像到harbor-------"
       #上传镜像到harbor
       docker tag ${image_name}:${tag_name} harbor.powerdata.com.cn:30280/${libary_name}/${image_name}:${tag_name}
       docker push harbor.powerdata.com.cn:30280/${libary_name}/${image_name}:${tag_name}

       echo "------删除本地镜像-------"
      #删除镜像
      docker rmi -f harbor.powerdata.com.cn:30280/${libary_name}/${image_name}:${tag_name}
      docker rmi -f ${image_name}:${tag_name}  
fi
