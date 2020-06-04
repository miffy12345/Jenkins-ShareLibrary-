package org.devops

#拉取git代码
def checkgit(giturl,gitbranch){
         git(url: '${giturl}', credentialsId: 'gitlab-id', branch: '${gitbranch}', changelog: true, poll: false)
}

#拉取svn代码
def checksvn(svnurl){
         checkout(scm: [$class: 'SubversionSCM', locations: [[cancelProcessOnExternalsFail: true,  credentialsId: 'svn-id', depthOption: 'infinity', ignoreExternalsOption: true, local: '.', remote: '${svnurl}']], quietOperation: true, workspaceUpdater: [$class: 'UpdateUpdater']], poll: false)
}