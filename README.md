# Docker를 이용한 spring-boot 어플리케이션 만들기

[원문](http://blog.adaofeliz.com/2014/11/21/first-look-spring-boot-and-docker/)

- mac os에서 docker daemon을 실행
- maven build plugins에 아래 plugin을 추가

```xml
<plugin>
        <artifactId>maven-resources-plugin</artifactId>
        <executions>
            <execution>
                <id>copy-resources</id>
                <phase>validate</phase>
                <goals>
                    <goal>copy-resources</goal>
                </goals>
                <configuration>
                    <outputDirectory>${basedir}/target/</outputDirectory>
                    <resources>
                        <resource>
                            <directory>.docker</directory>
                            <filtering>true</filtering>
                        </resource>
                    </resources>
                </configuration>
            </execution>
        </executions>
    </plugin>
```

- Controller 구현
- 아래와 같이 $PROJECT_ROOT/.docker/Dockerfile 을 생성

```dockerfile
FROM williamyeh/java8
ADD docker-demo-0.0.1-SNAPSHOT.jar /opt/docker-demo/
EXPOSE 8080
WORKDIR /opt/docker-demo/
CMD ["java", "-jar", "docker-demo-0.0.1-SNAPSHOT.jar"]
```

- build

```
mvn clean install
cd target
docker build -t docker-demo .
```

- Check Image Id with `docker images`

```
~/Downloads/docker-demo/target (master●)$ docker images
REPOSITORY          TAG                 IMAGE ID            CREATED             SIZE
docker-demo         latest              fa859c4a9247        9 minutes ago       611MB
ubuntu              latest              452a96d81c30        12 days ago         79.6MB
williamyeh/java8    latest              00bc163fa009        6 weeks ago         593MB
```

- run

`docker run -p 8080:8080 <image id>`

앞의 port가 사용할 port, 뒤는 docker container가 expose한 포트
