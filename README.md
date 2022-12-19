# Project description
Project goal - create a web app for creating restaurant management system\
Software consists of 3 objects - Restaurant, Menu and Menu item which are linked through a hierarchical connection:\
Restaurant -> Menu -> MenuItem\
There are three user roles - admin, employee and a regular user.\

Regular users can:
* View Restaurant list
* View Menus in specific restaurant
* Rate Restaurant

Employees can:
* Create, delete, edit new menu and menu item
* Check for recipe

Admins can:
* Add, Delete, Update Restaurants
* View all users list
* Assign new roles to users
* Delete Restaurants

# System architecture
The system consists of the following components:\
Back-end - Spring Boot\
Front-end - Angular\
Database - MySQL (local)

## System database ER diagram
![image](https://user-images.githubusercontent.com/67903431/208318871-2a622abc-09fe-44fb-a4e7-a13bc42710ad.png)

# Front end wireframes

## Login window
![image](https://user-images.githubusercontent.com/67903431/208390369-74056911-9fa8-48b3-b3ec-237599d5399d.png)
## Home window
![image](https://user-images.githubusercontent.com/67903431/208390424-a24ef3be-0b91-4f78-b275-0a91f8d943b1.png)
## Restaurants list window(Find your restaurant button pressed)
![image](https://user-images.githubusercontent.com/67903431/208390466-575c5383-3f0c-4d07-bf95-ccec5a1d04bb.png)
## Menu list window
![image](https://user-images.githubusercontent.com/67903431/208390482-445b9456-d596-4709-942e-e424599d23d5.png)
##
![image](https://user-images.githubusercontent.com/67903431/208390569-45d5e957-5ad8-4cc9-be82-b227ac96d000.png)
##
![image](https://user-images.githubusercontent.com/67903431/208390753-f5ca1518-52ba-481d-8387-c90ca1cf2843.png)
##
![image](https://user-images.githubusercontent.com/67903431/208390781-758bbbe5-2b7f-4b16-8020-dcde6fd64766.png)
## Users window (admin only)
![image](https://user-images.githubusercontent.com/67903431/208390921-6e103048-a52e-488c-b2fb-6abbe96d9340.png)
## Role popup window
![image](https://user-images.githubusercontent.com/67903431/208391139-0c52c51f-7535-4472-8cdd-fda8b5eb3b46.png)

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
