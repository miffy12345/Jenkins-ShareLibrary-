package org

def SonarScan(projectName,branchName){
    
    withCredentials([string(credentialsId : 'sonar-id' ,variable : 'sonar' ,)]){
    withSonarQubeEnv("sonar"){
        
        sh """ 
           mvn sonar:sonar -Dsonar.host.url=http://192.168.3.158:30490 -Dsonar.login=$SONAR_TOKEN -Dsonar.projectName=${project_name} -Dsonar.projectVersion=1.0 -Dsonar.sources=. -Dsonar.sourceEncoding=UTF-8 -Dsonar.java.binaries=. -Dsonar.branch.name=${branchName}
        """
    }

}
}
