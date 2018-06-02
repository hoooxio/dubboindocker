### Dubbo + Docker + Jenkins 持续集成
**Docker 启动命令**
- 客户端启动
`docker run -d --rm -p 8080:8080 dubbo-demo-consumer:1.0.0`
- 服务端启动
`docker run -e APP_ENV="docker" -d --rm --hostname dubbo-server --add-host dubbo-server:192.168.7.207 -p 20880:20880 dubbo-demo-provider:1.0.0`
