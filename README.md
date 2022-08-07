# MicroService


微服务

跨语言通信框架thrift

    信息模块 --message-thrift-python
    工具模块 --micro-service-util
    用户模块 --user-edge-service
    用户DAO --user-thrift-service
    

# docker
```text
docker tag zookeeper fqj102/zookeeper:3.5
docker images
docker push fqj102/zookeeper3.5
```

## 私有仓库
### 1. registry
```text
- search registry
- docker run -d -p 5000:5000 --restart always --name registry registry:2
- docker tag zookeeper:latest localhost:5000/zookeeper:3.5
- 확인 http://localhost:5000/v2/_catalog
```
### 2.harbor
```text
1.
    vi harbor.yml
        hostname: hub.xxxx.com
        port :8181
        harbor_admin_password: new_password
        data_volume: /Users/xxx/i/apps/harbor/data
        location: /Users/xxx/i/apps/harbor/log
    run install.sh
2.
    docker tag <imageID> <harbor_server>/<project_name>/<repo>:<tag>
    docker push <harbor_server>/<project_name>/<repo>:<tag>

3. --login(close proxy)
    - 503 error
    - docker info =>close http_proxy => https://github.com/docker/for-mac/issues/2715
    - docker login harbor.gaosu.com -u admin -p Harbor12345
4.
    docker tag zookeeper:latest hub.xxxx.com:8181/micro-service/zookeeper:3.5
    docker push hub.xxxx.com:8181/micro-service/zookeeper:3.5 
```

