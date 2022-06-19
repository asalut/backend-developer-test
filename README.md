# herd-service
Backend developer test for Software Engineer role at Halter

# How to run locally
Note: Please ensure that Docker is installed and running within the host system.

1. Open your CLI and go to the base directory of the project - i.e. herd-serivce-asalut
2. Within the CLI, type the below command to create the Docker image for herd-service, and run it and its dependencies
        docker-compose up
3. When the services have started successfully and running, you can consume its APIs through port 8090

Example CURL command for different endpoints:

Create a new cow:
curl --request POST http://localhost:8090/cows -H "Content-Type: application/json" -d "{\"collarId\":\"1\",\"cowNumber\":\"12345\"}"

Update a cow:
curl --request PUT http://localhost:8090/cows/b084fc50-6749-4bfe-92c1-986e76edbd99 -H "Content-Type: application/json" -d "{\"collarId\":\"2\",\"cowNumber\":\"12345\"}"

Get all cows:
curl --request GET http://localhost:8090/cows
