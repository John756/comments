- Demo aplikacia  bola testovana na Ubuntu 22.04.4 LTS
- Aplikacia je v java od JetBrains ver. jdks/jbr-17.0.11
- Pouziva H2 in memory databazu ktora je naplnena zo resoureces/data.sql
- Vyuziva externú API na https://jsonplaceholder.typicode.com/
- Konfiguracny subor aplikacie je rovnako v resources/application.properties, aplikacia bezi na porte 8081
- Aplikaciu otvorime v IntelliJ ako maven projekt, downloadneme javu a priradime projektu
- Vytvorime run configuraciu Sprig boot, meno: PrispevkyApplication, Run on: Local Machine, Build on: Java 17, Directory(Spring boot class): org.example.prispevky.PrispevkyApplication
- Dotiahneme pom zavislosti vygenerujem sources a prip.refresneme a zbuildujem cez maven mvn install
- Swagger dokumentacia je generovana maven-om automaticky pri maven builde pluginom enunciate-maven-plugin. V target/site je konfigurovana v enunciate.xml
- Integracne testy je mozne spustit pri maven builde alebo z testovacich clases
- Pre spustenie v Dockeri je nutne zastavit aplikaciu, docker bezi na tom istom porte ako Tomcat. Po nainstalovani Dockeru vytvorime Dockerfile, prevedieme docker login, vybuildujem aplikaciu: j@XPS-15-9560:~/ProjectsIntelliJ/Prispevky$ docker build -t comment-app .
- downoladneme pre docker: j@XPS-15-9560:~/ProjectsIntelliJ/Prispevky$ docker pull openjdk:17-jdk-slim
- nasttartujeme aplikaciu v dockeri: docker run -p 8081:8081 comment-app
- priklad uspesneho spustenia aplikacie v dockeri aprveho requestu:
j@XPS-15-9560:~/ProjectsIntelliJ/Prispevky$ docker build -t comment-app .
[+] Building 237.6s (17/17) FINISHED                                                                                                                                                                                                                                                           docker:default
 => [internal] load .dockerignore                                                                                                                                                                                                                                                                        0.0s
 => => transferring context: 2B                                                                                                                                                                                                                                                                          0.0s
 => [internal] load build definition from Dockerfile                                                                                                                                                                                                                                                     0.0s
 => => transferring dockerfile: 1.25kB                                                                                                                                                                                                                                                                   0.0s
 => [internal] load metadata for docker.io/library/openjdk:17-jdk-slim                                                                                                                                                                                                                                   0.0s
 => [internal] load metadata for docker.io/library/maven:3.8.1-openjdk-17-slim                                                                                                                                                                                                                           1.0s
 => [auth] library/maven:pull token for registry-1.docker.io                                                                                                                                                                                                                                             0.0s
 => [stage-1 1/3] FROM docker.io/library/openjdk:17-jdk-slim                                                                                                                                                                                                                                             0.0s
 => [build 1/7] FROM docker.io/library/maven:3.8.1-openjdk-17-slim@sha256:b33f2ce5438552e6c0d2f8e675319efdd5f5a318f3c9b9a279ffedb7f673ada0                                                                                                                                                               0.0s
 => [internal] load build context                                                                                                                                                                                                                                                                        0.0s
 => => transferring context: 36.01kB                                                                                                                                                                                                                                                                     0.0s
 => [stage-1 2/3] WORKDIR /app                                                                                                                                                                                                                                                                           0.0s
 => CACHED [build 2/7] WORKDIR /app                                                                                                                                                                                                                                                                      0.0s
 => [build 3/7] COPY . /app/                                                                                                                                                                                                                                                                             0.2s
 => [build 4/7] COPY pom.xml /app/                                                                                                                                                                                                                                                                       0.0s
 => [build 5/7] RUN mvn dependency:go-offline                                                                                                                                                                                                                                                          213.4s
 => [build 6/7] COPY src /app/src/                                                                                                                                                                                                                                                                       0.0s
 => [build 7/7] RUN mvn clean package -DskipTests                                                                                                                                                                                                                                                       21.7s
 => [stage-1 3/3] COPY --from=build /app/target/comment-0.0.1-SNAPSHOT.jar /app/comment-app.jar                                                                                                                                                                                                          0.1s
 => exporting to image                                                                                                                                                                                                                                                                                   0.3s
 => => exporting layers                                                                                                                                                                                                                                                                                  0.3s
 => => writing image sha256:0d1db69adee00b1a07d291df048a73e021b4ad15757c3601d5c007bc2d16e633                                                                                                                                                                                                             0.0s
 => => naming to docker.io/library/comment-app                                                                                                                                                                                                                                                           0.0s
j@XPS-15-9560:~/ProjectsIntelliJ/Prispevky$ docker run -p 8081:8081 comment-app

  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/

 :: Spring Boot ::       (v3.4.0-SNAPSHOT)

