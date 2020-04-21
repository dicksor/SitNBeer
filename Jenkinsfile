pipeline {
  agent any
  environment {
    SPRING_DATASOURCE_URL='jdbc:mysql://localhost:3306/spring_db_2020?serverTimezone=UTC&useUnicode=true&characterEncoding=utf8&useSSL=false'
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
        unstash "app"
        sh '(cd ./SitNBeer/; mvn clean test)'
        sh '(cd ./SitNBeer/; mvn sonar:sonar -Dsonar.projectKey=discksor_SitNBeer -Dsonar.organization=sitnbeer -Dsonar.host.url=https://sonarcloud.io -Dsonar.login=f98acff4af31e8f5ef952a30dd36e8f5346b93c5)'
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
        sh 'java -jar ./SitNBeer/target/sitnbeer-0.0.1-SNAPSHOT.jar >/dev/null 2>&1 &'
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
