# RestaurantManagement
## System database ER diagram
![image](https://user-images.githubusercontent.com/67903431/195720011-1ee3054e-f7f2-4dc4-990c-6a3f1451ea82.png)

## REST API
The Restaurant management system(backend) REST API description.

The following table gives an overview of how the API functions generally behave.

| Request type | Description |
| ------------ | ----------- |
| `GET`    | Access one or more resources and return the result as JSON. |
| `POST`   | Return `201 Created` if the resource is successfully created. |
| `PUT`    | Return `200 OK` if the resource is accessed or modified successfully. The (modified) result is returned as JSON. |
| `DELETE` | Return `200 OK` if the resource was deleted successfully. The (deleted) result is returned as JSON. |

## API V1  /api/v1/{path}:
### Swagger can be reached /swagger-ui/index.html
### Get list of restaurants
`GET /restaurants`

### Get a specific restaurant
`GET /restaurants/{restaurantID}`

### Post new restaurant
`POST /restaurants`

### Update restaurant
`PUT /restaurants/{restaurantID}`

### Delete restaurant 
`DELETE /restaurants/{restaurantID}`


### Get list of menus
`GET /restaurants/{restaurantID}/menus`

### Get a specific menu
`GET /restaurants/{restaurantId}/menus/{menuID}`

### Post new menu
`POST /restaurants/{restaurantID}/menus`

### Update menu
`PUT /restaurants/{restaurantId}/menus/{menuID}`

### Delete menu
`DELETE /restaurants/{restaurantId}/menus/{menuID}`


### Get list of menu items
`GET /restaurants/{restaurantID}/menus/{menuID}/items`

### Get a specific menu item
`GET /restaurants/{restaurantId}/menus/{menuID}/items/{itemID}`

### Post new menu item
`POST /restaurants/{restaurantID}/menus/{menuID}/items`

### Update menu item
`PUT /restaurants/{restaurantId}/menus/{menuID}/items/{itemID}`

### Delete menu item
`DELETE /restaurants/{restaurantId}/menus/{menuID}/items/{itemID}`

