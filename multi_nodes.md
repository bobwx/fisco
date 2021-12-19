 **获取build_chain.sh脚本**  
curl -#LO https://github.com/FISCO-BCOS/FISCO-BCOS/releases/download/v2.7.2/build_chain.sh && chmod u+x build_chain.sh  
**如果因为网络问题导致长时间无法下载build_chain.sh脚本，请尝试 curl -#LO https://gitee.com/FISCO-BCOS/FISCO-BCOS/raw/master-2.0/tools/build_chain.sh && chmod u+x build_chain.sh

 **生成星形区块链系统配置文件** 

 

### 生成区块链配置文件ip_list  
cat > ipconf << EOF  
 空格分隔的参数分别表示如下含义：  
 ip:num: 物理机IP以及物理机上的节点数目  
 agency_name: 机构名称  
 group_list: 节点所属的群组列表，不同群组以逗号分隔  
127.0.0.1:2 agencyA 1,2,3  
127.0.0.1:2 agencyB 1  
127.0.0.1:2 agencyC 2  
127.0.0.1:2 agencyD 3  
EOF  
bash buildchain.sh -f ipconf -p 30300,20200,8545  


### 查看群组共识状态  
查看node0 group1是否正常共识（Ctrl+c退回命令行）  
tail -f node0/log/* | grep "g:1.*++"  

### 配置控制台  
#获取控制台  
`$curl -#LO https://github.com/FISCO-BCOS/console/releases/download/v2.7.2/download_console.sh  `  
bash download_console.sh  

#若因为网络问题导致长时间无法执行上面的命令，请尝试以下命令：  
`https://osp-1257653870.cos.ap-guangzhou.myqcloud.com/FISCO-BCOS/console/releases/v2.8.0/download_console.sh  `  
bash download_console.sh  
#进入控制台操作目录  
cd console  
#拷贝group2节点证书到控制台配置目录  
cp ~/fisco/nodes/127.0.0.1/sdk/* conf/  
#获取node0的channel_listen_port  
grep "channel_listen_port" ~/fisco/nodes/127.0.0.1/node*/config.ini  
#拷贝控制台配置
cp ~/fisco/console/conf/config-example.toml ~/fisco/console/conf/config.toml  

### 启动控制台  
bash ~/console/start.sh  

#... 向group2发交易...
#切换到group2
$[group:1]> switch 2

#向group3发交易，返回交易哈希表明交易部署成功  
$[group:3]> deploy HelloWorld  
#退出控制台
$quit  

#查看group1出块情况：有新区块产生  
$cat node0/log/* |grep "g:1.*Report"  

### 拷贝group2群组配置到node2  
cd ~/nodes/127.0.0.1
cp -r node0/conf/group.2.* node2/conf/  
cd node2  
bash stop.sh  
bash start.sh  
#获得node2节点ID  
cat node2/conf/node.nodeid(复制此id)
cd ~/console/  
bash start.sh  
switch 2  
#查看当前共识节点列表  
getSealerNumber  
#将node2加入到共识节点  
addSealer (粘贴node2节点id)  


 **_### 多组并行  _** 
mkdir -p ~/fisco && cd ~/fisco  
#获取build_chain.sh脚本  
curl -#LO https://github.com/FISCO-BCOS/FISCO-BCOS/releases/download/v2.7.2/build_chain.sh && chmod u+x build_chain.sh  

#若因为网络问题导致长时间无法执行上面的命令，请尝试以下命令：  
curl -#LO https://gitee.com/FISCO-BCOS/FISCO-BCOS/releases/download/v2.7.2/build_chain.sh && chmod u+x build_chain.sh  

#构建本机单群组四节点区块链  
bash build_chain.sh -l 127.0.0.1:4 -o multi_nodes -p 20000,20100,7545  

#进入节点目录  
cd ~/fisco/multi_nodes/127.0.0.1  
bash start_all.sh  

#查看进程情况  
ps aux | grep fisco-bcos  

### 将group2加入区块链  
#进入节点目录  
cd ~/fisco/multi_nodes/127.0.0.1  

#拷贝group1的配置  
cp node0/conf/group.1.genesis node0/conf/group.2.genesis  
cp node0/conf/group.1.ini node0/conf/group.2.ini  

#修改群组ID  
sed -i "s/id=1/id=2/g"  node0/conf/group.2.genesis  
cat node0/conf/group.2.genesis | grep "id"  
#已修改到    id=2  

#更新group.2.genesis文件中的共识节点列表，剔除已废弃的共识节点。  

#将配置拷贝到各个节点  
cp node0/conf/group.2.genesis node1/conf/group.2.genesis  
cp node0/conf/group.2.genesis node2/conf/group.2.genesis  
cp node0/conf/group.2.genesis node3/conf/group.2.genesis  
cp node0/conf/group.2.ini node1/conf/group.2.ini  
cp node0/conf/group.2.ini node2/conf/group.2.ini  
cp node0/conf/group.2.ini node3/conf/group.2.ini  

#重启各个节点  
bash stop_all.sh  
bash start_all.sh  

### 配置控制台  
#获取channel_port  
grep "channel_listen_port" multi_nodes/127.0.0.1/node0/config.ini  
multi_nodes/127.0.0.1/node0/config.ini:     _**channel_listen_port=20100**_   

#进入控制台目录  
cd console  
#拷贝节点证书  
cp ~/fisco/multi_nodes/127.0.0.1/sdk/* conf  

#拷贝控制台配置  
cp ~/fisco/console/conf/config-example.toml ~/fisco/console/conf/config.toml  

#修改控制台连接节点的端口为20100和20101  
sed -i 's/127.0.0.1:20200/127.0.0.1:20100/g' ~/fisco/console/conf/config.toml  
sed -i 's/127.0.0.1:20201/127.0.0.1:20101/g' ~/fisco/console/conf/config.toml   

