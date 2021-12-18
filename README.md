 **总结、快速记忆** 

多群组

分布部署区块链平台
1.数据库
sudo apt install mariadb-server -y
service mysql start
mysql_secure_installation
mysql -u root -p
- >use mysql;
- >update mysql.user set plugin=‘mysql_native_password’;
- >update mysql.user set password=PASSWORD(‘!Qaz@Wsx’);
- >create database if not exists webasenodemanager default charset utf8 collate utf8_general_ci;
- >create database if not exists webasetransaction0 default charset utf8 collate utf8_general_ci;
- >create database if not exists webasetransaction1 default charset utf8 collate utf8_general_ci;
- >create database if not exists webasesign default charset utf8 collate utf8_general_ci;
- >create database if not exists webasechainmanager default charset utf8 collate utf8_general_ci;
- >create database if not exists webasestat default charset utf8 collate utf8_general_ci;
- >create database if not exists webasedata default charset utf8 collate utf8_general_ci;
- >flush privileges;
- >quit;





2.nginx配置
sudo apt install nginx -y
nginx 							#运行nginx
/var/run/nginx.pid					#nginx.pid文件位置
/var/log/nginx/						#nginx Log日志位置
/etc/nginx						#nginx配置文件位置
## 方式1：警告，前面一直配置过nginx服务器，不可采用该方法
cp -rf docs/nginx.conf /usr/local/nginx/conf

