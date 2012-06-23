

# UsbLamp Utility Readme.txt

## UsbLamp Utilityとは？

ホスト側から制御可能なUSBランプデバイスを簡単に使用するためのユーティリティー
です。Windowsサービスまたはバッチ実行プログラムとして作動します。

継続的インテグレーションツールと組み合わせて、ビルドの失敗をランプで表示する
ために使用したり、サーバーのステータスを監視したりといった用途に使用できます。多分。

## 対応デバイス

今のところ、下記のデバイスに対応しています。

  * USB Webmail Notifier
DL100B Dream Cheeky Generic Controller
(VendorID: 0x1d34, ProductID: 0x0004)

上記以外のデバイスについては動作確認は行なっていません。


## インストール環境

Java6以上が導入されたWindowsXP以降のWindows環境。


## インストール方法
   * 1- libusb-win32を使用してデバイスを認識する
     * 1-1. USBデバイスを接続します
     * 1-2. 下記zipファイルを解凍します。

  >libusb-win32-bin-1.2.6.0.zip

     * 1-3. libusb-win32をインストールします。インストール方法は下記サイトを参照してください。

  > http://libusbjava.sourceforge.net/wp/?page_id=8


  * 2 Windowsサービスへ登録する
     * 2-1. 下記フォルダのInstallService\_x86 (32 bit JavaVM環境)または
InstallService\_amd64.bat(64 bit JavaVM環境)を実行します。バッチ実行後、サービ
ス一覧に"UsbLamp"が登録されることを確認してください。
     * 2-1. サービス"UsbLamp"を起動します


## ランプの制御

### ランプの色と発光パターンを制御する

下記のプロパティファイルを書き換えることでランプの点灯・消灯や色の変更を制御
します。UsbLampサービスは1秒ごとに このファイル内容をチェックし、ファイル内容
が更新されていれば、内容に合わせて点灯パターンを変えます。

ファイルはテキスト形式なので、様々なツール（Webスクレイピングスクリプトや
サーバ監視サービス等）と簡単に連携することが 可能です。

> UsbLamp\conf\flag.properties
  
以下、USBデバイスとしてUSB Webmail Notifier(DL100B Dream Cheeky Generic Controller)を使用した場合について説明します。
初期状態では、上記ファイルのデフォルト内容は下記のとおりとなっています。

> flag=0

  この状態でUSBLampサービスを起動していても、ランプは点灯しません。
> flag=1

この状態では赤色のランプが点灯します。flagの値と色の関係については、device.xmlを
  参照してください。

 * 1: 赤色
 * 2: 青色
 * 3: 紫色
 * 4: 緑色
 * 5: 黄緑色
 * 6: 水色
 * 7: 白色

カンマ(,)、コロン(:)、セミコロン(;)で数値を区切ることで、複数の色を連続点灯することが可能です。

例: 
> flag=1,2;4:7

上記の例では、ランプが赤->青->緑->白->消灯->赤->青....の順序で点灯します。

区切り文字の連続は消灯を表します。

例: 
> flag=1,2,,4

上記の例では、ランプが赤->青->消灯->緑->消灯->赤->青....の順序で点灯します。
数値として解釈できない文字が書かれた場合や、device.xmlで定義されていない数値が使用された場合には、ランプは光りません。

### 発光インターバルを制御する

下記のプロパティファイルを書き換えることでランプの点灯間隔を制御します。
UsbLampサービスは1秒ごとに このファイル内容をチェックし、ファイル内容
が更新されていれば、内容に合わせて点灯間隔を変えます。

ファイルはテキスト形式なので、様々なツール（Webスクレイピングスクリプトや
サーバ監視サービス等）と簡単に連携することが 可能です。

> UsbLamp\conf\interval.properties

初期状態では、上記ファイルのデフォルト内容は下記のとおりとなっています。

> interval=1000

intervalプロパティの値はミリ秒単位です。
この場合、ランプは一秒ごとに点灯を繰り返します。

> interval=500

上記のように設定すると0.5秒ごとに点灯します。
intervalプロパティの最低値は100です。これ以下の値を指定しても、100ミリ秒間隔で
点灯します。