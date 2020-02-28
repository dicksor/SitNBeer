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
                unstash "app"
                sh '(cd ./SitNBeer/; mvn clean test)'
                sh '(cd ./SitNBeer/; mvn sonar:sonar)'
	        }
        }
    }
}
