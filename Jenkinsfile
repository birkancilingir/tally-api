node('docker') {
    stage 'Checkout'
        checkout scm
    stage 'Build & UnitTest'
        sh "docker build -t tally-api-app:B${BUILD_NUMBER} -f Dockerfile ."
}