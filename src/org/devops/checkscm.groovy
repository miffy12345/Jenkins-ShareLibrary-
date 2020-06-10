package org.devops

//拉取git代码
def checkgit(giturl,gitbranch){
        git(url: '${giturl}', credentialsId: 'gitlab-id', branch: '${git}', changelog: true, poll: false)
}
//拉取代码和脚本（开发环境）
def dev_git(giturl){
        git(url: '${giturl}', credentialsId: 'gitlab-id', branch: 'dev', changelog: true, poll: false)
        dir('script') {
          git(url: 'http://gitlab.powerdata.com.cn/jenkins/Script_Library.git', credentialsId: 'gitlab-id', branch: 'master', changelog: true, poll: false)
        }
}

//拉取svn代码
def checksvn(svnurl){
         checkout(scm: [$class: 'SubversionSCM', locations: [[cancelProcessOnExternalsFail: true,  credentialsId: 'svn-id', depthOption: 'infinity', ignoreExternalsOption: true, local: '.', remote: '${svnurl}']], quietOperation: true, workspaceUpdater: [$class: 'UpdateUpdater']], poll: false)
}
