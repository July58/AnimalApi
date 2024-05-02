# Animal API

**Description:** Uploading files with animals info

## Servers

- **Server url:** http://localhost:8082
- **DataBase:** PostgreSQL

## Paths

### Upload a file with animal information

- **Endpoint:** `/api/files/uploads`
- **Method:** POST
- **Summary:** Upload a file with animal information
- **Request Body:**
    - **Content Type:** multipart/form-data
- **Responses:**
    - **HTTP Status 201:**
        - **Description:** File uploaded successfully
        - **Content Type:** application/json
    - **HTTP Status 400:**
        - **Description:** Bad request

### Retrieve animals with optional filtering and sorting

- **Endpoint:** `/api/animals`
- **Method:** GET
- **Summary:** Retrieve animals with optional filtering and sorting
- **Parameters:**
    - **filters (query):**
        - **Description:** Filters to apply on animals (e.g., type=cat)
    - **sort (query):**
        - **Description:** Sort field for animals (e.g., name)
- **Responses:**
    - **HTTP Status 200:**
        - **Description:** OK
        - **Content Type:** application/json

