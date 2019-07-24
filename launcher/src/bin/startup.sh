#!/bin/sh
echo -------------------------------------------
echo start CBS server
echo -------------------------------------------

cd ..
for i in ./lib/*;
do CLASSPATH=$i:"$CLASSPATH";
done
export CLASSPATH=.:$CLASSPATH

java -cp $CLASSPATH cn.com.git.cbs.launcher.CBSMain