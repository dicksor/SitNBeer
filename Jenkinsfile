pipeline {
  agent any
  environment {
    SPRING_DATASOURCE_URL='jdbc:mysql://157.26.83.83:3306/sitnbeer'
    SPRING_DATASOURCE_USERNAME  = credentials('SPRING_DATASOURCE_USERNAME')
    SPRING_DATASOURCE_PASSWORD = credentials('SPRING_DATASOURCE_PASSWORD')
  }
  stages {
    stage('Build') {
      agent {
        docker {
          image 'maven:3.6.3-jdk-11-slim'
        }
      }
      steps {
        echo "Building"
        sh '(cd ./SitNBeer/; mvn clean package)'
        stash name: "app", includes: "**"
      }
    }
    stage('QualityTest') {
      agent {
        docker {
          image 'maven:3.6.3-jdk-11-slim'
        }
      }
      steps {
        echo "Running quality tests"
        sh '(cd ./SitNBeer/; mvn clean test)'
        sh '(cd ./SitNBeer/; mvn sonar:sonar)'
      }
    }
    stage('IntegrationTest') {
      agent {
        docker {
          image 'lucienmoor/katalon-for-jenkins:latest'
          args '-p 8888:8080'
        }
      }
      steps {
        echo "Running integration tests"
        unstash "app"
        sh 'java -jar .SitNBeer/target/SitNBeer-SNAPSHOT.jar >/dev/null 2>&1 &'
        sh 'sleep 30'
        sh 'chmod +x ./runTest.sh'
        sh './runTest.sh'

        cleanWs()
      }
    }
  }
  post {
    always {
      echo 'Clean up'
      deleteDir()
    }
  }
}
