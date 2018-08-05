
jar -cfM kontoRMI.zip *.*

javac -d . *.java

rmic	-d . ch.jmildner.rmi.kontoRMI.KontoImpl

jar -cf  kontoRMI.jar  ch\jmildner\rmi\kontoRMI\*.class 

call startServer

pause

call startClient

call clear

