# netty-tcp-demo

#### 介绍
基于netty的tcp长连接管理

#### 软件架构
基于netty、redis和springboot

#### 使用说明
1.  修改netty-tcp-client下的application.yml配置文件，修改redis的连接信息
2.  先后启动netty-tcp-server和netty-tcp-client项目，访问http://localhost:8088/demo/testOne开始测试


### 学习链接
1. https://mp.weixin.qq.com/s/MFKWK5tNEqSmA0HWfjC4IQ  Netty+SpringBoot 打造一个 TCP 长连接通讯方案
2. https://mp.weixin.qq.com/s/JLiz8Is-5TpZi1JnbaEAXA  Java 压缩20M文件从30秒到1秒的优化过程