## 致歉

<font color=#CD210B size=3>Sorry!!!由于一直以来事情较多，没来得及更新该项目，在此向关心该项目的各位小伙伴说声抱歉。该项目将于7月1日起正式开始更新，届时将花大量的时间在该项目上，希望各位小伙伴赖心等待。谢谢各位一直以来的支持。想要了解项目最新进度的小伙伴，可以watch该项目，以便接收到第一手更新信息。</font>

## 项目链接

项目 | 说明 | gitee地址 | github地址 |
----|------|----|------|----
Taroco | 后端项目 | [https://gitee.com/WeeChang_Sc/Taroco](https://gitee.com/WeeChang_Sc/Taroco)  | [https://github.com/weechang/Taroco](https://github.com/weechang/Taroco)
Taroco-View | 前端项目 | [https://gitee.com/WeeChang_Sc/Taroco-View](https://gitee.com/WeeChang_Sc/Taroco-View)  | [https://github.com/weechang/Taroco-View](https://github.com/weechang/Taroco-View)


 
## 项目介绍
   基于spring boot，spring cloud，axon framework的领域驱动设计的微服务开发框架系统。提供整套微服务架构模块：统一用户中心，用户授权、鉴权，配置中心，注册中心，服务路由，服务熔断，追踪、治理，灰度发布。后期将继续加上存储系统、微信系统。 
## 适用对象
   Taroco并不适用于所有场景。
   如果你只是想快速开发一款简单的产品的话，Taroco并不适合你。 
   如果你的系统只是一个简单的单体应用就能解决的话，Taroco并不适合你。
   Taroco适用的场景。
   由于Taroco采用CQRS（命令责任分离）架构,能比普通应用承载更高的读写，因此Taroco适合于高并发的系统。
   Taroco适用于复杂，需求迭代快速的业务场景。
   Taroco采用DDD（领域建模）思想，因此，Taroco适合于有一定DDD基础的开发者。
## 项目架构

``` lua
Taroco
├── taroco-config -- 分布式配置中心
├── taroco-registry -- 服务注册中心
├── taroco-gateway -- 服务网关
├── taroco-breaker -- 服务容错保护
├── taroco-common -- 项目基础服务组件
|    ├── taroco-core -- 核心组件封装
|    ├── taroco-monitor -- 应用监控
|    ├── taroco-log -- 统一日志
|    ├── taroco-service -- 服务组件封装
├── taroco-user-center -- 用户中心
|    ├── user-center-command -- 用户命令端
|    ├── user-center-common -- 用户公用
|    ├── user-center-query -- 用户查询端
├── taroco-oauth2 -- 用户授权
```

## 后端技术
技术 | 名称 | 官网
----|------|----
Spring Boot | 容器  | [http://projects.spring.io/spring-boot/](http://projects.spring.io/spring-boot/)
Spring Cloud | 微服务解决方案  | [http://projects.spring.io/spring-cloud/](http://projects.spring.io/spring-cloud/)
Axon | 容器  | [http://www.axonframework.org/](http://www.axonframework.org/)
Rabbit MQ | 消息队列  | [http://www.rabbitmq.com/](http://www.rabbitmq.com/)
Spring Data | 持久化  | [http://projects.spring.io/spring-data/](http://projects.spring.io/spring-data/)
Swagger2 | 接口测试框架  | [http://swagger.io/](http://swagger.io/)
Jenkins | 持续集成工具  | [https://jenkins.io/index.html](https://jenkins.io/index.html)
Maven | 项目构建管理  | [http://maven.apache.org/](http://maven.apache.org/)

## 前端技术
技术 | 名称 | 官网
----|------|----
LayUI | 函式库  | [http://www.layui.com/](http://www.layui.com/)
VUE | 函式库  | [https://cn.vuejs.org/](https://cn.vuejs.org/)
vue-router 2 | vue路由  | [https://router.vuejs.org/zh-cn/](https://router.vuejs.org/zh-cn/)
iview | VUE组件  | [https://www.iviewui.com](https://www.iviewui.com)

## 开发工具
- MongoDB: 数据库
- RabbitMQ: 消息中间件
- Git: 版本管理
- IntelliJ IDEA: 开发IDE

## 开发环境
- JDK8+

## 许可证
[MIT](LICENSE "MIT")


## 常见问题
- 安装lombok插件
   - eclipse [http://blog.csdn.net/hinstenyhisoka/article/details/50468271](http://blog.csdn.net/hinstenyhisoka/article/details/50468271)
   - idea [http://blog.csdn.net/hinstenyhisoka/article/details/50468271](http://blog.csdn.net/hinstenyhisoka/article/details/50468271)