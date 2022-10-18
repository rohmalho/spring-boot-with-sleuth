# For Java 11, try this
FROM openjdk:11

# Refer to Maven build -> finalName
ARG JAR_FILE=target/pricing-0.0.1-SNAPSHOT.jar

# cd /opt/app
WORKDIR /opt/app

VOLUME /tmp

EXPOSE 8082

# cp target/pricing-0.0.1-SNAPSHOT.jar /opt/app/pricing.jar
COPY ${JAR_FILE} pricing.jar

# java -jar /opt/app/pricing.jar
ENTRYPOINT ["java","-jar","pricing.jar"]