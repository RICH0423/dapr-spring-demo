# Service Invocation with Dapr 

## The description of services

| Attribute                   | Details |
|-----------------------------|---------|
| Dapr runtime version        | v1.4.0  |
| Dapr CLI version            | v1.4.0  |
| Java version                | Java 11 |
| Python version              | 3.9.7   |
| Node version                | 8.1.1   |

## Run the Dapr sidecar in self-hosted (Docker)
- Run the Node.js app with Dapr
```bash
cd node
npm install
dapr run --app-id nodeapp --app-port 3000 --dapr-http-port 3500 node app.js
```

- Post messages to the node service by Dapr sidecar
```bash
curl -XPOST -d '{"data":{"orderId":"42"}}' -H Content-Type:application/json \
http://localhost:3500/v1.0/invoke/nodeapp/method/neworder
```

- Verify the order was successfully persisted to the state store
```bash
curl http://localhost:3500/v1.0/invoke/nodeapp/method/order
```

- Run the Python app with Dapr
```bash
pip3 install requests
dapr run --app-id pythonapp python3 app.py
```

- Run the Spring Boot app with Dapr
```bash
./mvnw clean package
dapr run --app-id springapp --app-port 8081 -- java -jar target/spring-dapr-0.0.1.jar
```

