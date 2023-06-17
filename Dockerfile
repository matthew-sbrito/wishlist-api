FROM maven as builder

WORKDIR /app

COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:17-alpine

ARG APP_VERSION
ARG SERVER_PORT

WORKDIR /app

COPY --from=builder /app/target/wishlist-${APP_VERSION}.jar /app/wishlist.jar

EXPOSE ${SERVER_PORT}

ENTRYPOINT ["java", "-jar", "wishlist.jar"]