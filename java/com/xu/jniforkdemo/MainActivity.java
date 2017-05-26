package com.xu.jniforkdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

/*这个demo相当于在监听一个程序的pid，然后做其他操作 注意这个项目JNI都没有配置不能运行
 *注意：fork子进程可以拿到c的进程，各种卫士只能拿到java列表。c进程java是获取不到的，分叉出来的子进程可以做某些事情
 * 最常用的推送的进程，消息推送
*
* ## am 命令
* am命令 :在adb shell里可以通过am命令进行一些操作 如启动activity Service 启动浏览器等等
* am命令的源码在Am.java中, 在adb shell里执行am命令实际上就是启动一个线程执Am.java的main方法,am命令后面带的参数都会当作运行时的参数传递到main函数中
* am命令可以用start子命令,并且带指定的参数
* 常见参数: -a: action  -d data   -t 表示传入的类型 -n 指定的组件名字
* 举例: 在adb shell中通过am命令打开网页
* am start --user 0 -a android.intent.action.VIEW -d http://www.baidu.com
* 通过am命令打开activity
* am start --user 0 -n   com.itheima.fork/com.itheima.fork.MainActivity
* (系统sdk版本>16 需要加上--user 0 , <16不需要加)

* execlp c语言中执行系统命令的函数
* execlp() 会从PATH环境变量所指的目录中查找符合参数file的文件找到后就执行该文件, 第二个参数开始就是执行这个文件的 args[0],args[1] 最后一个参数用(char*)NULL结束
* android开发中 execlp函数对应android的path路径为system/bin/目录
* 调用格式

		execlp("am", "am", "start", "--user","0","-a", "android.intent.action.VIEW", "-d", "http://www.baidu.com", (char *) NULL);


		execlp("am", "am", "start", "--user","0", "-n" , "com.itheima.cforktest/com.itheima.cforktest.MainActivity",(char *) NULL);

*
* */


public class MainActivity extends AppCompatActivity {
    static{
        System.loadLibrary("fork");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void fork(View v){
        cfork();
    }
    public native void cfork();
}
