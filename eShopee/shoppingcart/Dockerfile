# Use openjdk 11
FROM openjdk:11

# Refer to Maven artifactId and version
ARG JAR_FILE=target/shoppingcart-0.0.1-SNAPSHOT.jar

# cd /opt/app
WORKDIR /opt/app

VOLUME /tmp

#Expose port refer to server.port in application.yaml, else use default port 8080
EXPOSE 8080

# cp target/shoppingcart-0.0.1-SNAPSHOT.jar /opt/app/shoppingcart.jar
COPY ${JAR_FILE} shoppingcart.jar

# java -jar /opt/app/shoppingcart.jar
ENTRYPOINT ["java","-jar","shoppingcart.jar"]