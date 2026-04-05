pipeline {
    agent any

    tools {
        maven 'MAVEN_HOME'
        jdk 'JAVA_HOME'
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
        stage('build with maven') {
            steps {
                bat 'mvn clean test'
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
                includes: 'screenshots/**,spark/**',
                keepAll: true,
                allowMissing: false,
                alwaysLinkToLastBuild: true
            ])
        }
    }
}