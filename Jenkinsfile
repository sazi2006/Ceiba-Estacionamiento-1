pipeline {
	agent {
		label 'Slave_Induccion'
	
	}
	
	options {
		buildDiscarder(logRotator(numToKeepStr: '5'))
		disableConcurrentBuilds()
	
	}
	
	tools {
		jdk 'JDK8_Centos'
		gradle 'Gradle4.5_Centos'
	
	}
	
	stages {
		stage('Checkout Code') {
			steps {
				echo "------------>Checkout<------------"
				checkout([$class: 'GitSCM', branches: [[name: '*/master']],
				doGenerateSubmoduleConfigurations: false, extensions: [], gitTool:
				'Git_Centos', submoduleCfg: [], userRemoteConfigs: [[credentialsId:
				'GitHub_vquintec', url:
				'https://github.com/vquintec/Ceiba-Estacionamiento']]])
				
				sh 'gradle --b ./build.gradle clean'
			}
		
		}
		
		stage('Compile') {
			steps {
				echo "------------>Compile<------------"
				sh 'gradle --b ./build.gradle compileJava'

			}
		
		}
		
		stage('Unit Tests') {
			steps {
				echo "------------>Unit Tests<------------"
				sh 'gradle --b ./build.gradle test --tests co.com.ceiba.dominio.unitaria.* -x installAngular -x buildAngular -x compileJava'

			}
		
		}
		
		stage('Integration Tests') {
			steps {
				echo "------------>Integration Tests<------------"
				sh 'gradle --b ./build.gradle test --tests co.com.ceiba.dominio.integracion.* -x installAngular -x buildAngular -x compileJava'

			}
		}
		
		stage('Functional Tests') {
			steps {
				echo "------------>Functional Tests<------------"
			}
		}
		
		
		stage('Coverage Report') {
			steps {
				echo "GENERATE COVERAGE REPORT"
				sh 'gradle --b ./build.gradle jacocoTestReport'
			
			}
		}
		
		
		stage('Static Code Analysis') {
			steps {
				echo "STATIC CODE ANALYSIS"
				
				withSonarQubeEnv('Sonar') {
					sh "${tool name: 'SonarScanner',type: 'hudson.plugins.sonar.SonarRunnerInstallation'}/bin/sonar-scanner -Dproject.settings=sonar-project.properties"
				
				}
			}
		
		}
		
		stage('Build') {
			steps {
				echo "------------>Build<------------"
				//Construir sin tarea test que se ejecutó previamente
				sh 'gradle --b ./build.gradle build -x compileJava -x test'

			}
		
		}
	
	
	}
	
	post {
		always {
			echo "This will always run"
		
		}
		
		success {
			echo 'This will run only if successful'
			junit '**/build/test-results/test/*.xml'
		
		}
		
		failure {
			echo 'This will run only if failed'
			mail (to: 'valentin.quintero@ceiba.com.co',subject: "Failed Pipeline:${currentBuild.fullDisplayName}",body: "Something is wrong with ${env.BUILD_URL}")

		}
		
		unstable {
			echo "run unstable"
		
		}
		
		changed {
			echo "Pipeline state has changed"
		
		}

		
	
	}


}