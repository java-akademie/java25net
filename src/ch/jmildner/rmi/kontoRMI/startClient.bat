
set classpath=kontoRMI.jar;%classpath%

java ch.jmildner.rmi.kontoRMI.KontoClient1 192.168.0.100
pause
java ch.jmildner.rmi.kontoRMI.KontoClient2 192.168.0.100
pause
java ch.jmildner.rmi.kontoRMI.KontoClient1 192.168.0.100
pause
java ch.jmildner.rmi.kontoRMI.KontoClient2 192.168.0.100
pause
