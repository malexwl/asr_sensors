FROM bellsoft/liberica-openjdk-alpine:17.0.13

COPY /target/asr-sensors.jar asr-sensors.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "asr-sensors.jar"]