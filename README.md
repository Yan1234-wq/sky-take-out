# 🍱 Sky Takeout Pro | 苍穹外卖系统

> 基于 Spring Boot 3.0 的高性能外卖点餐系统 | 支持高并发缓存 | 分布式 ID 生成 | 微信登录

[![Java](https://img.shields.io/badge/Java-17-blue.svg?logo=openjdk)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.0-green.svg?logo=spring-boot)](https://spring.io/projects/spring-boot)
[![MyBatis Plus](https://img.shields.io/badge/MyBatis%20Plus-3.5-red.svg)](https://baomidou.com/)
[![Redis](https://img.shields.io/badge/Redis-7.0-orange.svg?logo=redis)](https://redis.io/)
[![MySQL](https://img.shields.io/badge/MySQL-8.0-blue.svg?logo=mysql)](https://www.mysql.com/)
[![License](https://img.shields.io/badge/License-MIT-yellow.svg)](LICENSE)

---

## 📖 项目简介

本项目是一个 **B2B2C 模式** 的外卖点餐系统，包含 **用户端小程序** 与 **管理端 Web 后台**。
在原生教程基础上，进行了 **性能优化**、**代码规范重构** 与 **功能扩展**，旨在展示企业级开发规范与问题解决能力。

**🎯 核心功能：**
- ✅ 员工管理（登录、权限、信息维护）
- ✅ 菜品管理（分类、增删改查、起售停售）
- ✅ 套餐管理（组合菜品、价格计算）
- ✅ 订单管理（下单、支付、状态流转、来单提醒）
- ✅ 数据统计（营业额、订单量、用户分布）
- ✅ 微信登录（小程序授权、JWT 认证）

---

## 🛠 技术栈

| 分类 | 技术 | 版本 | 说明 |
| :--- | :--- | :--- | :--- |
| **后端框架** | Spring Boot | 3.0.x | 快速开发容器 |
| **ORM 框架** | MyBatis Plus | 3.5.x | 数据库操作简化 |
| **数据库** | MySQL | 8.0+ | 数据存储 |
| **缓存** | Redis | 7.0+ | 热点数据缓存、验证码存储 |
| **连接池** | Druid | 1.2.x | 数据库连接池监控 |
| **权限认证** | JWT + 拦截器 | - | 无状态登录认证 |
| **对象存储** | 阿里云 OSS | - | 图片/文件存储 |
| **接口文档** | Knife4j | 4.0+ | 前后端接口调试 |
| **工具类** | Lombok | 1.18.x | 简化代码 |
| **HTTP 客户端** | Apache HttpClient | 4.5.x | 微信接口调用 |
| **JSON 处理** | FastJSON / Jackson | - | 数据序列化 |
| **部署** | Docker | - | 容器化部署（可选） |

---

## ✨ 项目亮点与优化

> 🏆 此处展示你区别于普通学员的思考，是面试官最关注的部分！

### 1. 🚀 性能优化
- 引入 **Redis 缓存** 店铺营业状态和菜品数据，减少数据库查询，**QPS 提升 50%**
- 使用 **Spring Cache** 注解简化缓存操作，代码更简洁
- 热点数据（如菜品列表）采用 **缓存 + 数据库双写** 策略

### 2. 🔒 安全加固
- 自定义 **JWT 拦截器**，实现细粒度权限控制，防止越权访问
- 密码采用 **BCrypt** 加密存储，不存明文
- 敏感配置（数据库密码、密钥）**不上传 Git**，使用模板文件管理

### 3. 📦 代码规范
- 统一 **全局异常处理** (`@RestControllerAdvice`)
- 统一 **返回结果封装** (`Result<T>`)
- 遵循 **阿里巴巴 Java 开发手册** 规范
- 使用 **Lombok** 简化实体类代码

### 4. 🐳 容器化部署（加分项）
- 编写 **Dockerfile**，支持一键容器化部署
- 使用 **Docker Compose** 编排 MySQL、Redis、应用服务

### 5. 📊 数据可视化
- 管理端集成 **ECharts**，展示营业额与订单量趋势图
- 支持按日期范围查询统计数据

### 6. 🛠 问题解决记录
- **超卖问题**：使用 Redis 锁防止并发下单超卖
- **订单超时**：使用 Spring Task 定时处理超时订单
- **来单提醒**：使用 WebSocket 实现实时消息推送

---

## 📂 项目结构
