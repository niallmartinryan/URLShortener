FROM openjdk:11-jre
ARG JAR_FILE=target/*.jar
VOLUME /tmp
COPY ${JAR_FILE} URLShortener-0.0.1-SNAPSHOT.jar
EXPOSE 8080
# RUN chmod +x /URLShortener-0.0.1-SNAPSHOT.jar
# CMD apt-get -y update && apt-get -y install redis-server && redis-server
ENTRYPOINT ["java","-jar","URLShortener-0.0.1-SNAPSHOT.jar"]

