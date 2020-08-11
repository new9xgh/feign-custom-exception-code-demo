# Feign-Custom-Exception-Code-Demo

## Introduction
An exception occurred in the system when using the feign, can response the custom structure.

### Custom response structure example
```json
{"code": -1, "message": "system error", "data": null}
```

## Environment
- JDK 1.8

## Getting Started
1. Clone this repository using `git clone`.
2. Open the cloned project.
3. Import the `pom.xml` file from the root directory using `Maven Projects`.
4. Find each Application class to run each module.

## Module
- eureka
- gateway
- order-service
- product-service
- manager-web

## Test Case
- Case1: Success
```http request
POST http://localhost:8081/manager/order/create
Content-Type: application/json

{
  "userId" : 1000,
  "productId" : 1,
  "num" : 1
}
```

- Case2: Fail, parameter validate error
```http request
POST http://localhost:8081/manager/order/create
Content-Type: application/json

{
  "userId" : null,
  "productId" : 1,
  "num" : 1
}
```

- Case3: Fail, product not exist
```http request
POST http://localhost:8081/manager/order/create
Content-Type: application/json

{
  "userId" : 1000,
  "productId" : 3,
  "num" : 1
}
```

- Case4: Fail, not enough stock
```http request
POST http://localhost:8081/manager/order/create
Content-Type: application/json

{
  "userId" : 1000,
  "productId" : 2,
  "num" : 1
}
```

## License
[MIT](http://opensource.org/licenses/MIT)