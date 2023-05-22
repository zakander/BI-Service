FROM --platform=linux/amd64 openjdk:20
VOLUME /tmp
EXPOSE 8080
ADD target/stock-prediction-service-0.0.1-SNAPSHOT.jar stock-prediction-service-0.0.1-SNAPSHOT.jar
ENTRYPOINT [ "sh", "-c", "java -jar /stock-prediction-service-0.0.1-SNAPSHOT.jar" ]