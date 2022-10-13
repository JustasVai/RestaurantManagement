# RestaurantManagement
## System database ER diagram
![image](https://user-images.githubusercontent.com/67903431/195720011-1ee3054e-f7f2-4dc4-990c-6a3f1451ea82.png)

## REST API
The Restaurant management system(backend) REST API description.

### Get list of restaurants
`GET /restaurants`
#### Example Response
  ![image](https://user-images.githubusercontent.com/67903431/195717352-98e2daee-f1e0-44d7-950a-4882b81efbbd.png)


### Get a specific restaurant
`GET /restaurants/{restaurantID}`
#### Example
![image](https://user-images.githubusercontent.com/67903431/195717465-0f15d9ae-70bf-48dc-af72-de885cb9ddb7.png)



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

