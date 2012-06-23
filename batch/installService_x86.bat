@echo off

cd /d %~dp0
cd ..\

bin\daemon\prunsrv_x86.exe //IS//UsbLamp --DisplayName="UsbLamp" --Description="Usb-Lamp Service" --Classpath="%CD%;%CD%\conf;%CD%\lib\*" --LogPath="%CD%\logs" --StdOutput=auto --StdError=auto --StartPath="%CD%" --StartClass="jp.gr.java_conf.ka_ka_xyz.usblamp.daemon.UsbMain" --StartMode=jvm --StartParams=start --StopClass="jp.gr.java_conf.ka_ka_xyz.usblamp.daemon.UsbMain" --StopMode=jvm  --StopParams=stop --JvmOptions=-Djava.library.path="%CD%\lib_win32" ++JvmOptions=-Djava.util.logging.config.file="%CD%\conf\logging.properties"
pause