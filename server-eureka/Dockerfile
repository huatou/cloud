#使用jdk8作为基础镜像
FROM java:8
#指定作者
MAINTAINER zigar
#暴露容器的8088端口
#EXPOSE 7100
#将复制指定的xxl-job-admin-2.1.0.jar为容器中的job.jar，相当于拷贝到容器中取了个别名
ADD target/server-eureka.jar /server-eureka.jar
#ADD target/eureka8100.jar /eureka8100.jar
#ADD target/eureka9100.jar /eureka9100.jar
##创建一个新的容器并在新的容器中运行命令
#RUN bash -c 'touch /eureka7100.jar'
##设置时区
#ENV TZ=PRC
#RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone
##相当于在容器中用cmd命令执行jar包  指定外部配置文件
#ENTRYPOINT ["./startup.sh"]
CMD java -jar /server-eureka.jar