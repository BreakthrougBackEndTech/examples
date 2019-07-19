参考storm 0.9.3 官方k8s集成 https://github.com/kubernetes/examples/tree/master/staging/storm

# Storm 2.0.0 k8s 集成

## docker images
##### 1. 手动下载apache-storm-2.0.0并解压到docker/base/

##### 2. 添加如下配置到 docker/base/apache-storm-2.0.0/conf/storm.yaml：
 ```console
        storm.zookeeper.servers:
             - "zookeeper"
        nimbus.seeds: ["nimbus"]
 ```
##### 3.在base目录下运行 
 ```sh
sudo docker image build -t storm-base .
 ```
         
##### 4.在nimbus目录下运行 
 ```sh
sudo docker image build -t storm-nimbus:v1 .
 ```
        
##### 5.在supervisor目录下运行 
 ```sh
sudo docker image build -t storm-supervisor:v1 .
 ```
        
#### 检查本地images
```console
REPOSITORY                                             TAG                 IMAGE ID            CREATED             SIZE
storm-supervisor                                       v1                  59eb79a20397        23 hours ago        570MB
storm-nimbus                                           v1                  8f1c45dcceb2        24 hours ago        570MB
storm-base                                             latest              5573c2e6cba2        24 hours ago        570MB
```

## k8s pod and service

#### 1. 创建zookeeper
```sh
kubectl create -f zookeeper.json
```

```sh
kubectl create -f zookeeper-service.json
```
    
#### 检查状态

```sh
$ kubectl get pods
NAME        READY     STATUS    RESTARTS   AGE
zookeeper   1/1       Running   0          43s
```

```console
$ kubectl get services
NAME              CLUSTER_IP       EXTERNAL_IP       PORT(S)       SELECTOR               AGE
zookeeper         10.254.139.141   <none>            2181/TCP      name=zookeeper         10m
kubernetes        10.0.0.2         <none>            443/TCP       <none>                 1d

$ echo ruok | nc 10.254.139.141 2181; echo
imok
```

#### 2. 创建nimbus

```sh
kubectl create -f storm-nimbus.json
```

```sh
kubectl create -f storm-nimbus-service.json
```

#### 3. 创建supervisor

```sh
kubectl create -f storm-worker-controller.yaml
```

#### 4. 检查集群状态
```sh
kubectl create -f storm-test.yaml
```  

```console
luffy@luffy-VirtualBox:~/storm/docker$ sudo kubectl get pod
[sudo] password for luffy: 
NAME                                       READY     STATUS    RESTARTS   AGE
nimbus                                     1/1       Running   0          2h
storm-test                                 1/1       Running   0          1h
storm-worker-controller-85f98cdbf5-j6nl6   1/1       Running   0          2h
zookeeper                                  1/1       Running   0          2h
```

```console
luffy@luffy-VirtualBox:~/storm/docker$ sudo kubectl exec -it storm-test -- /bin/bash
root@storm-test:/opt/apache-storm# ./bin/storm list
...
No topologies running.
```


#### 5. 在集群运行自己的任务

a. 拷贝文件到storm-test pod
```sh
sudo kubectl cp /mnt/myshare/storm-1.0-SNAPSHOT.jar storm-test:/opt/apache-storm/
```

b. 登录到storm-test并运行strom 命令
```console
luffy@luffy-VirtualBox:~/storm/docker$ sudo kubectl exec -it storm-test -- /bin/bash
root@storm-test:/opt/apache-storm# ./bin/storm jar storm-1.0-SNAPSHOT.jar Topology wordcount
...
root@storm-test:/opt/apache-storm# ./bin/storm list
Running: /usr/local/openjdk-8/bin/java -client -Ddaemon.name= -Dstorm.options= -Dstorm.home=/opt/apache-storm -Dstorm.log.dir=/opt/apache-storm/logs -Djava.library.path=/usr/local/lib:/opt/local/lib:/usr/lib:/usr/lib64 -Dstorm.conf.file= -cp /opt/apache-storm/*:/opt/apache-storm/lib/*:/opt/apache-storm/extlib/*:/opt/apache-storm/extlib-daemon/*:/opt/apache-storm/conf:/opt/apache-storm/bin org.apache.storm.command.ListTopologies
08:11:17.924 [main] WARN  o.a.s.v.ConfigValidation - task.heartbeat.frequency.secs is a deprecated config please see class org.apache.storm.Config.TASK_HEARTBEAT_FREQUENCY_SECS for more information.
08:11:21.924 [main] INFO  o.a.s.u.NimbusClient - Found leader nimbus : nimbus:6627
Topology_name        Status     Num_tasks  Num_workers  Uptime_secs  Topology_Id          Owner               
----------------------------------------------------------------------------------------
wordcount            ACTIVE     6          1            6017         wordcount-1-1563517861 root                
root@storm-test:/opt/apache-storm# 
```