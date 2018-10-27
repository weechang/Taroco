# moreco

#### 项目介绍

moreco -- more ecosystem 更多的生态系统

moreco 是一个能够为小、中、大型项目提供最合适架构的一条龙生态系统。满足项目从小型到中型至大型的衍变过程。从编码到监控至运维都满足、且各种功能都插件化，支持插件间的切换。

常常在网上看到很多项目，一来就是spring cloud、docker等。当时一个项目最开始可能只是一个简单的想法，而这个想法需要快速成型。所以微服务、容器化并不合适，反而一个简单的单体应用就够了。

但是很少有从单体到集群再到微服务的项目。这样可能开始的时候单体项目是一套代码体系、微服务的时候又是另一套代码体系。这样对开发资源造成很大的浪费。

moreco能够满足你的项目从单体到微服务的整个流程。但你从单体迁移到微服务的时候，只需要改动很小一部分代码就能实现项目的微服务化，从而节省项目迁移成本。

moreco不仅仅是一套开发框架，moreco更是一套生态系统。从开发到监控至运维，moreco都提供一套更为完善的组件支持。包括但不限于以下项目：

* 架构方面：spring boot开发、spring cloud开发、领域驱动开发。

* 开发方面：对象存储（七牛、阿里云、HDFS、LOCAL）、消息通知（邮件、短信、站内信）。

* 监控报警：物理机监控、应用监控、方法监控、数据库监控。

* 运维方面：统一日志。

#### 软件架构

软件架构说明

#### 技术选型


#### 项目结构
``` 
moreco
├── moreco-base ------------------------------ 基础、公共方法封装
├── moreco-cloud ----------------------------- spring cloud 组件
|    ├── moreco-cloud-breaker----------------- 熔断
|    ├── moreco-cloud-config  ---------------- 配置中心
|    ├── moreco-cloud-gateway ---------------- 网关
|    ├── moreco-cloud-register --------------- 服务注册中心
├── moreco-demo ------------------------------ demo
|    ├── moreco-spring-boot-demo ------------- spring boot demo
├── moreco-message --------------------------- 消息中心
├── moreco-monitor --------------------------- 监控中心
├── moreco-ops ------------------------------- 运维中心
├── moreco-oss ------------------------------- 对象存储
├── moreco-rbac ------------------------------ RBAC权限管理
├── moreco-security -------------------------- 安全认证
|    ├── moreco-security-base ---------------- 安全认证基础组件
|    ├── moreco-security-shiro --------------- shiro 组件
|    ├── moreco-security-spring-security ----- spring security 组件
├── moreco-starter --------------------------- 启动组件
|    ├── moreco-starter-spring-boot ---------- spring boot 启动组件
|    ├── moreco-starter-spring-cloud --------- spring cloud 启动组件
```

#### 开发进度


#### 安装教程

1. xxxx
2. xxxx
3. xxxx

#### 使用说明

1. xxxx
2. xxxx
3. xxxx

#### 捐赠名单
