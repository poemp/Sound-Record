@echo off
echo Install Baidu JDK

mvn install:install-file -DgroupId=com.baidu.aip -DartifactId=java-sdk -Dversion=4.4.1 -Dpackaging=jar -Dfile=../lib/aip-java-sdk-4.1.1.jar

echo Install Success