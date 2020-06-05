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

//手动打包
def buildArtifacts(){
        sh '''cd ${WORKSPACE}/src/main/webapp
          jar -cvf ${project_name}.war ./*'''
        archiveArtifacts 'src/main/webapp/*.war'
}
