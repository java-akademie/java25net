cls


del *.class
del *.jar
del *.zip

rem jar -cfM myrmi.zip *.*


javac -d . *.java


rmic -d . ch.jmildner.rmi.myrmi.KarteiImpl

jar -cmf  manifest.mf  myrmi.jar  ch\jmildner\rmi\myrmi\*.class

rd ch /s