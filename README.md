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













## IDEA新建gradle项目

![image-20240531110355797](http://47.101.155.205/image-20240531110355797.png)

关联远程git仓库

![image-20240531111247750](http://47.101.155.205/image-20240531111247750.png)



### SpringBoot项目

1. 指定SpringBoot的parent

~~~gradle
buildscript {
    repositories {
        mavenCentral()
        maven {
            url "https://maven.aliyun.com/repository/public" // 使用aliyun仓库镜像
        }
    }
    // 相当于maven中parent的spring-boot版本
    dependencies {
        classpath("org.springframework.boot:spring-boot-gradle-plugin:2.7.18")
    }
}

~~~

2. dependencies依赖引入

~~~gradle
dependencies {
        // 引入依赖的方式
        // implementation 声明在编译时需要但不需要暴露给使用此模块的编译器的依赖
        // compileOnly 声明只在编译时需要,但在运行时不需要的依赖,例如：lombok
        // runtimeOnly 声明只在运行时需要,但在编译时不需要的依赖,例如：JDBC驱动
        // testImplementation 声明只在测试编译和运行时需要的依赖
        // testCompileOnly 只在测试编译时需要的依赖,而在测试运行时不需要
        // testRuntimeOnly 测试运行时需要的依赖,而在测试编译时不需要
        // annotationProcessor 声明注解处理器的依赖,只在编译期间使用,生成代码或其他编译时工件
        // api 用于声明在编译时和运行时都需要的依赖,并且这些依赖对使用此模块的编译器是可见的
        // compileClasspath 配置用于定义编译时的类路径,但不会影响运行时的类路径
        // runtimeClasspath 配置用于定义运行时的类路径,但不会影响编译时的类路径
        // compile 在编译时和运行时都需要的依赖,并且这些依赖对使用此模块的编译器是可见的,已被implementation和api取代
        // runtime 声明了在运行时需要的依赖,已被runtimeOnly取代
        testImplementation 'org.junit.jupiter:junit-jupiter:5.10.2'
        compileOnly('org.projectlombok:lombok:1.18.32')
        annotationProcessor 'org.projectlombok:lombok:1.18.32'
        testCompileOnly 'org.projectlombok:lombok:1.18.32'
        testAnnotationProcessor 'org.projectlombok:lombok:1.18.32'
}

~~~

3. 项目构建build

![image-20240603132452815](http://47.101.155.205/image-20240603132452815.png)



#### bootJar构建

SpringBoot插件提供的一个任务，用于生成可执行的Jar文件。有一下要求

1. 这个模块中要有main方法
2. 引入springboot相关的插件

~~~gradle
// 这样引入，代码需要在bootJar的前面
apply plugin: 'org.springframework.boot'
apply plugin: 'io.spring.dependency-management'

bootJar {
    archiveBaseName = 'gradleSpring' // jar名称
    archiveVersion = '0.1.0' // jar版本
}

~~~



#### 自定义task

~~~gradle
// root项目下的build.gradle配置
def buildProjectName = "app"
// 在app项目构建之后
// 将app/build/libs/app.jar复制到build/dev、build/test、build/prod
// 将app/build/dist/springBoot.sh复制到build/dev、build/test、build/prod
// 将app/build/dist/springBoot.bat复制到build/dev、build/test、build/prod ...
// 将app/build/dist/application-dev.yml复制到复制到build/dev；app/build/dist/application-test.yml复制到复制到build/test ...
// 将上面所复制的文件,文件名-dev、-test、-prod的替换成空串
task buildJar(dependsOn: buildProjectName + ':build') doLast {
    ["build/dev", "build/test", "build/prod"].each { dest ->
        copy {
            from buildProjectName + "/build/libs/" + buildProjectName + ".jar"
            into dest
            from buildProjectName + "/dist/springBoot.sh"
            into dest
            from buildProjectName + "/dist/springBoot.bat"
            into dest
            from buildProjectName + "/dist/startGatewayJavaFx.bat"
            into dest
            from(buildProjectName + "/dist/application-".concat(dest.replace('build/', '')).concat(".yml"))
            into dest
            rename { String fileName ->
                fileName.replace('-'.concat(dest.replace('build/', '')), '')
            }
        }
    }
    file(buildProjectName + "/build").deleteDir()
}

~~~



~~~gradle
// root项目下的build.gradle配置
// 定义钩子函数，在任务完成之后执行
def jarName = "gradleProject.jar"
gradle.taskGraph.whenReady { taskGraph ->
    taskGraph.allTasks.each { task ->
        if (task.name == 'build') {
            task.doLast {
                // project.name == 'gradleProject' 这个放在外层 结果会是true
                if (project.name == 'gradleProject') {
                    println 'Build task finished, invoke task'
                    // 在这里添加你希望在构建后执行的代码

                    ["build/dev", "build/test", "build/prod"].each { dest ->
                        copy {
                            from "/build/libs/" + jarName
                            into dest
                            from "/dist/springBoot.sh"
                            into dest
                            from "/dist/springBoot.bat"
                            into dest
                            from "/dist/startGatewayJavaFx.bat"
                            into dest
                            from("/dist/application-".concat(dest.replace('build/', '')).concat(".yml"))
                            into dest
                            rename { String fileName ->
                                fileName.replace('-'.concat(dest.replace('build/', '')), '')
                            }
                        }
                    }
                }


            }
        }
    }
}

~~~

