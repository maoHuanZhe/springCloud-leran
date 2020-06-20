# 安装配置consul
## 下载
```
https://www.consul.io/downloads
```
## Windows安装
下载下来解压以后是个exe文件，放在任意文件夹后，在环境变量Path中配置相应路径以后
查看版本
```shell script
 consul --version
```
## 运行
```shell script
consul agent -dev
```
## 访问
```
http://localhost:8500/
```
