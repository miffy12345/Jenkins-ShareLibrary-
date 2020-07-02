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
//构建打包（包含多种打包类型）
def Build(buildType){
        if ("${buildType}" == "maven"){
            sh """
               mvn package -Dmaven.test.skip=true
            """
        }
        if ("${buildType}" == "npm"){
            sh """
               echo"当前选择的构建类型为npm"
            """
        }
        if ("${buildType}" == "android"){
            sh """
               echo"当前选择的构建类型为android"
            """
        }
}

//构建镜像
def docker_images(libary_name,images_name,tag_name){
        sh '''
           images=$(echo ${images_name} | tr '[A-Z]' '[a-z]')
           tag=$(echo ${tag_name} | tr '[A-Z]' '[a-z]')
           cp  ${WORKSPACE}/src/main/docker/Dockerfile ${WORKSPACE}/target/
           cp ${WORKSPACE}/script/docker/build_pom.sh ${WORKSPACE}/target/
           cd ${WORKSPACE}/target/
           sh build_pom.sh ${libary_name} ${images} ${tag} ${images_name}-${tag_name}
        '''
}
//手动打包
def buildArtifacts(){
        sh '''cd ${WORKSPACE}/src/main/webapp
          jar -cvf ${project_name}.war ./*'''
        archiveArtifacts 'src/main/webapp/*.war'
}
