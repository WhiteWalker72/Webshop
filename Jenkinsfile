pipeline {
  agent any
  stages {
    stage('Initialize') {
      steps {
        sh '''echo PATH = ${PATH}
        echo M2_HOME = ${M2_HOME}
        mvn clean'''
      }
    }
    stage('Build') {
      steps {
        sh 'mvn -B -DskipTests package'
      }
    }
    stage('Test') {
      steps {
        sh 'mvn test'
      }
    }
  }
  triggers {
    pollSCM('H/15 * * * *')
  }
}