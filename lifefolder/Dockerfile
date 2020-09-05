#基于centos
FROM centos
#作者
MAINTAINER lingz
#复制新文件或目录，并将它们添加到容器的文件系统中
COPY ./target/*.jar /jar/
#运行指定命令
RUN java -server -Xms512M -Xmx1024M -Xmn512M -XX:PermSize=512M \
             -Xss20M -XX:+DisableExplicitGC -XX:+UseConcMarkSweepGC \
             -XX:+CMSParallelRemarkEnabled -XX:+UseCMSCompactAtFullCollection  \
             -XX:LargePageSizeInBytes=128m -XX:+UseFastAccessorMethods  \
             -XX:+UseCMSInitiatingOccupancyOnly -XX:CMSInitiatingOccupancyFraction=70 \
             -Dfile.encoding=UTF-8  \
             -jar /jar/lifefolder-1.0-SNAPSHOT.jar
~                               
#指定该容器端口号
EXPOSE 8090
