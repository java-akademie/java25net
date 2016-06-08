cls
set classpath=mydb.jar;%classpath%
start rmiregistry
java ch.jmildner.rmi.myRmiDB.MyDbReg

pause
