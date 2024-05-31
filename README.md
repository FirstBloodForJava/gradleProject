# gradleProject
搭建gradle项目步骤
官网文档地址：https://docs.gradle.org/current/userguide/userguide.html

## Getting Started

### 1.Gradle核心内容

#### 1.Gradle介绍

![image-20240531134245746](http://47.101.155.205/image-20240531134245746.png)

1. Project：类似模块划分
2. Build Scripts：构建Project
3. Dependency Management：依赖管理
4. Tasks：(build.gradle)一个独立的工作单元，可以执行特定的操作，如编译代码、运行测试、打包应用程序等
5. Plugins：扩展gradle的功能，并可以选择性的向项目插入Tasks

##### project structure

![Gradle项目结构](http://47.101.155.205/image-20240531135218645.png)

1. gradle wrapper：gradle wrapper、gradle和gradle.bat
2. setting.gradle：项目关系，单个项目可以不需要，多个项目必须有
3. build.gradle构建脚本
4. src源码



##### build gradle

![image-20240531135718546](http://47.101.155.205/image-20240531135718546.png)



~~~bash
# 安装gradle
gradle build

# gradle wrapper
gradlew build 		# linux
gradlew.bat build 	# windows
~~~



#### 2.build.gradle

![image-20240531141337018](http://47.101.155.205/image-20240531141337018.png)








### 2.Gradle Tutorial

#### 1.命令行初始化项目

~~~bash
gradle init --type java-application  --dsl groovy

~~~

![image-20240531143344793](http://47.101.155.205/image-20240531143344793.png)

![image-20240531143356538](http://47.101.155.205/image-20240531143356538.png)



#### 2.gradle tasks

~~~bash
# 查询<project>的依赖信息
./gradlew.bat :<project>:dependencies


~~~













### 3.IDEA新建gradle项目

![image-20240531110355797](http://47.101.155.205/image-20240531110355797.png)

关联远程git仓库

![image-20240531111247750](http://47.101.155.205/image-20240531111247750.png)