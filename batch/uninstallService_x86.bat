@echo off

cd /d %~dp0
cd ..\

bin\daemon\prunsrv_x86.exe //DS//UsbLamp
pause