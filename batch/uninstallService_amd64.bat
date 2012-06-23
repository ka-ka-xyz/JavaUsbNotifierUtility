@echo off

cd /d %~dp0
cd ..\

bin\daemon\prunsrv_amd64.exe //DS//UsbLamp
pause