FROM openjdk:11
EXPOSE 8090
ADD target/rudy-kafka-dummy.jar rudy-kafka-dummy.jar
ENTRYPOINT ["java","-jar","/java_ocr.jar"]

