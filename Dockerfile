FROM bellsoft/liberica-openjdk-alpine:17.0.13

#WORKDIR /target

#COPY ../target/asr-sensors.jar asr-sensors.jar
COPY /target/asr-sensors.jar asr-sensors.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "asr-sensors.jar"]