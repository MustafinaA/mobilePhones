FROM tomcat:9-jdk8
COPY  target/mobiles.war /usr/local/tomcat/webapps/mobiles.war
EXPOSE 8080