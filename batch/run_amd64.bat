@echo off

cd /d %~dp0
cd ..\
java -cp ".;.\conf;.\lib\*" -Djava.library.path=".\lib_amd64" -Djava.util.logging.config.file="%CD%\conf\logging.properties" jp.gr.java_conf.ka_ka_xyz.usblamp.daemon.UsbMain start