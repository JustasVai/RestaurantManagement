# RestaurantManagement

## REST API
The Restaurant management system(backend) REST API description.

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

