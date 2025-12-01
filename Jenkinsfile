pipeline {
    agent any
    tools {
            maven 'maven-example'  // имя, заданное в Jenkins
        }
    stages {
        stage('Example................................................................') {
            steps {
                echo "Running ${env.BUILD_ID} on ${env.JENKINS_URL}"
            }
        }
        stage('Build') {
            steps {
                echo 'Building................................................................'
                sh 'mvn clean package -DskipTests'
                archiveArtifacts artifacts: '**/target/*.jar', fingerprint: true
            }
        }
        stage('Test') {
            steps {
                echo 'Testing................................................................'
//                 sh 'mvn test'  // вместо make check
//                 junit '**/target/surefire-reports/*.xml'
            }
        }
        stage('Deploy') {
            when {
              expression {
                currentBuild.result == null || currentBuild.result == 'SUCCESS'
              }
            }
            steps {
                echo 'Deploying................................................................'
                sh 'mvn install -DskipTests'
            }
        }
    }
}
