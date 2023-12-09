FROM aehrc/jre:openjdk-17

WORKDIR /app

# copy jar to root
COPY "build/libs/com.h-fahmy.templates.portainerapptemplatemerger-all.jar" ./app.jar

EXPOSE 8080

CMD ["java", "-jar", "app.jar"]