del *.class
del *.jar
del *.zip

cls

jar -cfM waeh.zip *.*

set jc=jikes

%jc% -d . Waeh.java
%jc% -d . WaehImpl.java
%jc% -d . WaehReg.java
%jc% -d . WaehClient.java

rmic -d . ch.jmb.ueb13.WaehImpl

jar -cf  waeh.jar  ch\jmb\ueb13\*.class

del ch\jmb\ueb13\*.class
rd  ch\jmb\ueb13
rd  ch\jmb
rd  ch

pause
