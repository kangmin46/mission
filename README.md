# kakao pay 과제 유형 3번

## 개발 프레임워크
* 웹 프레임워크
    * Spring boot
* ORM
    * JPA(Spring data JPA) 
* DB
    * h2 database
* 빌드도구
    * gradle
* 기타 라이브러리  
    * OpenCSV   

## 문제 해결 전략

* Entity 
    * Fund
        * Fund Entity는 연도와 월 기관별로 지원 금액을 가지고 있는 엔티티 입니다.
        * Institute라는 엔티티를 ManytoOne 단방향으로 참조하고 있습니다.
    * Institute
        * 은행명과 은행기관코드만을 가지고 있는 엔티티 입니다.

* 은행별로 연도 별로 Grouping을 하고 총합을 구하는 요구사항이 많았습니다. 
* 그래서 각 연도별 은행의 지원 총합을 조회하여 데이터를 가공하여 json 으로 응답하는 로직을 애플리케이션에 구현하였습니다. 

* API 명세
    1. 데이터 파일에서 각 레코드를 데이터베이스에 저장하는 API
        requestMethod : POST
        requestUrl : /funds
        httpStatusCode : 201 Created
        responseBody : X
    2. 주택금융 공급 금융기관(은행) 목록을 출력하는 API
        requestMethod : GET
        requestUrl : /institutes
        httpStatusCode : 200 Ok
        ```
        [
            {
                "name": "주택도시기금",
                "code": "hou00"
            },
            {
                "name": "국민은행",
                "code": "bhk00"
            },
            {
                "name": "우리은행",
                "code": "bhk01"
            },
            {
                "name": "신한은행",
                "code": "bhk02"
            },
        ...
        ```
    3. 년도별 각 금융기관의 지원금액 합계를 출력하는 API 
        requestMethod : GET
        requestUrl : /funds
        httpStatusCode : 200 Ok
        ```
        "name": "주택금융 공급현황",
        "amountPerYear": [
        {
            "year": 2005,
            "totalAmount": 22247,
            "detailAmount": {
                "농협은행/수협은행": 1486,
                "하나은행": 3122,
                "우리은행": 2303,
                "국민은행": 13231,
                "신한은행": 1815,
                "외환은행": 1732,
                "주택도시기금": 22247,
                "기타은행": 1376,
                "한국시티은행": 704
            }
        },
        ...
        ```
    4. 각 년도 별 각 기관의 전체 지원금액 중에서 가장 큰 금액의 기관명을 출력하는 API
        requestMethod : GET
        requestUrl : /institutes/max
        httpStatusCode : 200 Ok
        ```
        {
            "year": 2014,
            "bank": "주택도시기금"
        }
        ```
    5. 전체 년도(2005~2016)에서 외환은행의 지원금액 평균 중에서 가장 작은 금액과 큰 금액을 출력하는 API
        * 요구사항에는 외환은행에 대한 요청만 처리하는 API였지만 기관코드를 PathVariable로 받아서 다른 기관의 지원금액도
        조회할 수 있게 하였습니다.
        requestMethod : GET
        requetUrl : /institutes/{instituteCode}/averages  (외환은행의 기관코드는 "bhk06")
        httpStatusCode : 200 Ok
        ```
        {
        "bank": "외환은행",
        "minMaxFunds": [
            {
            "year": 2015,
            "amount": 1701
            },
            {
            "year": 2017,
            "amount": 0
            }
            ]
         }
        ```
    



    

