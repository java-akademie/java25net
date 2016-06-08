javac -d . *.java
rmic  -d . ch.java2.rmi.remote.KontoImpl

start rmiregistry
rem pause

start java ch.java2.rmi.remote.KontoReg
rem pause

java ch.java2.rmi.remote.KontoClient
pause
