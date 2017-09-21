FROM codeurjc/demo-sip-client

COPY src src
ADD pom.xml /

CMD mvn test;
