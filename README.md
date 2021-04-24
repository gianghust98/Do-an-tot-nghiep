# Do-an-tot-nghiep

### Tool
Spring Tool Suite 4.0
### Run web
- Khi pull code về nhớ vào file application.properties sửa config database, vì trên máy mình đang dùng database với cổng là 8889.
- Sau đó, ra terminal(cmd): cd vào project webtoeic -> mvn compile -> mvn test
- Run project web: mvn spring-boot:run (hoặc ở trong ide spring tool suite 4 thì chuột phải vào project webtoeic => run as => Spring boot app)
### Run server demo grpcHelloWorld
- cd vào folder grpcHelloWorld -> python3 helloWorld_server.py
