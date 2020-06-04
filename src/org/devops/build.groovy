package org.devops

//构建
def compile(){
        sh """
           mvn clean compile
        """
}

#构建打包
def package(){
        sh """
           mvn clean package -Dmaven.test.skip=true
        """
}

#手动打包
def Artifacts(){
        sh '''cd ${WORKSPACE}/src/main/webapp
          jar -cvf ${project_name}.war ./*'''
        archiveArtifacts 'src/main/webapp/*.war'
}