pipeline {
    agent any

    node(){
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
                }
            }
        }
    }
}