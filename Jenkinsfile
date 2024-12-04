pipeline {
    agent any  // This tells Jenkins to run the pipeline on any available agent

    environment{
        listEmails="singhpalankush@gmail.com"
    }

    parameters {
        // Parameters to pass values from Jenkins UI when triggering the pipeline
        string(description: "Added branch to deploy", defaultValue:'master' name:'branchname')
        string(name: 'CITY', defaultValue: 'London', description: 'City for Weather Test')
        string(name: 'ALLOWED_VARIANCE', defaultValue: '2.0', description: 'Allowed Temperature Variance')
    }

    tools{
        //  Use the installed JDK named 'jdk'
        jdk 'jdk'
        //  Install the Maven project configured
        maven mvn
    }

    stages {
        stage('Git Checkout') {
            steps {
                // This step fetches the code from the Git repository
                git branch: branchname, changelog: false, credentialsId:'Github', poll:false, url: git 'https://github.com/ankush1618/WeatherComparator_Asesment'
            }
        }

        stage('Build') {
            steps {
                // This step runs Maven to build the project (download dependencies, compile, etc.)
                sh 'mvn clean install'  // Runs Maven to clean and install dependencies
            }
        }

        stage('Test') {
            steps {
                // This step runs the tests using Maven, passing parameters for CITY and ALLOWED_VARIANCE
                sh "mvn test -DCITY=${params.CITY} -DALLOWED_VARIANCE=${params.ALLOWED_VARIANCE}"  // Executes tests
            }
        }
    }

    post {
        // This section defines post-build actions
        always {
            // Clean up, report, or other actions after pipeline run
            echo "Pipeline finished"
        }
    }
}
