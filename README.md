## Steps for running this service locally:

1. clone this repository
2. Import this project in InteliJ as a maven project
3. You'll firstly need to run a postgres database, you can run one by using docker and the following command: `docker run -p 5432:5432 postgres:9.5`
4. run it by either running in terminal : mvn spring-boot:run or by running the main class AuthorizationServerApplication.java

### Steps for using it:
1. Firstly you'll need to create a user. You can do so by:
    * navigating to [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html) and selecting the post `users` option.
    * using curl, for example:
           `curl -X POST --header 'Content-Type: application/json' --header 'Accept: application/json' -d '{
              "email": "cpatrut@outlook.com",
              "firstName": "Catalin",
              "lastName": "Patrut",
              "nickname": "bill2",
              "password": "abc123"
            }' 'http://localhost:8080/users/'`
            
2. Obtain access_tokens by sending a request with curl (or other tool but make sure it's similar with the curl one:):
    `curl --request POST \
       --url 'http://localhost:8080/oauth/token?grant_type=password&username=bill&password=abc123' \
       --header 'authorization: Basic bXktdHJ1c3RlZC1jbGllbnQ6c2VjcmV0' \
       --header 'cache-control: no-cache' \
       --header 'postman-token: b3aec035-5f30-1bb7-c4c2-985c0af85bdf'`
       
       
      