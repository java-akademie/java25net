del *.class
del *.jar
del *.zip

set jc=javac

pause
cls

jar -cfM mydb.zip *.*

%jc% -d . MyDb.java
%jc% -d . MyDbImpl.java

rmic	-d . ch.jmildner.rmi.myRmiDB.MyDbImpl

%jc% -d . MyDbReg.java


%jc% -d . MyDbPanel.java 
%jc% -d . MyDbFrame.java 

set p=ch\jmildner\rmi\myRmiDB

jar -cf  mydb.jar  %p%\*.class
jar -cf  mydbc.jar %p%\MyDb.class %p%\MyDbF*.class %p%\MyDbP*.class %p%\MyDbImpl_Stub*.class  

rd  ch /s

pause

