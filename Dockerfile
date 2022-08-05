FROM openjdk:11
EXPOSE 8090
ADD target/java_ocr.jar java_ocr.jar
ENTRYPOINT ["java","-jar","/java_ocr.jar"]

