pipeline {
    agent any
    environment {
        PROD_GIT = "git+ssh://git@push-par-clevercloud-customers.services.clever-cloud.com/app_777f93d3-b71d-4a54-abe7-ee008e60eb7b"
        GIT_CREDENTIAL_ID = 'c5ff76fbde2d5ff915673d52a2edae473536a0c8'
    }
    stages {
        stage('build') {
            steps {
                sh './mvnw clean package'
            }
        }
         stage('deploy') {
             when {
                branch 'master'
            }
            steps {
               sshagent(["${GIT_CREDENTIAL_ID}"]) {
                  sh "git checkout ${GIT_BRANCH}"
                  sh "git pull"
                  sh "git push --force ${PROD_GIT} ${GIT_BRANCH}:master"
                  slackSend channel: '#jenkins_nantes', color: 'good', message: "Déploiement en cours chez Clever Cloud ! ${env.JOB_NAME} commit ${env.GIT_COMMIT}"
               }
            }
        }
    }
    post {
        success {
           slackSend channel: '#jenkins_nantes', color: 'good', message: "Succès ! ${env.JOB_NAME} commit ${env.GIT_COMMIT} (<${env.BUILD_URL}|Open>)"
        }
        failure {
           slackSend channel: '#jenkins_nantes', color: 'danger', message: "Oops ! ${env.JOB_NAME} commit ${env.GIT_COMMIT} (<${env.BUILD_URL}|Open>)"
        }
    }
}