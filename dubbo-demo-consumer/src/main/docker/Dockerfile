# Version : 1.0
# Author  : Hox <h@hooox.io>

# 使用 Oracle JDK 1.8
FROM dockette/jdk8

# 将打包后的项目添加到容器中指定目录
ADD dubbo-demo-consumer-1.0.0.jar /app.jar

EXPOSE 8080

# 运行启动脚本
CMD ["java","-jar","/app.jar"]