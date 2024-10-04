# RestAPIMaven
A Sample repo for REST API in Java using Spring Maven framework


#### How-To guide:
1. Clone project on local
2. Open in IntelliJ -> find `pom.xml` -> right click -> Maven -> Click `Generate resources and update folders`
	Note: This project is built using Amazon Coretto 17.0.12 SDK with Java8 language. Settings can be configured in IntelliJ -> File -> Project Settings
3. Go to `Main.java` and run
4. Download Postman on local

##### API guideline:

###### Create a User
`URL`: http://localhost:8080/addUser
body: raw-json
```
{
  "id": 5,
  "name": "Leo",
  "age": 5,
  "email": "leo@mail.com"
}
```
Success Response: Status 200 OK with response entity as body above

Failed Response: 

###### Get a User
`URL`: http://localhost:8080/getUser?name=Leo
Success Response:
```
Status 200 OK
{
    "id": 1,
    "name": "Shae",
    "age": 42,
    "email": "shae@abc.com"
}
```

Failed Response:
```
Status 404 NOT FOUND
{
    "timestamp": "2024-10-04T18:12:17.133+00:00",
    "status": 404,
    "error": "Not Found",
    "path": "/getUser"
}
```

###### Get All Users
`URL`: http://localhost:8080/getAllUsers
Success Response:
```
Status 200 OK

[
    {
        "id": 1,
        "name": "Alby",
        "age": 32,
        "email": "alby@abc.com"
    },
    {
        "id": 1,
        "name": "Molly",
        "age": 25,
        "email": "molly@abc.com"
    },
    {
        "id": 1,
        "name": "Shae",
        "age": 42,
        "email": "shae@abc.com"
    }
]
```

###### Update a User
`URL`: http://localhost:8080/updateUser

body: raw-json
```
{
  "id": 100,
  "name": "Sh",
  "age": 5,
  "email": "shae@mail.com"
}
```
Success Response: Status 200 OK with response entity as body above

Failed Response: Status 304 Not Modified: "User Sh not found"

###### Delete a User
`URL`: http://localhost:8080/deleteUser?userName=Shae

body: raw-json
```
{
  "id": 100,
  "name": "Shae",
  "age": 5,
  "email": "shae@mail.com"
}
```

Success Response: Status 202 Accepted

- Note: you can run a getAll() here to confirm

Failed Response: 
```
`Status 422 Unprocessible Entity`
{
    "timestamp": "2024-10-04T18:17:43.750+00:00",
    "status": 422,
    "error": "Unprocessable Entity",
    "path": "/deleteUser"
}
