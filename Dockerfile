FROM openjdk:8-jre-alpine
VOLUME /tmp
ADD @project.build.finalName@.jar app.jar
RUN sh -c 'touch /app.jar'
ENTRYPOINT [ "sh", "-c", "java -jar /app.jar" ]
