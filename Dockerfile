# -------- BUILD STAGE --------
FROM maven:3.9.6-eclipse-temurin-17 AS builder
WORKDIR /app

COPY pom.xml .
RUN mvn dependency:go-offline

COPY src ./src
RUN mvn clean package -DskipTests

# -------- RUN STAGE --------
FROM eclipse-temurin:17-jre
WORKDIR /app

# 👇 THIS LINE IS THE MOST IMPORTANT
COPY --from=builder /app/target/*jar /app/app.jar

# Debug safety (temporary but recommended)
RUN ls -l /app

EXPOSE 8080
CMD ["java", "-jar", "/app/app.jar"]
