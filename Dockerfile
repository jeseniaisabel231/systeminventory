# ------------------------------------------------------------------
# ETAPA 1: Construcción (Build Stage)
# Usamos una imagen JDK completa para compilar
# ------------------------------------------------------------------
FROM eclipse-temurin:25-jdk AS builder

WORKDIR /app

# Copiamos primero la configuración de maven para aprovechar la caché de Docker
COPY .mvn/ .mvn
COPY mvnw pom.xml ./

# Damos permisos de ejecución al wrapper
RUN chmod +x mvnw

# Descargamos las dependencias (esto se cacheará si no cambias el pom.xml)
RUN ./mvnw dependency:go-offline

# Copiamos el código fuente
COPY src ./src

# Compilamos el proyecto y generamos el JAR (saltando tests para agilidad en build)
RUN ./mvnw clean package -DskipTests

# Extraemos el nombre del jar generado para facilitar la copia
# Spring Boot suele generar el jar en /target
ARG JAR_FILE=target/*.jar
RUN cp ${JAR_FILE} application.jar

# Decompresion de capas (Opcional pero recomendado para Spring Boot moderno)
RUN java -Djarmode=layertools -jar application.jar extract

# ------------------------------------------------------------------
# ETAPA 2: Ejecución (Run Stage)
# Usamos una imagen JRE (o JDK si JRE no existe aún para v25) más ligera
# ------------------------------------------------------------------
FROM eclipse-temurin:25-jdk
# Nota: Si existe una versión "-jre" o "-alpine" para Java 25, úsala aquí para ahorrar espacio.

WORKDIR /application

# Copiamos las capas extraídas en la etapa anterior (Mejor práctica de Spring Boot)
COPY --from=builder /app/dependencies/ ./
COPY --from=builder /app/spring-boot-loader/ ./
COPY --from=builder /app/snapshot-dependencies/ ./
COPY --from=builder /app/application/ ./

# Exponemos el puerto por defecto de Spring Boot
EXPOSE 8080

# Comando de entrada
ENTRYPOINT ["java", "org.springframework.boot.loader.launch.JarLauncher"]