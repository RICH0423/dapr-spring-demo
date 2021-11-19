## Dapr PubSub Sample

### Implement subscriber with Spring Boot & Dapr

- To subscribe to topics, start a web server and listen on the following GET endpoint: **/dapr/subscribe** and the response format must contain the following fields:
```json
{
  "pubsubname": "pubsub-component",
  "topic": "input-topic",
  "route": "api/input"
}
```

sample code:
```java
@GetMapping("/dapr/subscribe")
    public List<Subscription> subscribe() {
        /*
         * pubsubname: Which pub/sub component Dapr should use.
         * topic: Which topic to subscribe to.
         * route: Which endpoint for Dapr to call on when a message comes to that topic.
         */
        Subscription subscription = new Subscription("pubsub", "A", "processors/A");
        return List.of(subscription);
    }
```


- run spring boot subscriber with dapr sidecar
```bash
dapr run --app-id springboot-subscriber --components-path ../components --app-port 8081 -- java -jar target/springboot-subscriber-0.0.1.jar
```

### Kakfa PubSub Component
- Component format
```yaml
apiVersion: dapr.io/v1alpha1
kind: Component
metadata:
  name: kafka-pubsub
  namespace: default
spec:
  type: pubsub.kafka
  version: v1
  metadata:
  - name: brokers 
    value: "localhost:9092"
  - name: consumerGroup
    value: "group1"
  - name: clientID
    value: "springboot-app"
  - name: authRequired
    value: "false"
```

- run dapr sidecar for testing
```bash
dapr run --log-level debug --app-id dapr-kafka --dapr-http-port 3500 --components-path components
```

- pushing message to Kafka topic
```bash
curl -X POST http://localhost:3500/v1.0/publish/kafka-pubsub/myTopic \
  -H "Content-Type: application/json" \
  -d '{
        "data": {
          "message": "Hi"
        }
      }'
```

## Cloud Events message format 
Dapr uses the CloudEvents 1.0 specification as its message format. 

- Dapr implements the following Cloud Events fields:
  - id
  - source
  - specversion
  - type
  - datacontenttype (Optional)

- The Cloud event example:
```json
{
  "traceid":"00-2fbf05af42328ddaee7423ff724cf3bb-063141a9f262cebf-01",
  "id":"1cb16190-a364-49c2-b66d-5d6b1cba426a",
  "type":"com.dapr.event.sent",
  "source":"react-form",
  "topic":"A",
  "pubsubname":"pubsub",
  "data":{"messageType":"A","message":"test2"},
  "specversion":"1.0",
  "datacontenttype":"application/json"
}
```



## Reference
- [Pubsub quickstart](https://github.com/dapr/quickstarts/tree/master/pub-sub)
- [Dapr Java SDK for Pubsub](https://github.com/dapr/java-sdk/tree/master/examples/src/main/java/io/dapr/examples/pubsub/http)
- [Kafka PubSub component](https://docs.dapr.io/reference/components-reference/supported-pubsub/setup-apache-kafka/)
