<center><font size=72>Moreco</font></center>

moreco -- more ecosystem
 
[![License](https://img.shields.io/badge/license-MIT-blue.svg)](LICENSE)  [![PRs Welcome](https://img.shields.io/badge/PRs-welcome-brightgreen.svg)](https://github.com/weechang/moreco) [![jdk](https://img.shields.io/badge/JDK-1.8+-green.svg)](https://www.oracle.com/technetwork/java/javase/downloads/index.html) [![github](https://img.shields.io/github/stars/weechang/moreco.svg?style=social)](https://github.com/weechang/moreco)

前端：[https://github.com/weechang/moreco-view](https://github.com/weechang/moreco-view)
 
演示：[https://moreco.weechang.xyz/](https://moreco.weechang.xyz/)

## 简介

moreco 是一个能够为小、中、大型项目提供最合适架构的一条龙生态系统。满足项目从小型到中型至大型的衍变过程。从编码到监控至运维都满足、且各种功能都插件化，支持插件间的切换。

常常在网上看到很多项目，一来就是spring cloud、docker等。当时一个项目最开始可能只是一个简单的想法，而这个想法需要快速成型。所以微服务、容器化并不合适，反而一个简单的单体应用就够了。

但是很少有从单体到集群再到微服务的项目。这样可能开始的时候单体项目是一套代码体系、微服务的时候又是另一套代码体系。这样对开发资源造成很大的浪费。

moreco能够满足你的项目从单体到微服务的整个流程。但你从单体迁移到微服务的时候，只需要改动很小一部分代码就能实现项目的微服务化，从而节省项目迁移成本。

moreco不仅仅是一套开发框架，moreco更是一套生态系统。从开发到监控至运维，moreco都提供一套更为完善的组件支持。包括但不限于以下项目：

* 软件架构：spring boot → spring cloud（包括spring cloud alibaba）。

* 结构组件：对象存储（七牛、阿里云、腾讯云、又拍云、LOCAL）、消息通知（邮件、短信、站内信）。

* 请求鉴权：spring-security

* 系统运维：性能监控、微服务管理、统一日志。

## 技术选型

### 后端技术

| **技术**                    | **名称**       | **官网**  |
| -------------------------- | -------------- | --------  |
| Spring Framework           | 容器            | [https://spring.io/projects/spring-framework](https://spring.io/projects/spring-framework) |
| Spring MVC                 | MVC框架         |  |
| Spring Boot                | 快速开发        | [https://spring.io/projects/spring-boot](https://spring.io/projects/spring-boot) |
| Spring Cloud               | 微服务框架      | [https://spring.io/projects/spring-cloud](https://spring.io/projects/spring-cloud) |
| AliOSS & Qiniu & QCloudCOS | 云存储平台       | [https://www.aliyun.com/product/oss/](https://www.aliyun.com/product/oss/) [http://www.qiniu.com/](http://www.qiniu.com/) [https://www.qcloud.com/product/cos](https://www.qcloud.com/product/cos) |
| Maven                      | 版本控制工具     | [http://maven.apache.org/](http://maven.apache.org/) |

### 前端技术

| **技术**      | **名称**       | **官网**  |
| --------------| --------------| --------  |
| Node.js       | 编译环境       | [https://nodejs.org/](https://nodejs.org/) |
| npm           | 版本控制工具   | [https://www.npmjs.com/](https://www.npmjs.com/) |
| Vue.js        | 组件库         | [https://cn.vuejs.org/](https://cn.vuejs.org/) |


## 项目结构
``` 
moreco
├── moreco-core ------------------------------ 核心、公共方法封装
├── moreco-data ------------------------------ ORM
|    ├── moreco-data-core -------------------- ORM 核心
|    ├── moreco-data-jpa --------------------- ORM Jpa
|    ├── moreco-data-redis ------------------- ORM Redis
|    ├── moreco-data-mongodb ----------------- ORM MongoDB
├── moreco-component ------------------------- 项目组件
|    ├── moreco-component-swagger ------------ swagger 组件
|    ├── moreco-component-rbac --------------- rbac 组件
|    ├── moreco-component-oss ---------------- 对象存储组件
|    ├── moreco-component-message ------------ 消息通知组件
|    ├── moreco-component-ops ---------------- 运维组件（日志、动态切流）
├── moreco-security -------------------------- 安全认证
├── moreco-cloud ----------------------------- spring cloud 组件
|    ├── moreco-cloud-register --------------- 服务注册中心
|    ├── moreco-cloud-config  ---------------- 配置中心
|    ├── moreco-cloud-gateway ---------------- 网关
├── moreco-demo ------------------------------ demo
|    ├── moreco-spring-boot-demo ------------- spring boot demo
|    ├── moreco-spring-cloud-demo-a ---------- spring cloud demo a
|    ├── moreco-spring-cloud-demo-a ---------- spring cloud demo b
├── moreco-monitor --------------------------- 监控中心
|    ├── moreco-core ------------------------- 基础类
|    ├── moreco-agent ------------------------ sdk、agent
|    ├── moreco-home ------------------------- 数据处理、管理
├── moreco-starter --------------------------- 启动组件
|    ├── moreco-starter-spring-boot ---------- spring boot 启动组件
|    ├── moreco-starter-spring-cloud --------- spring cloud 启动组件
├── moreco-task ------------------------------ 分布式任务管理
```

## 软件架构

软件架构说明

## 开发进度汇总

![开发进度](/imgs/moreco-doing.png)

## 运行效果

![Login](/imgs/moreco-login.png)

![Home](/imgs/moreco-home.png)

![Rbac](/imgs/moreco-rbac.png)

![i18n](/imgs/moreco-i18n.png)

![swagger](/imgs/moreco-swagger.png)

![monitor](/imgs/moreco-monitor.png)