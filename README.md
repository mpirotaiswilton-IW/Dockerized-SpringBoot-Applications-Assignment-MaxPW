# Dockerized-SpringBoot-Applications-Assignment-MaxPW
 
This Github repository contains two Springboot APIs that can be dockerized:

- A Hello-World API
- A Naughts and Crosses API

## Setup

### Pre-requisites

1. If not already installed:

-  Install Docker on your device (you can use the following link for a guide: [https://docs.docker.com/get-docker/](https://docs.docker.com/get-docker/))
- Install the latest version of OpenJDK 17 on your device (The following page has a complete catalogue of OpenJDK downloads: [https://www.openlogic.com/openjdk-downloads](https://www.openlogic.com/openjdk-downloads))

2. Clone this repository or download the .zip file from GitHub (extract the downloaded zip file )

### Running Hello-World

1. Using a Command Line Interface used to run Docker and docker-compose commands, change directory to the downloaded/cloned repository, then change directory to the "/hello-world" folder

2. Run the following command: 

```
docker-compose up
```

3. The Docker container should now be running with the "Hello-World" API installed
4. Using a browser, search "[localhost:8080](localhost:8080)": you will be greeted with the following text:

```
Hello World! :D
```

### Running Naughts-and-crosses

1. Using a Command Line Interface used to run Docker and docker-compose commands, change directory to the downloaded/cloned repository, then change directory to the "/naughts-and-crosses" folder

2. Run the following command: 

```
docker-compose up
```

3. The Docker container should now be running with the "Naughts and Crosses" API installed and can now be tested using Postman
4. Import the "Naughts and Crosses API.postman_collection.json" file into Postman: this will include a full collection of requests you can use on the Naughts-and-crosses API

#### Playing a game (using Postman)

1. Register 2 new users by running the "Register User 1" and "Register User 2" requests (localhost:8080/user/register/). By default, these requests will create 2 new users called "foo" and "bar" with their own passwords. Feel free to change these if you wish. A successful request will return a "201 Created" response.
2. Authenticate for each new user by using the "Authenticate User" request (localhost:8080/authenticate), filling in the request body with their respective credentials (by default the request body is populated with the credentials for a user called "foo"). Sending a successful request should yield a 200 OK response and a response Header called Authorization with a bearer token value: Copy the token value (the long string after the word 'Bearer') for later use. A Token for this API will last for 12 hours.
3. Create a new game using the "Create Game" request (localhost:8080/game/). Under Postman's Authorization
tab for the request or in the Authorization header, put the bearer token of the user who is creating the game (eg. user foo or bar). Inside the request body, define some game settings such as whether the player creating the new game starts first or are playing as naughts or crosses. A successful request should return a 201 Created response along with a JSON object of the newly created game.
4. Have a second user join the newly created game using the "Join Game" (localhost:8080/game/join/{game id}). Under Postman's Authorization tab for the request or in the Authorization header, put the bearer token of the other user who is joining the game. A successful request should return a 200 OK response along with a JSON object of the newly created game.
5. Each user can now submit a move using the "Play Move" request (localhost:8080/game/{game id}/play/{number from 1 to 9}). Don't forget each user must enter their token to be authorized to submit a new move. Users can use the second path parameter to select the square they want to choose, where 1 is the top left most square and 9 is the bottom right most square:
```
ASCII visual representation:

1 | 2 | 3
--+---+--
4 | 5 | 6
--+---+--
7 | 8 | 9
```
5. (Cont') A successful move will return a 200 OK response and a list of all moves played in  chronological order. Players become unable to submit a move when the game has been won or been deemed a draw, where all squares are filled and no winning position has been achieved. 
6. (Optional) Users can also forfeit a game if they so choose using the "Forfeit Game" request (localhost:8080/game/forfeit/{game id}). Under Postman's Authorization tab for the request or in the Authorization header, put the bearer token of the user who is forfeiting the game (this user must be one of the players). A successful request should return a 200 OK response along with a JSON object of the forfeited game.
7. (Optional) Any user who created a game can also delete it at any time using the "Delete Game" request (localhost:8080/game/delete/{game id}). Under Postman's Authorization tab for the request or in the Authorization header, put the bearer token of the user who is deleting the game they created. A successful request should return a 204 OK response.

