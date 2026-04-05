pipeline {
    agent any

    tools {
        maven 'MAVEN_HOME'
        jdk 'JAVA_HOME'
    }

    parameter {
        // choice(name: 'ENVIRONMENT', choices: ['QA', 'Staging', 'Production'], description: 'Select the environment to run tests against')
        choice(name: 'BROWSER', choices: ['chrome', 'firefox', 'edge'], description: 'Select the browser to run tests on')
    }

    stages {
        stage('checkout') {
            steps {
                script {
                    try {
                        checkout scm
                        if(!fileExists('pom.xml')) {
                            error "pom.xml not found in reporsitory"
                        }

                        echo "SCM checkout completed Successfully."
                    }
                    catch (err) {
                        error "SCM checkout failed: ${err.message}"
                    }
                }
            }
        }
        stage('check maven') {
            steps {
                bat 'mvn -version'
                bat 'echo MAVEN_HOME=%MAVEN_HOME%'
                bat 'where mvn'
            }
        }
        stage('check JDK') {
            steps {
                bat 'java --version'
                bat 'echo JAVA_HOME=%JAVA_HOME%'
                bat 'where java'
            }
        }
        // stage('check environment') {
        //     steps {
        //         echo "Running tests against environment: ${params.ENVIRONMENT}"
        //     }
        // }
        stage('check browser') {
            steps {
                echo "Running tests on browser: ${params.BROWSER}"
            }
        }
        stage('build') {
            steps {
                bat 'mvn clean compile'
            }
        }
        stage('Run Tests') {
            steps {
                bat 'mvn test -Dbrowser=${params.BROWSER}'
            }
        }

    }
    
    post {
        always {
            junit '**/target/surefire-reports/*.xml'
            publishHTML ([
                reportName: 'Extent Report',
                reportDir: 'target',
                reportFiles: 'extent-report.html',
                keepAll: true,
                allowMissing: false,
                alwaysLinkToLastBuild: true
            ])
        }
    }
}