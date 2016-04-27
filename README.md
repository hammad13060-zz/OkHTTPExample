# OkHTTPExample
A sample android client consuming rest api using [OkHttp](https://github.com/square/okhttp) library. [json-server](https://github.com/typicode/json-server "json-server") is used for quick backend

# Usage

## Android Client
1. clone the repo
2. import the android client project into the android studio
3. change the ip address for SERVER_URL variable in Constants.java to the machine on which you will run json-server
  - consider `public static final String SERVER_URL = "http://192.168.0.8:3000/users/";`
  - replace `192.168.0.8` with your ip address
4. Build the project. Run.

## JSON server
1. install [node.js](https://nodejs.org/en/)
2. install [json-server](https://github.com/typicode/json-server) using following command
  - `npm install -g json-server`
3. cd into cloned OkHTTPExample directory
4. type the following command
  - `json-server --watch db.json`
    OR
  - `sudo json-server --watch db.json` in case access is denied
5. Your are good to go now
6. Play with client

