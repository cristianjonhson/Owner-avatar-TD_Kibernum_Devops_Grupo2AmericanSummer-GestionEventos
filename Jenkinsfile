pipeline {
    agent any
    stages {
        stage('Checkout Code') {
            steps {
                git url: 'https://github.com/cristianjonhson/TD_Kibernum_Devops_Grupo2AmericanSummer-GestionEventos.git', branch: 'test/selenium'  }
        }
        stage('Build Java Project') {
            steps {
                sh 'mvn -f pom.xml clean install'  // Construye tu proyecto Java con Maven
            }
        }
        stage('Install Python and Selenium') {
            steps {
                sh '''
                # Verificar si Python está instalado, si no, instalarlo
                if ! command -v python3 &> /dev/null
                then
                    sudo apt-get update
                    sudo apt-get install -y python3 python3-pip
                fi

                # Instalar las dependencias de Selenium
                pip3 install selenium
                '''
            }
        }
        stage('Setup ChromeDriver') {
            steps {
                // Copiar chromedriver.exe a la carpeta donde está el script
                sh 'cp scripts-automation/chromedriver.exe /usr/local/bin/chromedriver'
                sh 'chmod +x /usr/local/bin/chromedriver'
            }
        }
        stage('Run Selenium Tests') {
            steps {
                // Ejecutar cada script de pruebas
                sh 'python3 scripts-automation/categorias.py'
                sh 'python3 scripts-automation/ciudades.py'
                sh 'python3 scripts-automation/usuarios.py'
                sh 'python3 scripts-automation/eventos.py'
            }
        }
    }
    post {
        always {
            // Puedes incluir un paso para guardar los logs de los resultados
            archiveArtifacts artifacts: '**/selenium_logs/*.log', allowEmptyArchive: true

            // Generar reportes de pruebas en formato XML si es necesario
            junit 'selenium_report.xml'  // Si generas reportes en XML, si no, puedes quitar esto
        }
    }
}
