FROM --platform=linux/amd64 openjdk:20
VOLUME /tmp
EXPOSE 8000
ADD target/stock-date-data-service-0.0.1-SNAPSHOT.jar stock-date-data-service-0.0.1-SNAPSHOT.jar
ENTRYPOINT [ "sh", "-c", "java -jar /stock-date-data-service-0.0.1-SNAPSHOT.jar" ]