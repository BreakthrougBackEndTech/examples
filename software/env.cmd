echo start

echo Mysql START
start /d "C:\PF1GJ6B2-Data\zhegong\Desktop\D\Program Files\mysql\bin\" mysqld.exe --console
start /d "C:\PF1GJ6B2-Data\zhegong\Desktop\D\Program Files\mysql_slave\bin\" mysqld.exe --console

echo Redis START
start /d "C:\PF1GJ6B2-Data\zhegong\Desktop\D\Program Files\Redis-x64-3.2.100\" startup.bat

echo Zookeeper START
start /d "C:\PF1GJ6B2-Data\zhegong\Desktop\D\Program Files\zookeeper-cluster\zookeeper-3.4.12-0\bin" zkServer.cmd
start /d "C:\PF1GJ6B2-Data\zhegong\Desktop\D\Program Files\zookeeper-cluster\zookeeper-3.4.12-1\bin" zkServer.cmd
start /d "C:\PF1GJ6B2-Data\zhegong\Desktop\D\Program Files\zookeeper-cluster\zookeeper-3.4.12-2\bin" zkServer.cmd

