pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                echo 'Building..'
                sh 'mvn clean package -Dmaven.test.skip=true -Dcheckstyle.skip=true'
            }
        }
        stage('Test') {
            steps {
                echo 'Testing..'
                sh 'mvn test'
            }
        }
        stage('docker build config server') {
            steps {
                sh 'docker build -f configserver/Dockerfile -t configserver .'
            }
        }
    }
}