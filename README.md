# Project description

# System architecture
The system consists of the following components:\
Back-end - Spring Boot\
Front-end - Angular\
Database - MySQL (local)

## System database ER diagram
![image](https://user-images.githubusercontent.com/67903431/208318871-2a622abc-09fe-44fb-a4e7-a13bc42710ad.png)


# API documentation
The Restaurant management system(backend) REST API description.

The following table gives an overview of how the API functions generally behave.

| Request type | Description |
| ------------ | ----------- |
| `GET`    | Access one or more resources and return the result as JSON. |
| `POST`   | Return `201 Created` if the resource is successfully created. |
| `PUT`    | Return `200 OK` if the resource is accessed or modified successfully. The (modified) result is returned as JSON. |
| `DELETE` | Return `200 OK` if the resource was deleted successfully. The (deleted) result is returned as JSON. |

## API V1  /api/v1/{path}:
## Restaurants 

### 
Every request needs header:
```http
Authorization: Bearer OAUTH-TOKEN
```
#### Get list of restaurants
```http
GET /restaurants
```
Example response:
```
{
  "id": "550ce892-fcbf-4cdb-bb5a-c0305842a4e0"
  "name": "String",
  "address": "String",
  "rating": 0,
  "phoneNumber": "String",
  "description": "String"
}
```

#### Get a specific restaurant
```http
GET /restaurants/{restaurantID}
```
Example response:
```
{
  "id": "550ce892-fcbf-4cdb-bb5a-c0305842a4e0",
  "name": "String",
  "address": "String",
  "rating": 0,
  "phoneNumber": "String",
  "description": "String"
}
```


#### Post new restaurant
```http
POST /restaurants
```
Example request body:
```
{
  "name": "String",
  "address": "String",
  "rating": 0,
  "phoneNumber": "String",
  "description": "String"
}
```

#### Update restaurant
```http
PUT /restaurants/{restaurantID}
```
Example request body:
```
{
  "name": "String",
  "address": "String",
  "rating": 0,
  "phoneNumber": "String",
  "description": "String"
}
```
Example response:
```
{
  "id": "550ce892-fcbf-4cdb-bb5a-c0305842a4e0",
  "name": "String",
  "address": "String",
  "rating": 0,
  "phoneNumber": "String",
  "description": "String"
}
```

#### Delete restaurant 
```http
DELETE /restaurants/{restaurantID}
```

## Menus
#### Get list of menus
```http
GET /restaurants/{restaurantID}/menus
```
Example response:
```
{
    "id": "550ce892-fcbf-4cdb-bb5a-c0305842a4e0",
    "startDate": "2022-10-04T21:00:00.000+00:00",
    "endDate": "2023-10-11T21:00:00.000+00:00",
    "title": "String",
    "description": "String"
}
```

#### Get a specific menu
```http
GET /restaurants/{restaurantId}/menus/{menuID}
```
Example response:
```
{
  "id": "550ce892-fcbf-4cdb-bb5a-c0305842a4e0",
  "startDate": "2022-10-04T21:00:00.000+00:00",
  "endDate": "2023-10-11T21:00:00.000+00:00",
  "title": "String",
  "description": "String"
}
```

#### Post new menu
```http
POST /restaurants/{restaurantID}/menus
```
Example request body:
```
{
  "startDate": "2022-10-04T21:00:00.000+00:00",
  "endDate": "2023-10-11T21:00:00.000+00:00",
  "title": "String",
  "description": "String"
}
```

#### Update menu
```http
PUT /restaurants/{restaurantId}/menus/{menuID}
```
Example request body:
```
{
  "startDate": "2022-10-04T21:00:00.000+00:00",
  "endDate": "2023-10-11T21:00:00.000+00:00",
  "title": "String",
  "description": "String"
}
```
Example response:
```
{
  "id": "550ce892-fcbf-4cdb-bb5a-c0305842a4e0",
  "startDate": "2022-10-04T21:00:00.000+00:00",
  "endDate": "2023-10-11T21:00:00.000+00:00",
  "title": "String",
  "description": "String"
}
```

#### Delete menu
```http
DELETE /restaurants/{restaurantId}/menus/{menuID}
```
## Menu items
#### Get list of menu items
```http
GET /restaurants/{restaurantID}/menus/{menuID}/items
```
Example response:
```
{
  "id": "550ce892-fcbf-4cdb-bb5a-c0305842a4e0",
  "name": "String",
  "price": 0,
  "recipe": "String",
  "description": "String"
}
```

#### Get a specific menu item
```http
GET /restaurants/{restaurantId}/menus/{menuID}/items/{itemID}
```
Example response:
```
{
  "id": "550ce892-fcbf-4cdb-bb5a-c0305842a4e0",
  "name": "String",
  "price": 0,
  "recipe": "String",
  "description": "String"
}
```

#### Post new menu item
```http
POST /restaurants/{restaurantID}/menus/{menuID}/items
```
Example request body:
```
{
  "name": "String",
  "price": 0,
  "recipe": "String",
  "description": "String"
}
```

#### Update menu item
```http
PUT /restaurants/{restaurantId}/menus/{menuID}/items/{itemID}
```
Example request body:
```
{
  "name": "String",
  "price": 0,
  "recipe": "String",
  "description": "String"
}
```
Example response:
```
{
  "id": "550ce892-fcbf-4cdb-bb5a-c0305842a4e0",
  "name": "String",
  "price": 0,
  "recipe": "String",
  "description": "String"
}
```

#### Delete menu item
```http
DELETE /restaurants/{restaurantId}/menus/{menuID}/items/{itemID}
```

## Users 
## /api/{path}

#### Get all users(admin only)
```http
GET /users
```
Example response:
```
{
  "email": "String",
  "username": "String",
  "roles": [
        {
          "roleName": "ROLE_USER"
        }
      ]
   },
```

#### Login to get token
```http
POST /login
```
Example request body:
```
{
    "username":"String",
    "password":"String"
}
```
Example response:
```
{
  "access_token": "String",
  "refresh_token": "String"
}
```


#### Register new user
```http
POST /register
```
Example request body:
```
{
    "username":"String",
    "email":"String",
    "password":"String"
}
```

#### Get new token without login
```http
POST /token/refresh
```
Example response:
```
{
  "access_token": "String",
  "refresh_token": "String"
}
```
