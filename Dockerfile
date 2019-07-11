FROM openjdk:11
ADD build/libs/clonegradle.jar clonegradle.jar

ADD build/install/clonegradle/lib/animal-sniffer-annotations-1.14.jar  animal-sniffer-annotations-1.14.jar 
ADD build/install/clonegradle/lib/bcpkix-jdk15on-1.59.jar bcpkix-jdk15on-1.59.jar
ADD build/install/clonegradle/lib/bcprov-ext-jdk15on-1.59.jar bcprov-ext-jdk15on-1.59.jar
ADD build/install/clonegradle/lib/bcprov-jdk15on-1.59.jar bcprov-jdk15on-1.59.jar
ADD build/install/clonegradle/lib/builder-annotations-0.9.2.jar builder-annotations-0.9.2.jar
ADD build/install/clonegradle/lib/checker-qual-2.0.0.jar checker-qual-2.0.0.jar
ADD build/install/clonegradle/lib/client-java-4.0.0.jar client-java-4.0.0.jar
ADD build/install/clonegradle/lib/client-java-api-4.0.0.jar client-java-api-4.0.0.jar
ADD build/install/clonegradle/lib/client-java-proto-4.0.0.jar client-java-proto-4.0.0.jar
ADD build/install/clonegradle/lib/commons-codec-1.11.jar commons-codec-1.11.jar
ADD build/install/clonegradle/lib/commons-compress-1.18.jar commons-compress-1.18.jar
ADD build/install/clonegradle/lib/commons-lang3-3.7.jar commons-lang3-3.7.jar
ADD build/install/clonegradle/lib/error_prone_annotations-2.1.3.jar error_prone_annotations-2.1.3.jar
ADD build/install/clonegradle/lib/gson-2.8.0.jar gson-2.8.0.jar
ADD build/install/clonegradle/lib/guava-25.1-jre.jar guava-25.1-jre.jar
ADD build/install/clonegradle/lib/j2objc-annotations-1.1.jar j2objc-annotations-1.1.jar
ADD build/install/clonegradle/lib/joda-convert-1.2.jar joda-convert-1.2.jar
ADD build/install/clonegradle/lib/joda-time-2.9.3.jar joda-time-2.9.3.jar
ADD build/install/clonegradle/lib/jsr305-3.0.2.jar jsr305-3.0.2.jar
ADD build/install/clonegradle/lib/logging-interceptor-2.7.5.jar logging-interceptor-2.7.5.jar
ADD build/install/clonegradle/lib/okhttp-2.7.5.jar okhttp-2.7.5.jar
ADD build/install/clonegradle/lib/okhttp-ws-2.7.5.jar okhttp-ws-2.7.5.jar
ADD build/install/clonegradle/lib/okio-1.6.0.jar okio-1.6.0.jar
ADD build/install/clonegradle/lib/protobuf-java-3.4.0.jar protobuf-java-3.4.0.jar
ADD build/install/clonegradle/lib/resourcecify-annotations-0.9.2.jar resourcecify-annotations-0.9.2.jar
ADD build/install/clonegradle/lib/slf4j-api-1.7.25.jar slf4j-api-1.7.25.jar
ADD build/install/clonegradle/lib/slf4j-simple-1.7.25.jar slf4j-simple-1.7.25.jar
ADD build/install/clonegradle/lib/snakeyaml-1.19.jar snakeyaml-1.19.jar
ADD build/install/clonegradle/lib/sundr-codegen-0.9.2.jar sundr-codegen-0.9.2.jar
ADD build/install/clonegradle/lib/sundr-core-0.9.2.jar sundr-core-0.9.2.jar
ADD build/install/clonegradle/lib/swagger-annotations-1.5.12.jar swagger-annotations-1.5.12.jar

ADD resources/kubeconfig.yaml resources/kubeconfig.yaml

EXPOSE 8080
ENTRYPOINT ["java","-jar","clonegradle.jar"]