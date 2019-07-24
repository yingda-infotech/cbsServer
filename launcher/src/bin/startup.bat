@echo off
cd ..
for %%I in (dir ./lib/*.jar) do call ./bin/cpappend.bat ./lib/%%I
java -classpath "%CLASSPATH%;%JAR_PATH%" cn.com.git.cbs.launcher.CBSMain