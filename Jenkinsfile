pipeline {
    agent any

    options {
        buildDiscarder(
            logRotator(
                numToKeepStr: '5',
                artifactNumToKeepStr: '5'
            )
        )
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

        stage('Build & Tag Docker Image') {
            steps {
                echo 'Building Docker Image with Tags...'

                sh '''
                    docker build \
                        -t adgaut21/booking-ms:latest \
                        -t booking-ms:latest \
                        .
                '''

                echo 'Docker Image Build Completed!'
            }
        }

        stage('Docker Image Scanning') {
            steps {
                echo 'Scanning Docker Image with Trivy...'

                sh '''
                    trivy image booking-ms:latest || \
                    echo "Scan Failed - Proceeding with Caution"
                '''

                echo 'Docker Image Scanning Completed!'
            }
        }

        stage('Push Docker Image to Docker Hub') {
            steps {
                script {
                    withCredentials([
                        string(
                            credentialsId: 'dockerhubCred',
                            variable: 'dockerhubCred'
                        )
                    ]) {
                        sh '''
                            echo "$dockerhubCred" | \
                            docker login \
                                --username adgaut21 \
                                --password-stdin
                        '''

                        echo 'Pushing Docker Image to Docker Hub...'

                        sh '''
                            docker push adgaut21/booking-ms:latest
                        '''

                        echo 'Docker Image Pushed to Docker Hub Successfully!'
                    }
                }
            }
        }

stage('Push Docker Image to Amazon ECR') {
    steps {
        script {
            echo 'Tagging and Pushing Docker Image to ECR...'

            withCredentials([
                [$class: 'AmazonWebServicesCredentialsBinding',
                 credentialsId: 'pushecr']
            ]) {
                sh '''
                    echo "Logging into Amazon ECR..."

                    aws ecr get-login-password --region ap-south-1 | \
                    docker login --username AWS --password-stdin \
                    846797579443.dkr.ecr.us-east-1.amazonaws.com

                    echo "Tagging Docker Image..."

                    docker tag adgaut21/booking-ms:latest \
                    846797579443.dkr.ecr.us-east-1.amazonaws.com/adgaut21/booking-ms:latest

                    echo "Pushing Docker Image to ECR..."

                    docker push \
                    846797579443.dkr.ecr.us-east-1.amazonaws.com/adgaut21/booking-ms:latest
                '''
            }

            echo 'Docker Image Pushed to Amazon ECR Successfully!'
        }
    }
}

        stage('Cleanup Docker Images') {
            steps {
                echo 'Cleaning up local Docker images...'

                sh '''
                    docker rmi -f $(docker images -aq) || true
                '''

                echo 'Local Docker images deleted successfully!'
            }
        }
    }
}

