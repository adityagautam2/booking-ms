pipeline {
    agent any

    options {
        buildDiscarder(logRotator(numToKeepStr: '5', artifactNumToKeepStr: '5'))
    }
    tools {
        maven 'maven_3.9.16'
    }
    stages {
       stage('Code Compilation') {
          steps {
             echo 'Starting code compilation'
             sh 'mvn clean compile'
             echo 'Code compilation completed successfully'
          }
       }

       stage('Code QA Execution') {
          steps {
             echo 'Running JUNIT Test cases...'
             sh 'mvn clean test'
             echo 'JUnit test cases completed successfully'
          }
       }

       stage('Code Package') {
          steps {
             echo 'Creating Artifact'
             sh 'mvn clean package'
             echo 'Artifact creation completed successfully'
          }
       }


    }
}