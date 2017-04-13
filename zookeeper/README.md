# Zookeeper学习日记

### 基本概念

ZooKeeper是一个分布式的，开放源码的分布式应用程序协调服务，是Google的Chubby一个开源的实现，是Hadoop和Hbase的重要组件。
ZK是典型的分布式数据一致性的解决方案，分布式应用程序可以基于它实现诸如数据发布/订阅、负载均衡、命名服务、分布式协调/通知、
集群管理、Master选举、分布式锁和分布式队列等功能。
    
- 集群角色
       
       Leader：事务请求的唯一调度和处理者，保证集群事务处理的顺序性；集群内部各服务器的调度者。
       Follower：处理客户端非事务请求，转发事务请求给Leader服务器； 参与事务请求Proposal的投票； 参与Leader的选举投票。
       Observer：3.3.0版本开始引入。与Follower工作原理基本一致，对于非事务请求，可以进行独立的处理，对于事务请求，
       则会转发给Leader服务器。区别是Observer不参与任何形式的投票
       
- 会话(Session）

        session指的是客户端会话。一个客户端启动的时候会首先与服务器建立一个TCP长连接，从第一次建立长连接开始，
        客户端session生命周期也就开始了。在sessionTimout规定的时间内能够重新连接上集群中的任意一台服务器，该session仍然有效。

- 数据节点(ZNode)
        
        节点分为两类：构成集群的机器、数据模型中的数据单元
        zookeeper将所有数据存储在内存中，数据模型是一棵树 ZNode Tree，结构与unix的文件系统类似。
        数据节点分为持久节点、持久顺序节点、临时节点、临时顺序节点几种类型。
        临时节点的生命周期与客户端的session绑定，当session失效，该节点即被移除。
        每个数据节点ZNode，zookeeper都会为其维护一个stat数据结构，维护了id、time、version等信息
        

- 事件监听器(Watcher)
        
        事件监听，在指定节点上注册watcher，并且在一些特定事件触发的时候，zookeeper服务器端会将事件通知到订阅的客户端，
        该机制是zookeeper实现分布式协调服务的重要性。
 
- 版本 
   
        上文中提到的ZNode的stat数据结构中维护的version信息，记录了ZNode的三个数据版本，
        分别是version（当前ZNode版本）、cversion（当前ZNode子节点的版本）和aversion（当前ZNode的ACL版本）。
        这些版本信息保证分布式数据原子性操作

        

### Install

##### 单机安装    

    Zookeeper 的启动脚本在 bin 目录下，Linux 下的启动脚本是 zkServer.sh
    执行启动脚本之前，还有几个基本的配置项需要配置一下，Zookeeper的配置文件在 conf 目录下，这个目录下有 zoo_sample.cfg 和 log4j.properties，
    你需要做的就是将 zoo_sample.cfg 改名为 zoo.cfg，因为 Zookeeper 在启动时会找这个文件作为默认配置文件。
     
     tickTime=2000 
     dataDir=/data/zookeeper
     clientPort=2181 
     
    tickTime：这个时间是作为 Zookeeper 服务器之间或客户端与服务器之间维持心跳的时间间隔，也就是每个 tickTime 时间就会发送一个心跳。
    dataDir：顾名思义就是 Zookeeper 保存数据的目录，默认情况下，Zookeeper 将写数据的日志文件也保存在这个目录里。
    clientPort：这个端口就是客户端连接 Zookeeper 服务器的端口，Zookeeper 会监听这个端口，接受客户端的访问请求。
    
    通过 zkServer.sh start 命令就可以启动zk了

##### 集群安装： 
    initLimit=5 
    syncLimit=2 
    server.1=192.168.211.1:2888:3888 
    server.2=192.168.211.2:2888:3888 
    
    initLimit：用来配置Zookeeper 接受客户端（这里所说的客户端不是用户连接 Zookeeper 服务器的客户端，而是 Zookeeper 服务器集群中连接到 Leader 的 Follower 服务器）
    初始化连接时最长能忍受多少个心跳时间间隔数。当已经超过 5 个心跳的时间（也就是 tickTime）长度后 Zookeeper 服务器还没有收到客户端的返回信息，
    那么表明这个客户端连接失败。总的时间长度就是 5*2000=10 秒
    
    syncLimit：这个配置项标识 Leader 与 Follower 之间发送消息，请求和应答时间长度，最长不能超过多少个 tickTime 的时间长度，总的时间长度就是 2*2000=4 秒
    
    server.A=B：C：D：其中
    A 是一个数字，表示这个是第几号服务器；
    B 是这个服务器的 ip 地址；
    C 表示的是这个服务器与集群中的 Leader 服务器交换信息的端口；
    D 表示的是万一集群中的 Leader 服务器挂了，需要一个端口来重新进行选举，选出一个新的 Leader，而这个端口就是用来执行选举时服务器相互通信的端口。
    如果是伪集群的配置方式，由于 B 都是一样，所以不同的 Zookeeper 实例通信端口号不能一样，所以要给它们分配不同的端口号。
    除了修改 zoo.cfg 配置文件，集群模式下还要配置一个文件 myid，这个文件在 dataDir 目录下，这个文件里面就有一个数据就是 A 的值，Zookeeper 启动时会读取这个文件，拿到里面的数据与 zoo.cfg 里面的配置信息比较从而判断到底是那个 server。

### 简单操作

#### 基本操作命令

##### zkServer.sh
    
    1. 启动ZK服务:       sh bin/zkServer.sh start
    2. 查看ZK服务状态:    sh bin/zkServer.sh status
    3. 停止ZK服务:       sh bin/zkServer.sh stop
    4. 重启ZK服务:       sh bin/zkServer.sh restart
    
##### zkCli.sh
  
    1. 显示根目录下、文件：           ls / 使用 ls 命令来查看当前 ZooKeeper 中所包含的内容
    2. 显示根目录下、文件：           ls2 / 查看当前节点数据并能看到更新次数等数据
    3. 创建文件，并设置初始内容：      
         
         create [-s] [-e] path data  -s表示创建的结点是顺序结点  -e表示创建的结点是临时结点
         create /zk "test" 创建一个新的 znode节点“ zk ”以及与它关联的字符串
         
    4. 获取文件内容：                get /zk 确认 znode 是否包含我们所创建的字符串
    5. 修改文件内容：                set /zk "zkbak" 对 zk 所关联的字符串进行设置
    6. 删除文件：                   delete /zk 将刚才创建的 znode 删除
    7. 退出客户端：                 quit
    8. 帮助命令：                   help
    

### Java客户端Api 

#### ZooKeeper类
    ZooKeeper(String connectString, int sessionTimeout, Watcher watcher)
    
    connectString:   zookeeper server列表, 以逗号隔开. ZooKeeper对象初始化后, 将从server列表中选择一个server, 并尝试与其建立连接. 如果连接建立失败, 则会从列表的剩余项中选择一个server, 并再次尝试建立连接.
    sessionTimeout:  指定连接的超时时间.
    watcher:         事件回调接口.
    
    
###  Watch机制
    

        ZooKeeper 允许客户端向服务端注册一个 Watcher 监听，当服务端的一些指定事件触发了这个 Watcher，那么就会向指定客户端发送一个事件通知来实现分布式的通知功能。
        ZooKeeper 的 Watcher 机制主要包括客户端线程、客户端 WatchManager 和 ZooKeeper 服务器三部分。
        在具体工作流程上，简单地讲，客户端在向 ZooKeeper 服务器注册 Watcher 的同时，会将 Watcher 对象存储在客户端的 WatchManager 中。
        当 ZooKeeper 服务器端触发 Watcher 事件后，会向客户端发送通知，客户端线程从 WatchManager 中取出对应的 Watcher 对象来执行回调逻辑
        
        一个zk的节点可以被监控，包括这个目录中存储的数据的修改，子节点目录的变化，一旦变化可以通知设置监控的客户端，
        
   ![注册和通知流程图](https://www.ibm.com/developerworks/cn/opensource/os-cn-apache-zookeeper-watcher/img001.png "注册和通知流程图")
 
 
#### zookeeper机制的特点 
 
- 一次性的触发器（one-time trigger）  
  
  
        watch机制官方说明：一个Watch事件是一个一次性的触发器，当被设置了Watch的数据发生了改变的时候，则服务器将这个改变发送给设置了Watch的客户端，以便通知它们。


- 发送给客户端（Sent to the client）     
 
        
        这个表明了Watch的通知事件是从服务器发送给客户端的，是异步的，这就表明不同的客户端收到的Watch的时间可能不同，
        但是ZooKeeper有保证：当一个客户端在看到Watch事件之前是不会看到结点数据的变化的。
        例如：A=3，此时在上面设置了一次Watch，如果A突然变成4了，那么客户端会先收到Watch事件的通知，然后才会看到A=4。
 
        Zookeeper 客户端和服务端是通过 Socket 进行通信的，由于网络存在故障，所以监视事件很有可能不会成功地到达客户端，监视事件是异步发送至监视者的，
        Zookeeper 本身提供了保序性(ordering guarantee)：即客户端只有首先看到了监视事件后，才会感知到它所设置监视的 znode 发生了变化
        (a client will never see a change for which it has set a watch until it first sees the watch event).
        网络延迟或者其他因素可能导致不同的客户端在不同的时刻感知某一监视事件，但是不同的客户端所看到的一切具有一致的顺序。
     
- 被设置Watch的数据（The data for which the watch was set）
 
 
        数据监视和子节点监视(data watches and child watches) 
 
        getData() and exists() 设置数据监视，getChildren() 设置子节点监视。
        
        setData() 会触发设置在某一节点上所设置的数据监视(假定数据设置成功)，
        而一次成功的 create() 操作则会触发当前节点上所设置的数据监视以及父节点的子节点监视。
        一次成功的 delete() 操作将会触发当前节点的数据监视和子节点监视事件，同时也会触发该节点父节点的child watch。
 
 3.各种watch触发的情况总结
 
        可以注册watcher的方法：getData、exists、getChildren。
 
        可以触发watcher的方法：create、delete、setData。连接断开的情况下触发的watcher会丢失。
 
        一个Watcher实例是一个回调函数，被回调一次后就被移除了。如果还需要关注数据的变化，需要再次注册watcher。
 
        New ZooKeeper时注册的watcher叫default watcher，只对client的连接状态变化作出反应。   

### ZKClient && Curator
    

### 典型应用场景

#### 数据发布/订阅

#### 负载均衡

#### 命名服务

#### 分布式协调/通知

#### 集群管理

#### Master选举

#### 分布式锁

#### 分布式队列


    
     
  