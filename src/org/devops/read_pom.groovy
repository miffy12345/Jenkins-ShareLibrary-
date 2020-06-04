package org.devops

def SonarScan(projectName){
    
    withCredentials([string(credentialsId : 'sonar-id' ,variable : 'sonar' ,)]){
    withSonarQubeEnv("sonar"){
        
        sh """ 
           mvn sonar:sonar -Dsonar.host.url=http://192.168.3.158:30490 -Dsonar.login=$sonar -Dsonar.projectName=${project_name} -Dsonar.projectVersion=1.0 -Dsonar.sources=. -Dsonar.sourceEncoding=UTF-8 -Dsonar.java.binaries=.
        """
    }

}
}
