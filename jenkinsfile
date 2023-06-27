node{
    
    stage('Clonnage du repository'){
        git 'https://github.com/GustavoLeDev/Projet_PAK_Demo.git'
    }
    
    stage('Maven Build'){
        def MavenHome = toll name: "Maven 3.9.1", type:"maven"
        def mavenCMD = "${MavenHome}/bin/mvn"
        sh '${mavenCMD} clean package'
    }
    
    stage('Revision du code'){
        withSonarQubeEnv('sonarqube'){
             def MavenHome = toll name: "Maven 3.9.1", type:"maven"
             def mavenCMD = "${MavenHome}/bin/mvn"
             sh '${mavenCMD} sonar:sonar'
        }
    }
    
    stage('Telechargement de l artefact'){
        nexusArtifactUploader artifacts: [[artifactId: '01-maven-web-app', classifier: '', file: 'target/01-maven-web-app.war', type: 'war']], credentialsId: 'Nexus-credential', groupId: 'in.PAK_project', nexusUrl: '127.0.0.1:8081', nexusVersion: 'nexus3', protocol: 'http', repository: 'PAK_project-snapshot-repository', version: '1.0-SNAPSHOT'
        
    }
    
    stage('Deploiement'){
        sshagent(['TOMCAT']) {
          sh 'scp -o StrictHostkeyChecking=no target/01-maven-web-app.war admin@127.0.0.1:Program Files/Apache Software Foundation/Tomcat 10.1/webapps'
       }
    }
}
