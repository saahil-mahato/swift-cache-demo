
# swift-cache-demo

## Steps to run app:

1. Install openjdk-8 and maven.
2. Install dependencies using `mvn clean install`
3. Run the application with `mvn spring-boot:run`

# Book API Documentation

## Base URL


## Endpoints

### 1. Retrieve a Book

- **Endpoint:** `GET /books/{id}`
- **Description:** Retrieves a `Book` by its identifier.
- **Path Parameters:**
    - `id` (String): The identifier of the `Book` to retrieve.
- **Response:**
    - **200 OK:** The `Book` with the specified identifier.
- **Example Request:**

    ```http
    GET /books/123abc
    ```
- **Example Response:**

    ```json
    {
      "id": "123abc",
      "name": "The Great Gatsby",
      "author": "F. Scott Fitzgerald",
      "price": 10.99
    }
    ```

### 2. Add a New Book

- **Endpoint:** `POST /books`
- **Description:** Adds a new `Book` to the system.
- **Request Body:**
    - **Content-Type:** `application/json`
    - **Body:** The `Book` object to add.
- **Response:**
    - **201 Created:** The added `Book`.
- **Example Request:**

    ```http
    POST /books
    Content-Type: application/json
    
    {
      "name": "1984",
      "author": "George Orwell",
      "price": 8.99
    }
    ```
- **Example Response:**

    ```json
    {
      "id": "456def",
      "name": "1984",
      "author": "George Orwell",
      "price": 8.99
    }
    ```

### 3. Remove a Book

- **Endpoint:** `DELETE /books/{id}`
- **Description:** Removes a `Book` from the system by its identifier.
- **Path Parameters:**
    - `id` (String): The identifier of the `Book` to remove.
- **Response:**
    - **204 No Content:** The `Book` was successfully removed.
- **Example Request:**

    ```http
    DELETE /books/123abc
    ```

### 4. Get Cache Size

- **Endpoint:** `GET /books/cache/size`
- **Description:** Retrieves the current size of the cache.
- **Response:**
    - **200 OK:** An integer representing the number of entries currently in the cache.
- **Example Request:**

    ```http
    GET /books/cache/size
    ```
- **Example Response:**

    ```
    10
    ```

### 5. Clear Cache

- **Endpoint:** `DELETE /books/cache`
- **Description:** Clears all entries from the cache.
- **Response:**
    - **204 No Content:** The cache was successfully cleared.
- **Example Request:**

    ```http
    DELETE /books/cache
    ```
