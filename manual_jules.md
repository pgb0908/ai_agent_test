# Jules' Project Manual

이 문서는 `ai_agent_test` 프로젝트의 상세 매뉴얼입니다. 본 문서는 개발자와 학습자 모두를 대상으로 하며, 프로젝트 내의 API 엔드포인트에 대한 깊이 있는 분석과 사용법을 다룹니다.

## 개요 (Overview)

이 프로젝트는 Spring Boot 3.3.0 기반의 웹 애플리케이션으로, REST API 설계를 학습하고 테스트하기 위한 다양한 예제들을 포함하고 있습니다.

*   **Language**: Java 21
*   **Framework**: Spring Boot 3.3.0
*   **Base Package**: `com.example.demo`

---

## API Documentation (`HelloController`)

`HelloController`는 클라이언트의 다양한 HTTP 요청을 처리하고 적절한 응답을 반환하는 핵심 컨트롤러입니다. 총 10개의 엔드포인트가 정의되어 있으며, 각 API는 RESTful 설계의 기본 개념들과 Spring MVC의 주요 기능들을 보여줍니다.

### 1. Basic Hello
가장 기본적인 GET 요청을 처리하며, 서버의 정상 작동 여부를 확인하는 헬스 체크용으로도 사용할 수 있습니다.

*   **URL**: `/hello`
*   **Method**: `GET`
*   **Description**: 간단한 인사말 문자열을 반환합니다.
*   **Parameters**: 없음
*   **Response**: `String` ("Hello, World!")
*   **Example**:
    ```bash
    curl -X GET http://localhost:8080/hello
    ```

### 2. Path Variable
URL 경로에 포함된 변수(`@PathVariable`)를 처리하는 방법을 보여줍니다.

*   **URL**: `/greet/{name}`
*   **Method**: `GET`
*   **Description**: URL 경로로 전달받은 이름을 포함하여 인사말을 반환합니다.
*   **Parameters**:
    *   `name` (Path Variable): 인사할 대상의 이름 (String)
*   **Response**: `String` ("Hello, {name}!")
*   **Example**:
    ```bash
    curl -X GET http://localhost:8080/greet/Jules
    # Response: Hello, Jules!
    ```

### 3. Request Parameters
URL 쿼리 스트링(`@RequestParam`)을 통해 데이터를 전달받는 방법을 보여줍니다.

*   **URL**: `/sum`
*   **Method**: `GET`
*   **Description**: 두 개의 정수를 입력받아 그 합계를 반환합니다.
*   **Parameters**:
    *   `a` (Query Param): 첫 번째 정수 (int)
    *   `b` (Query Param): 두 번째 정수 (int)
*   **Response**: `String` ("Sum: {value}")
*   **Example**:
    ```bash
    curl -X GET "http://localhost:8080/sum?a=10&b=20"
    # Response: Sum: 30
    ```

### 4. POST with Body
HTTP Body(`@RequestBody`)를 통해 데이터를 서버로 전송하는 방법을 보여줍니다. 주로 데이터 생성 시 사용됩니다.

*   **URL**: `/echo`
*   **Method**: `POST`
*   **Description**: 클라이언트가 보낸 메시지를 그대로 반환(Echo)합니다.
*   **Parameters**:
    *   Body: 서버로 보낼 메시지 (String)
*   **Response**: `String` ("Echo: {message}")
*   **Example**:
    ```bash
    curl -X POST http://localhost:8080/echo \
         -H "Content-Type: text/plain" \
         -d "Hello Server"
    # Response: Echo: Hello Server
    ```

### 5. Random Number
서버 내부 로직에 의해 생성된 동적인 데이터를 반환하는 예제입니다.

*   **URL**: `/random`
*   **Method**: `GET`
*   **Description**: 0부터 99 사이의 무작위 정수를 반환합니다.
*   **Parameters**: 없음
*   **Response**: `int` (0 ~ 99)
*   **Example**:
    ```bash
    curl -X GET http://localhost:8080/random
    ```

### 6. JSON Response (Map)
객체나 Map을 반환할 때 Spring Boot가 자동으로 JSON 형식으로 직렬화(Serialization) 해주는 기능을 보여줍니다.

*   **URL**: `/status`
*   **Method**: `GET`
*   **Description**: 서버의 현재 상태와 가동 시간(uptime) 정보를 JSON 포맷으로 반환합니다.
*   **Parameters**: 없음
*   **Response**: `JSON`
    ```json
    {
      "uptime": 1717123456789,
      "status": "UP"
    }
    ```
*   **Example**:
    ```bash
    curl -X GET http://localhost:8080/status
    ```

### 7. PUT Request
리소스를 업데이트할 때 주로 사용되는 PUT 메서드(`@PutMapping`) 예제입니다.

*   **URL**: `/update`
*   **Method**: `PUT`
*   **Description**: 입력받은 데이터로 업데이트를 수행했다는 메시지를 반환합니다. (실제 DB 연동은 없음)
*   **Parameters**:
    *   Body: 업데이트할 데이터 내용 (String)
*   **Response**: `String` ("Updated: {data}")
*   **Example**:
    ```bash
    curl -X PUT http://localhost:8080/update \
         -H "Content-Type: text/plain" \
         -d "New Data"
    # Response: Updated: New Data
    ```

### 8. DELETE Request
리소스를 삭제할 때 사용되는 DELETE 메서드(`@DeleteMapping`) 예제입니다.

*   **URL**: `/delete/{id}`
*   **Method**: `DELETE`
*   **Description**: 특정 ID를 가진 리소스를 삭제했다는 메시지를 반환합니다.
*   **Parameters**:
    *   `id` (Path Variable): 삭제할 리소스의 ID (int)
*   **Response**: `String` ("Deleted item with ID: {id}")
*   **Example**:
    ```bash
    curl -X DELETE http://localhost:8080/delete/42
    # Response: Deleted item with ID: 42
    ```

### 9. Request Header
HTTP 헤더 정보(`@RequestHeader`)를 추출하여 사용하는 방법을 보여줍니다.

*   **URL**: `/headers`
*   **Method**: `GET`
*   **Description**: 요청을 보낸 클라이언트의 `User-Agent` 헤더 값을 읽어서 반환합니다.
*   **Parameters**:
    *   `User-Agent` (Header): 클라이언트 브라우저 또는 도구 정보
*   **Response**: `String` ("Your User-Agent is: {userAgent}")
*   **Example**:
    ```bash
    curl -X GET http://localhost:8080/headers \
         -H "User-Agent: MyCustomAgent/1.0"
    # Response: Your User-Agent is: MyCustomAgent/1.0
    ```

### 10. Current Time
Java의 `LocalDateTime`을 사용하여 날짜/시간 데이터를 처리하는 예제입니다.

*   **URL**: `/time`
*   **Method**: `GET`
*   **Description**: 서버의 현재 날짜와 시간을 반환합니다.
*   **Parameters**: 없음
*   **Response**: `String` ("Current Time: {ISO-8601 Timestamp}")
*   **Example**:
    ```bash
    curl -X GET http://localhost:8080/time
    ```

---
작성자: Jules (AI Agent)
작성일: 2024년
