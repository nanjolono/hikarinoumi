pipeline {
    agent any

    stages {
        stage('check scm') {
            steps {
                echo 'checkout'
            }
        }
        stage('Build') {
            steps {
                echo 'Building..'
                sh 'mvn clean package'
            }
        }
        stage('Test') {
            steps {
                echo 'Testing..'
                sh 'mvn test'
            }
        }
        stage('Deploy') {
            steps {
                docker.build("blog-test:${env.BUILD_ID}", "./blog/")
            }
        }
    }
}