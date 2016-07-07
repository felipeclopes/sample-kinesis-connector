FROM maven:3.3.9-jdk-7

RUN mkdir /app

COPY . /app

WORKDIR /app

RUN cd /app && mvn clean install

CMD ["java", "-jar", "target/connector-1.0-jar-with-dependencies.jar"]