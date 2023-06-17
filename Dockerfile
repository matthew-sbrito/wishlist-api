FROM openjdk:17-alpine

ARG APP_VERSION
ARG SERVER_PORT

WORKDIR /app

COPY /target/wishlist-${APP_VERSION}.jar /app/wishlist.jar

EXPOSE ${SERVER_PORT}

ENTRYPOINT ["java", "-jar", "wishlist.jar"]