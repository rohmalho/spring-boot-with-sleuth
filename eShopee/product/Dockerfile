# For Java 11, try this
FROM openjdk:11

# Refer to Maven build -> finalName
ARG JAR_FILE=target/product-0.0.1-SNAPSHOT.jar

# cd /opt/app
WORKDIR /opt/app

VOLUME /tmp

EXPOSE 8081

# cp target/product-0.0.1-SNAPSHOT.jar /opt/app/product.jar
COPY ${JAR_FILE} product.jar

# java -jar /opt/app/product.jar
ENTRYPOINT ["java","-jar","product.jar"]