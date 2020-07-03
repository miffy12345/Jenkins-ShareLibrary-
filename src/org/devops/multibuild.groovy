package org.devops

//构建打包（包含多种打包类型）
def multibuild(system_type){
        
    if("${system_type}" == "Maven"){
           sh '''
               mvn package -Dmaven.test.skip=true
            '''
    }
    else{
            println("当前选择的构建类型为其他")
    }
}
