pipeline {
  agent any
  triggers {
       pollSCM('H/15 * * * *')
  }
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
    stage('Report') {
      steps {
        junit 'target/surefire-reports/**/*.xml'
      }
    }
  }
}