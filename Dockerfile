FROM amazoncorretto:11 as bulid
WORKDIR application

COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
COPY src src

# set cache to decrease re-build time
RUN --mount=type=cache,target=/root/.m2 ./mvnw install
RUN cp /application/target/*.jar app.jar
RUN java -Djarmode=layertools -jar app.jar extract

FROM amazoncorretto:11
WORKDIR application
COPY --from=bulid application/* ./

ENTRYPOINT ["java", "org.springframework.boot.loader.JarLauncher"]