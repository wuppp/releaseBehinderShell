# 编译

```shell
mvn clean package -DskipTests
```

# 使用

1. 首先查看机器进程，找到Tomcat或者Weblogic进程ID，如下为查找Tocmat进程ID
```shell
ps -el | grep org.apache.catalina.startup.Bootstrap
```

2. 运行卸载内存马程序
```shell
java -Xbootclasspath/a:$JAVA_HOME/lib/tools.jar -jar releaseBehinderShell-1.0-SNAPSHOT-jar-with-dependencies.jar [pid]
```

# 注意

1. 本工具只在Tomcat环境下进行测试通过，weblogic环境为进行测试。
2. 由于使用本工具导致的任何服务器崩溃等一系列问题，与本人无关。