## 方式2：如果服务器已有nginx，可在原配置文件nginx.conf增加一个server
upstream data_server{
        server 10.0.0.1:5010; # WeBASE-Data-Fetcher服务ip和端口
    }
    server {
        listen       5200 default_server; # 前端端口（端口需要开通策略且不能被占用）
        server_name  127.0.0.1;           # 服务器ip，也可配置为域名
        location / {
                root   /WeBASE-Data/WeBASE-Data-Web/dist;   # 前端文件路径(对应修改文件需要有权限访问)
                index  index.html index.htm;
                try_files $uri $uri/ /index.html =404;
        }

        include /etc/nginx/default.d/*.conf;

        location /mgr {
                    proxy_pass    http://data_server/;    		
                    proxy_set_header		Host			 $host;
                    proxy_set_header		X-Real-IP		 $remote_addr;
                    proxy_set_header		X-Forwarded-For	 $proxy_add_x_forwarded_for;
        }
    }
3.dist/script/webash.sh配置
节点管理node-manager、链管理chain-Manager
4.拷贝nodes/127.0.0.1/sdk/*
节点前置front、交易transaction(复制sdk.crt、sdk.key为node.crt、node.key)
5.配置dist/conf/application.*
节点前置front、节点管理node-manager、交易transaction、签名sign、链管理chain-Manager
6.管理平台Web配置
bash get_solc_js.sh
配置 docs/nginx.conf文件
cp -rf docs/nginx.conf /etc/nginx
nginx


 **Docker新建ubantu** 

1.docker pull ubuntu
2.docker run -d --name fisco  -it --privileged=true  -p 5000-5010:5000-5010 -p 3316:22 -p 5200:5200 -p 3306:3306  ubuntu
3.apt update
4.apt upgrade
5.apt install openssh-server openssh-client -y              选择6-70-19
6.passwd root  # 设置root用户密码

apt autoremove 删除已安装程序

配置ssh
(1)apt install openssh-client openssh-server -y   #安装ssh
(2) ps -e | grep ssh  #查看是否启动
(3) /etc/init.d/ssh start 或者 service ssh start
(4)systemctl enable ssh  #加入开机启动项

下载vim
apt install vim

ssh配置
(5)vi /etc/ssh/sshd_config     #配置ssh客户端
	*去掉PasswordAuthentication yes前面的#号  ;  
	*把PermitRootLogin prohibit-password改成PermitRootLogin yes
(6)/etc/init.d/ssh restart   #重启ssh服务
(7)ssh -p 端口 用户名@ip地址   #试用终端远程连接配置好的ubuntu



 **数据库配置：
MySQL允许root远程登录** 
mysql -u root -p
use mysql；
SELECT User, Password, Host FROM user;
GRANT ALL PRIVILEGES ON *.* TO 'root'@'%' IDENTIFIED BY 'root' WITH GRANT OPTION;
flush privileges;

MySQL 5.7
alter user ‘root’@‘127.0.0.1’ identified with mysql_naitve_password by ’新密码’;  #修改密码策略
create user root@‘%’ identified by ‘新密码’;    #允许远程登录

 **MariaDB修改密码策略** 
update mysql.user set plugin=‘mysql_native_password’;  #重置加密方式

update mysql.user set password=PASSWORD(‘’新密码) where User=‘root’;  #设置新密码

flush privileges;

drop database 删除数据库




 **fisco分步单设备部署手册** 

分步部署：https://webasedoc.readthedocs.io/zh_CN/latest/docs/WeBASE-Front/index.html

前置条件
1. ubuntu安装
2. 更新ubuntu系统
3. 如有需要ubuntu安装ssh
区块链网络部署
1. 安装依赖
sudo apt install -y openssl curl
1. 创建操作目录, 下载安装脚本
## 创建操作目录
cd ~ && mkdir -p fisco && cd fisco

## 下载脚本
curl -#LO <https://osp-1257653870.cos.ap-guangzhou.myqcloud.com/FISCO-BCOS/FISCO-BCOS/releases/v2.8.0/build_chain.sh> && chmod u+x build_chain.sh
1. 搭建单群组4节点联盟链
bash build_chain.sh -l 127.0.0.1:4 -p 30300,20200,8545
* 注意事项
    1. 该脚本会从https://github.com/FISCO-BCOS/FISCO-BCOS/releases/download/v2.8.0/fisco-bcos.tar.gz下载源文件，执行完毕后会自动删除&#x2028;
    2. 源码编译方式完整部署&#x2028;## 1.安装编译所需要的依赖
    3. sudo apt install -y g++ libssl-dev openssl cmake git build-essential autoconf texinfo flex patch bison libgmp-dev zlib1g-dev automake libtool
    4. ## 2.克隆源码
    5. git clone <https://gitee.com/FISCO-BCOS/FISCO-BCOS.git> -b master
    6. ## 3.编译，编译后的文件在 FISCO-BCOS/build/bin/fisco-bcos
    7. cd FISCO-BCOS
    8. mkdir -p build && cd build
    9. cmake ..
    10. # 高性能机器可添加-j4使用4核加速编译
    11. make -j2
    12. ## 4.使用build_chain.sh搭建联盟链网络
    13. bash build_chain.sh -l 127.0.0.1:4 -e bin/fisco-bcos
    14. &#x2028;
1. 启动联盟链
bash nodes/127.0.0.1/start_all.sh
1. 检查进程
ps -ef | grep -v grep | grep fisco-bcos
1. 检查日志输出
查看节点node0链接的节点数
tail -f nodes/127.0.0.1/node0/log/log*  | grep connected
检查是否在共识
tail -f nodes/127.0.0.1/node0/log/log*  | grep +++
控制台部署
1. 安装java并配置环境变量，openjdk安装目录为/usr/lib/jvm
## 安装jdk
sudo apt install -y default-jdk     （sudo apt install openjdk-8-jdk）

vim /etc/profile # 该处需要安装vim

## 在profile文件最后一行添加环境变量配置
export JAVA_HOME=/usr/lib/jvm/java-11-openjdk-amd64
export JRE_HOME=${JAVA_HOME}/jre 
export CLASSPATH=.:${JAVA_HOME}/lib:${JRE_HOME}/lib 
export PATH=${JAVA_HOME}/bin:$PATH

## 在终端加载环境变量
source /etc/profile
1. 获取控制台脚本安装文件,并执行脚本
cd ~/fisco && curl -LO <https://github.com/FISCO-BCOS/console/releases/download/v2.8.0/download_console.sh>
bash download_console.sh

## 源码下载地址https://osp-1257653870.cos.ap-guangzhou.myqcloud.com/FISCO-BCOS/console/releases/v2.8.0/console.tar.gz
1. 拷贝控制台配置文件
cp -n console/conf/config-example.toml console/conf/config.toml
1. 配置控制台证书
cp -r nodes/127.0.0.1/sdk/* console/conf/
1. 启动控制台
cd ~/fisco/console && bash start.sh
1. 用控制台获取信息（控制台中输入help方法可获取控制台使用帮助）
## 获取客户端版本
getNodeVersion

## 获取节点信息
getPeers
1. 部署内置HelloWorld合约，并记录下contract address信息
deploy HelloWorld 
1. 调用HelloWorld的get方法
## 此处合约地址是部署时的contract address
call HelloWorld 0x197c5a8ca0dd944d97c044d6a2ce3715c13d81ca get
1. 调用合约的set方法
call HelloWorld 0x197c5a8ca0dd944d97c044d6a2ce3715c13d81ca set "Hello, blockchain"
1. 查看块高
getBlockNumber
节点前置服务部署
1. 下载源码
git clone <https://gitee.com/WeBank/WeBASE-Front.git>
1. 编译代码
cd WeBASE-Front
## 需要下载https://services.gradle.org/distributions/gradle-6.6.1-bin.zip
chmod +x ./gradlew && ./gradlew build -x test
1. 修改配置
cd dist
cp -r conf_template conf
cd conf
## 将节点所在目录nodes/${ip}/sdk下的所有文件拷贝到当前conf目录
cp -r nodes/${ip}/sdk/* ./conf/
1. 修改配置文件（根据实际情况修改），WeBASE-Front/dist/conf目录下application.yml
vi application.yml
1. 服务启动停止 ,WeBASE-Front/dist目录下
启动: bash start.sh
停止: bash stop.sh
检查: bash status.sh
1. 访问控制台
http://{deployIP}:{frontPort}/WeBASE-Front
示例：<http://localhost:5002/WeBASE-Front>
节点管理服务
1. 安装mysql或者MariaDB
## 参考文档地址：<https://blog.csdn.net/weixx3/article/details/80782479>
######### mysql -uroot -p 需要加sudo的解决办法：<https://www.pianshen.com/article/889039421/>
#########  <https://www.jianshu.com/p/840fee52c0f6>
## 1.安装mysql依赖
sudo apt install mysql-server mysql-client
## 2.初始化配置
sudo mysql_secure_installation
## 3.检查mysql启动状态 
systemctl status mysql.service

## 或者
##1.安装MariaDB
sudo apt install mariadb-server
sudo mysql_secure_installation

1. 新建数据库
CREATE DATABASE IF NOT EXISTS webasenodemanager DEFAULT CHARSET utf8 COLLATE utf8_general_ci;
1. 下载源码
git clone <https://gitee.com/WeBank/WeBASE-Node-Manager.git>
1. 编译代码
cd WeBASE-Node-Manager
chmod +x ./gradlew && ./gradlew build -x test
1. 修改脚本配置
cd  dist/script

修改数据库名称：sed -i "s/webasenodemanager/webasenodemanager/g" webase.sh
修改数据库用户名：sed -i "s/defaultAccount/root/g" webase.sh
修改数据库密码：sed -i "s/defaultPassword/12345678/g" webase.sh
1. 拷贝dist目录下的配置文件
cp conf_template conf -r

## 修改配置信息
修改服务端口：sed -i "s/5001/5001/g" conf/application.yml
修改数据库IP：sed -i "s/127.0.0.1/127.0.0.1/g" conf/application.yml
修改数据库端口：sed -i "s/3306/3306/g" conf/application.yml
修改数据库名称：sed -i "s/webasenodemanager/webasenodemanager/g" conf/application.yml
修改数据库用户：sed -i "s/defaultAccount/root/g" conf/application.yml
修改数据库密码：sed -i "s/defaultPassword/Abc123!_/g" conf/application.yml
1. 启动停止
启动：bash start.sh
停止：bash stop.sh
检查：bash status.sh
1. 查看日志
全量日志：tail -f log/WeBASE-Node-Manager.log
错误日志：tail -f log/WeBASE-Node-Manager-error.log
WeBASE管理平台
1. 安装nginx
## 下载源码
wget <http://nginx.org/download/nginx-1.16.1.tar.gz>
## 安装依赖
sudo apt install libpcre3 libpcre3-dev zlib1g zlib1g-dev openssl libssl-dev

sudo cp nginx-1.16.1.tar.gz /usr/local/
cd /usr/local/
## 解压
sudo tar -zxvf  nginx-1.16.1.tar.gz
cd nginx-1.16.1
## 配置
sudo ./configure --prefix=/usr/local/nginx
## 编译安装
make
make install

## 测试是否安装成功
sudo /usr/local/nginx/sbin/nginx -t
1. nginx启动停止命令
/usr/local/nginx/sbin/nginx -s reload            # 重新载入配置文件
/usr/local/nginx/sbin/nginx -s reopen            # 重启 Nginx
/usr/local/nginx/sbin/nginx -s stop              # 停止 Nginx
ps -ef | grep nginx                              # 查看nginx进程
1. 下载源码
git clone <https://gitee.com/WeBank/WeBASE-Web.git>
1. 执行脚本,下载js文件
cd WeBASE-Web
bash ./get_solc_js.sh
1. WeBASE-Web同级目录下拉去移动端代码
git clone <https://gitee.com/WeBank/WeBASE-Web-Mobile.git>
1. 修改配置
# 修改服务器ip，也可以使用域名
sed -i "s%127.0.0.1%${your_ip}%g" docs/nginx.conf

# 修改WeBASE-Web服务端口（端口需要开通策略且不能被占用）
sed -i "s%5000%${your_port}%g" docs/nginx.conf

###### 绝对路径 ##########
# 修改静态文件路径（文件需要有权限访问）
sed -i "s%/data/WeBASE-Web/dist%${your_file_dir}%g" docs/nginx.conf

# 节点管理服务ip和端口
sed -i "s%10.0.0.1:5001%${your_node_manager}%g" docs/nginx.conf
1. 拷贝nginx配置文件并启动
cp -rf docs/nginx.conf /usr/local/nginx/conf
## 启动nginx
/usr/local/nginx/sbin/nginx
1. 访问页面
http://{deployIP}:{webPort}
示例：<http://127.0.0.1:5000>

### 被修改后的密码为：admin/Lenovo1234
签名服务器部署
1. 下载代码
git clone <https://gitee.com/WeBank/WeBASE-Sign.git>
1. 编译代码
cd WeBASE-Sign
chmod +x ./gradlew && ./gradlew build -x test
1. 数据库初始化
CREATE DATABASE IF NOT EXISTS webasesign DEFAULT CHARSET utf8 COLLATE utf8_general_ci;
1. 修改配置
cd dist
cp conf_template conf -r
## 根据实际情况修改数据库表名，ip，端口等
vi conf/application.yml
1. 服务启动停止
启动：bash start.sh
停止：bash stop.sh
检查：bash status.sh
1. 查看日志
全量日志：tail -f log/WeBASE-Sign.log
错误日志：tail -f log/WeBASE-Sign-error.log
链管理服务器部署
1. 下载代码
git clone <https://gitee.com/WeBank/WeBASE-Chain-Manager.git>
1. 编译
cd WeBASE-Chain-Manager
chmod +x ./gradlew && ./gradlew build -x test
1. 数据库初始化
CREATE DATABASE IF NOT EXISTS webasechainmanager DEFAULT CHARSET utf8 COLLATE utf8_general_ci;
1. 修改脚本配置
cd  dist/script

修改数据库名称：sed -i "s/webasechainmanager/${your_db_name}/g" webase.sh
修改数据库用户名：sed -i "s/defaultAccount/${your_db_account}/g" webase.sh
修改数据库密码：sed -i "s/defaultPassword/${your_db_password}/g" webase.sh

1. 执行数据库脚本
bash webase.sh 127.0.0.1 3306
1. 修改配置
cp conf_template conf -r

修改服务端口：sed -i "s/5005/${your_server_port}/g" conf/application.yml
修改数据库IP：sed -i "s/127.0.0.1/${your_db_ip}/g" conf/application.yml
修改数据库端口：sed -i "s/3306/${your_db_port}/g" conf/application.yml
修改数据库名称：sed -i "s/webasechainmanager/${your_db_name}/g" conf/application.yml
修改数据库用户：sed -i "s/defaultAccount/${your_db_account}/g" conf/application.yml
修改数据库密码：sed -i "s/defaultPassword/${your_db_password}/g" conf/application.yml
1. 服务启动停止
启动：bash start.sh
停止：bash stop.sh
检查：bash status.sh
1. 访问页面
http://{deployIP}:{deployPort}/WeBASE-Chain-Manager
示例：<http://localhost:5005/WeBASE-Chain-Manager>
数据统计服务部署
1. 下载代码
git clone <https://gitee.com/WeBank/WeBASE-Stat.git>
1. 编译
￼
cd WeBASE-Stat
chmod +x ./gradlew && ./gradlew build -x test
1. 数据库初始化
CREATE DATABASE IF NOT EXISTS webasestat DEFAULT CHARSET utf8 COLLATE utf8_general_ci;
1. 修改配置文件
cp conf_template conf -r

修改服务端口：sed -i "s/5008/${your_server_port}/g" conf/application.yml
修改数据库IP：sed -i "s/127.0.0.1/${your_db_ip}/g" conf/application.yml
修改数据库端口：sed -i "s/3306/${your_db_port}/g" conf/application.yml
修改数据库名称：sed -i "s/webasestat/${your_db_name}/g" conf/application.yml
修改数据库用户：sed -i "s/defaultAccount/${your_db_account}/g" conf/application.yml
修改数据库密码：sed -i "s/defaultPassword/${your_db_password}/g" conf/application.yml
1. 服务启动停止
启动：bash start.sh
停止：bash stop.sh
检查：bash status.sh
1. 访问页面
http://{deployIP}:{deployPort}/WeBASE-Stat/swagger-ui.html
示例：<http://localhost:5008/WeBASE-Stat/swagger-ui.html>
1. 查看日志
全量日志：tail -f log/WeBASE-Stat.log
错误日志：tail -f log/WeBASE-Stat-error.log
数据监管服务部署
一、WeBASE-Data-Collect搭建
1. 下载代码
git clone <https://gitee.com/WeBank/WeBASE-Data.git>
1. 编译代码
cd WeBASE-Data/WeBASE-Data-Collect
chmod +x ./gradlew && ./gradlew build -x test
1. 初始化数据库
CREATE DATABASE IF NOT EXISTS webasedata DEFAULT CHARSET utf8 COLLATE utf8_general_ci;
1. 修改脚本配置
cd  dist/script
修改数据库名称：sed -i "s/webasedata/${your_db_name}/g" webase.sh
修改数据库用户名：sed -i "s/defaultAccount/${your_db_account}/g" webase.sh
修改数据库密码：sed -i "s/defaultPassword/${your_db_password}/g" webase.sh
1. 运行脚本数据库
bash webase.sh 127.0.0.1 3306
1. 配置修改
cp conf_template conf -r

根据实际情况修改配置文件conf/applicationyml的参数
1. 服务启动停止
启动：bash start.sh
停止：bash stop.sh
检查：bash status.sh
1. 页面访问
http://{deployIP}:{deployPort}/WeBASE-Data-Collect
示例：<http://localhost:5009/WeBASE-Data-Collect>
二、WeBASE-Data-Fetcher搭建
1. 编译
cd WeBASE-Data/WeBASE-Data-Fetcher
chmod +x ./gradlew && ./gradlew build -x test
1. 修改配置
cd dist
cp conf_template conf -r
## 根据实际情况修改conf/applicationyml文件
1. 服务启动停止
启动：bash start.sh
停止：bash stop.sh
检查：bash status.sh
1. 访问
http://{deployIP}:{deployPort}/WeBASE-Data-Fetcher/swagger-ui.html
示例：<http://localhost:5010/WeBASE-Data-Fetcher/swagger-ui.html>
WeBASE-Data-Web搭建
1. 修改配置文件
cd WeBASE-Data-Web

## 在docs目录下有配置文件nginx.conf

# 修改服务器ip，也可以使用域名
sed -i "s%127.0.0.1%${your_ip}%g" docs/nginx.conf
# 修改WeBASE-Data-Web服务端口（端口需要开通策略且不能被占用）
sed -i "s%5200%${your_port}%g" docs/nginx.conf
# 修改静态文件路径（文件需要有权限访问）
sed -i "s%/data/WeBASE-Data-Web/dist%${your_file_dir}%g" docs/nginx.conf
# WeBASE-Data-Fetcher服务ip和端口
sed -i "s%10.0.0.1:5010%${your_fetcher}%g" docs/nginx.conf
1. 配置nginx
## 方式1：警告，前面一直配置过nginx服务器，不可采用该方法
cp -rf docs/nginx.conf /usr/local/nginx/conf

## 方式2：如果服务器已有nginx，可在原配置文件nginx.conf增加一个server
upstream data_server{
        server 10.0.0.1:5010; # WeBASE-Data-Fetcher服务ip和端口
    }
    server {
        listen       5200 default_server; # 前端端口（端口需要开通策略且不能被占用）
        server_name  127.0.0.1;           # 服务器ip，也可配置为域名
        location / {
                root   /WeBASE-Data/WeBASE-Data-Web/dist;   # 前端文件路径(对应修改文件需要有权限访问)
                index  index.html index.htm;
                try_files $uri $uri/ /index.html =404;
        }

        include /etc/nginx/default.d/*.conf;

        location /mgr {
                    proxy_pass    http://data_server/;    		
                    proxy_set_header		Host			 $host;
                    proxy_set_header		X-Real-IP		 $remote_addr;
                    proxy_set_header		X-Forwarded-For	 $proxy_add_x_forwarded_for;
        }
    }
1. 启动nginx
/usr/local/nginx/sbin/nginx # nginx在/usr/local目录下
1. 访问
http://{deployIP}:{webPort}
示例：<http://127.0.0.1:5200>
ubuntu重装系统后，ssh密钥无法使用
WARNING: REMOTE HOST IDENTIFICATION HAS CHANGED!
https://blog.csdn.net/xlgen157387/article/details/52669709



