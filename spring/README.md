init project with http://start.spring.io
工具书 Spring in Action, 5th Edition
#### 属性校验
```
@Data
public class Order {

@NotBlank(message="Zip code is required")
private String zip;
@CreditCardNumber(message="Not a valid credit card number")
private String ccNumber;

private Date createdAt;

@PrePersist  //JPA的注解
void createdAt() {
    this.createdAt = new Date();
}
}

public String processDesign(@Valid Order order, Errors errors) {

<input type="text" th:field="*{ccNumber}"/>
<span class="validationError"
th:if="${#fields.hasErrors('ccNumber')}"
th:errors="*{ccNumber}">CC Num Error</span>

```

JPA 也是支持自定义查询的
```
@Query("Order o where o.deliveryCity='Seattle'")
List<Order> readOrdersDeliveredInSeattle();
```

##### HTTPS
$ keytool -keystore mykeys.jks -genkey -alias tomcat -keyalg RSA

server:
  port: 8443
  ssl:
    key-store: file:///path/to/mykeys.jks
    key-store-password: letmein
    key-password: letmein
    
##### 配置属性文件元数据
需要在 META-INF（例如，在项目下的 src/main/resources/META-INF 中）中创建一个名为 
addition-spring-configuration-metadata.json 的文件

spring-configuration-metadata.json 是默认的

#### 消息队列

JMS java message service，  activeMq Artemis
RabbitMq  kafka

Messages can be transformed, split, aggregated, routed, and processed by service
activators in the course of a flow.

####Spring Cloud native

Scaling Eureka

#### hystrix

<dependency>
<groupId>org.springframework.cloud</groupId>
<artifactId>spring-cloud-starter-netflix-hystrix-dashboard</artifactId>
</dependency>

management:
    endpoints:
        web:
            exposure:
                include: hystrix.stream

@EnableHystrixDashboard
使用 http://localhost:****/hystrix  观察 http://localhost:<otherport>/actuator/hystrix.stream

@EnableTurbine  多个hystrix监控

yaml配置文件
turbine:
    app-config: ingredient-service,taco-service,order-service,user-service
    stream-name-expression: "'default'"
    
http://localhost:8989/turbine.stream 监控

还有一个轻量级熔断器 CircuitBreaker  failsafe
<dependency>
  <groupId>net.jodah</groupId>
  <artifactId>failsafe</artifactId>
  <version>2.3.5</version>
</dependency>

#### Actuator
```
GET /auditevents Produces a report of any audit events that have
been fired.

GET /beans Describes all the beans in the Spring application
context.

GET /conditions Produces a report of autoconfiguration conditions
that either passed or failed, leading to the beans
created in the application context.

GET /configprops Describes all configuration properties along with
the current values.

GET, POST,
DELETE
/env Produces a report of all property sources and their
properties available to the Spring application.

GET /env/{toMatch} Describes the value of a single environment property. No
GET /health Returns the aggregate health of the application
and (possibly) the health of external dependent
applications.

GET /heapdump Downloads a heap dump. No
GET /httptrace Produces a trace of the most recent 100 requests. No
GET /info Returns any developer-defined information about
the application.

GET /loggers Produces a list of packages in the application along
with their configured and effective logging levels.

GET, POST /loggers/{name} Returns the configured and effective logging level
of a given logger. The effective logging level can be
set with a POST request.

GET /mappings Produces a report of all HTTP mappings and their
corresponding handler methods.

GET /metrics Returns a list of all metrics categories. No
GET /metrics/{name} Returns a multidimensional set of values for a
given metric.

GET /scheduledtasks Lists all scheduled tasks. No
GET /threaddump Returns a report of all application threads. No
```

暴露Actuator endpoints
```yaml
management:
  endpoints:
    web:
      exposure:
        include: health,info,beans,conditions
        或
        include: '*'
        exclude: threaddump,heapdump
```

curl localhost:8080/actuator
curl localhost:8080/actuator/health

显示health详情
```yaml
management:
  endpoint:
    health:
      show-details: always
```

自定义health
```java
@Component
public class WackoHealthIndicator
implements HealthIndicator {
@Override
public Health health() {
```


自定义信息
```java
@Component
public class TacoCountInfoContributor implements InfoContributor {
    private TacoRepository tacoRepo;
    public TacoCountInfoContributor(TacoRepository tacoRepo) {
    this.tacoRepo = tacoRepo;
    }
    @Override
    public void contribute(Builder builder) {
        long tacoCount = tacoRepo.count();
        Map<String, Object> tacoMap = new HashMap<String, Object>();
        tacoMap.put("count", tacoCount);
        builder.withDetail("taco-stats", tacoMap);
    }
}
```
更多自定义属性
http 参考16章
jmx 参考18章

安全认证
```java
@Override
protected void configure(HttpSecurity http) throws Exception {
http
.authorizeRequests()
.antMatchers("/actuator/**").hasRole("ADMIN")
.and()
.httpBasic();
}
```

Spring boot admin
可以监控健康状态， 线程， 性能等

#### Config
```java
@Component
public class ServiceConfig{
@Value("${example.property}")
private String exampleProperty;
```

#### Zuul
静态路由

```yaml 
zuul:
routes:
licensestatic:
path: /licensestatic/**
serviceId: licensestatic
ribbon:
eureka:
enabled: false
licensestatic:
ribbon:
listOfServers: http://licenseservice-static1:8081,
http://licenseservice-static2:8082
```
hystrix 配置的时间超过5秒，你必须同时设置Hystrix和Ribbon的超时时间
```yaml
hystrix.command.default.execution.isolation.thread.timeoutInMilliseconds: 2500
hystrix.command.user-service.execution.isolation.thread.timeoutInMilliseconds: 7000
user-service.ribbon.ReadTimeout: 7000
```
