FROM --platform=linux/amd64 openjdk:20
VOLUME /tmp
EXPOSE 8001
ADD target/stock-scraper-service-0.0.1-SNAPSHOT.jar stock-scraper-service-0.0.1-SNAPSHOT.jar
ENTRYPOINT [ "sh", "-c", "java -jar /stock-scraper-service-0.0.1-SNAPSHOT.jar" ]