2024-08-15T07:32:32.112Z  INFO 1 --- [Prispevky] [           main] o.e.prispevky.PrispevkyApplication       : Starting PrispevkyApplication v0.0.1-SNAPSHOT using Java 17.0.2 with PID 1 (/app/comment-app.jar started by root in /app)
2024-08-15T07:32:32.120Z  INFO 1 --- [Prispevky] [           main] o.e.prispevky.PrispevkyApplication       : No active profile set, falling back to 1 default profile: "default"
2024-08-15T07:32:33.285Z  INFO 1 --- [Prispevky] [           main] .s.d.r.c.RepositoryConfigurationDelegate : Bootstrapping Spring Data JPA repositories in DEFAULT mode.
2024-08-15T07:32:33.501Z  INFO 1 --- [Prispevky] [           main] .s.d.r.c.RepositoryConfigurationDelegate : Finished Spring Data repository scanning in 205 ms. Found 1 JPA repository interface.
2024-08-15T07:32:34.199Z  INFO 1 --- [Prispevky] [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat initialized with port 8081 (http)
2024-08-15T07:32:34.224Z  INFO 1 --- [Prispevky] [           main] o.apache.catalina.core.StandardService   : Starting service [Tomcat]
2024-08-15T07:32:34.224Z  INFO 1 --- [Prispevky] [           main] o.apache.catalina.core.StandardEngine    : Starting Servlet engine: [Apache Tomcat/10.1.28]
2024-08-15T07:32:34.280Z  INFO 1 --- [Prispevky] [           main] o.a.c.c.C.[Tomcat].[localhost].[/]       : Initializing Spring embedded WebApplicationContext
2024-08-15T07:32:34.282Z  INFO 1 --- [Prispevky] [           main] w.s.c.ServletWebServerApplicationContext : Root WebApplicationContext: initialization completed in 1971 ms
2024-08-15T07:32:34.332Z  INFO 1 --- [Prispevky] [           main] com.zaxxer.hikari.HikariDataSource       : HikariPool-1 - Starting...
2024-08-15T07:32:34.659Z  INFO 1 --- [Prispevky] [           main] com.zaxxer.hikari.pool.HikariPool        : HikariPool-1 - Added connection conn0: url=jdbc:h2:mem:~/COMMENTS user=NA
2024-08-15T07:32:34.661Z  INFO 1 --- [Prispevky] [           main] com.zaxxer.hikari.HikariDataSource       : HikariPool-1 - Start completed.
2024-08-15T07:32:34.674Z  INFO 1 --- [Prispevky] [           main] o.s.b.a.h2.H2ConsoleAutoConfiguration    : H2 console available at '/h2-console'. Database available at 'jdbc:h2:mem:~/COMMENTS'
2024-08-15T07:32:34.881Z  INFO 1 --- [Prispevky] [           main] o.hibernate.jpa.internal.util.LogHelper  : HHH000204: Processing PersistenceUnitInfo [name: default]
2024-08-15T07:32:34.947Z  INFO 1 --- [Prispevky] [           main] org.hibernate.Version                    : HHH000412: Hibernate ORM core version 6.5.2.Final
2024-08-15T07:32:34.989Z  INFO 1 --- [Prispevky] [           main] o.h.c.internal.RegionFactoryInitiator    : HHH000026: Second-level cache disabled
2024-08-15T07:32:35.334Z  INFO 1 --- [Prispevky] [           main] o.s.o.j.p.SpringPersistenceUnitInfo      : No LoadTimeWeaver setup: ignoring JPA class transformer
2024-08-15T07:32:35.405Z  WARN 1 --- [Prispevky] [           main] org.hibernate.orm.deprecation            : HHH90000025: H2Dialect does not need to be specified explicitly using 'hibernate.dialect' (remove the property setting and it will be selected by default)
2024-08-15T07:32:36.291Z  INFO 1 --- [Prispevky] [           main] o.h.e.t.j.p.i.JtaPlatformInitiator       : HHH000489: No JTA platform available (set 'hibernate.transaction.jta.platform' to enable JTA platform integration)
Hibernate: alter table if exists comments alter column body set data type varchar(255)
2024-08-15T07:32:36.333Z  INFO 1 --- [Prispevky] [           main] j.LocalContainerEntityManagerFactoryBean : Initialized JPA EntityManagerFactory for persistence unit 'default'
2024-08-15T07:32:36.937Z  WARN 1 --- [Prispevky] [           main] JpaBaseConfiguration$JpaWebConfiguration : spring.jpa.open-in-view is enabled by default. Therefore, database queries may be performed during view rendering. Explicitly configure spring.jpa.open-in-view to disable this warning
2024-08-15T07:32:37.420Z  INFO 1 --- [Prispevky] [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port 8081 (http) with context path '/'
2024-08-15T07:32:37.439Z  INFO 1 --- [Prispevky] [           main] o.e.prispevky.PrispevkyApplication       : Started PrispevkyApplication in 5.938 seconds (process running for 6.58)
2024-08-15T07:32:58.708Z  INFO 1 --- [Prispevky] [nio-8081-exec-1] o.a.c.c.C.[Tomcat].[localhost].[/]       : Initializing Spring DispatcherServlet 'dispatcherServlet'
2024-08-15T07:32:58.709Z  INFO 1 --- [Prispevky] [nio-8081-exec-1] o.s.web.servlet.DispatcherServlet        : Initializing Servlet 'dispatcherServlet'
2024-08-15T07:32:58.710Z  INFO 1 --- [Prispevky] [nio-8081-exec-1] o.s.web.servlet.DispatcherServlet        : Completed initialization in 1 ms
Hibernate: select c1_0.id,c1_0.body,c1_0.title,c1_0.userid from comments c1_0 where c1_0.id=?
Hibernate: update comments set body=?,title=?,userid=? where id=?

-docker zastavime tak ze vyhladame ID spusteneho kontajneru: j@XPS-15-9560:~$ docker ps
 CONTAINER ID   IMAGE         COMMAND                  CREATED          STATUS          PORTS                                       NAMES
 d0f344004c52   comment-app   "java -jar comment-a…"   25 minutes ago   Up 25 minutes   0.0.0.0:8081->8081/tcp, :::8081->8081/tcp   sharp_elion
 -a nasledne zastavime docker stop d0f344004c52





