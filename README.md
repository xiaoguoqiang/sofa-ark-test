# sofa-ark-test
本项目包含两个module：
 instrument-agent：作为instrument的agent jar包。
 app-one：将会打包为Ark 包，并在其启动时获取Ark包中instrument-agent.jar的位置，通过attach api，load到app-one运行的JVM上。
 
注意:IDEA 调试代码时，请使用jar application的方式调试
 
结论：由于Ark包为一个整体，因此无法load。
