del *.class

javac -d . MyInterface.java
javac -d . MyImpl.java
rmic  -d . ch.minerva.MyImpl
javac -d . MyClient.java
javac -d . MyReg.java
start rmiregistry
pause
start java ch.minerva.MyReg
pause
java ch.minerva.MyClient
pause