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
    }
}