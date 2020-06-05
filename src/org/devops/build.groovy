package org.devops

//构建
def compile(){
        sh """
           mvn clean compile
        """
}

//构建打包
def buildpackage(){
        sh """
           mvn package -Dmaven.test.skip=true
        """
}
//构建镜像
def docker_images(libary_name,images_name,tag_name,giturl_branch){
        sh """
           cp  ${WORKSPACE}/src/main/docker/Dockerfile ${WORKSPACE}/target/
           cp ${WORKSPACE}/script/docker/build_pom.sh ${WORKSPACE}/target/
           cd ${WORKSPACE}/target/
           sh build_pom.sh ${libary_name} ${images_name} ${tag_name} ${images_name}_${tag_name} ${giturl_branch}
        """
}
//手动打包
def buildArtifacts(){
        sh '''cd ${WORKSPACE}/src/main/webapp
          jar -cvf ${project_name}.war ./*'''
        archiveArtifacts 'src/main/webapp/*.war'
}
