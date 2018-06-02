# Version : 1.0
# Author  : Hox <h@hooox.io>

# 使用 Oracle JDK 1.8
FROM dockette/jdk8

# 将打包后的项目添加到容器中指定目录
ADD dubbo-demo-provider-1.0.0-dev.tar.gz /app

# 切换动作目录
WORKDIR /app/dubbo-demo-provider-1.0.0

# 暴露 20880 接口
EXPOSE 20880

# 运行启动脚本
CMD ["bin/start.sh"]