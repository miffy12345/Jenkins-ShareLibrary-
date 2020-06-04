package org.devops

#拉取git代码
def checkgit(giturl,gitbranch){
        checkout([$class: 'GitSCM', branches: [[name: "${gitbranch}"]], 
                         doGenerateSubmoduleConfigurations: false, 
                         extensions: [], 
                         submoduleCfg: [], 
                         userRemoteConfigs: [[credentialsId: 'gitlab-id', url: "${giturl}"]]])
}

#拉取svn代码
def checksvn(svnurl){
         checkout(scm: [$class: 'SubversionSCM', locations: [[cancelProcessOnExternalsFail: true,  credentialsId: 'svn-id', depthOption: 'infinity', ignoreExternalsOption: true, local: '.', remote: '${svnurl}']], quietOperation: true, workspaceUpdater: [$class: 'UpdateUpdater']], poll: false)
}