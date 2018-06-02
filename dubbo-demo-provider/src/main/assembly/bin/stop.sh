#!/bin/sh
cd `dirname $0`
BIN_DIR=`pwd`
cd ..

# 配置文件
CONFIG_FILE=@script.file.confg-file@
# 获取服务名称
SERVER_NAME=`sed '/@script.file.app-name@/!d;s/.*=//' $CONFIG_FILE | tr -d '\r'`

# 获取 pid
PIDS=`ps -ef | grep java | grep "$SERVER_NAME" |awk '{print $2}'`
if [ -z "$PIDS" ]; then
    echo "ERROR: The $SERVER_NAME does not started!"
    exit 1
fi

if [ "$1" != "skip" ]; then
    $BIN_DIR/dump.sh
fi

# kill 线程
echo -e "Stopping the $SERVER_NAME ...\c"
for PID in $PIDS ; do
    kill $PID > /dev/null 2>&1
done

COUNT=0
while [ $COUNT -lt 1 ]; do
    echo -e ".\c"
    sleep 1
    COUNT=1
    for PID in $PIDS ; do
        PID_EXIST=`ps -f -p $PID | grep java`
        if [ -n "$PID_EXIST" ]; then
            COUNT=0
            break
        fi
    done
done

echo "OK!"
echo "PID: $PIDS"