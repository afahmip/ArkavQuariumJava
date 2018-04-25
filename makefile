JFLAGS = -g 
OUTFLAGS = -d
CLASSPATH = -cp
JC = javac 
JVM = java 
MAIN = src/Main.java
PATH_HASIL = "out/production/ArkavQuarium_Java"
TOOLS = src/tools/*.java
BINATANG = src/binatang/*.java
BENDA = src/benda/*.java
CONTROLLER = src/controller/*.java

.SUFFIXES: .java .class 
.java.class: 
	$(JC) $(JFLAGS) $(MAIN) $(TOOLS) $(BINATANG) $(CONTROLLER) $(BENDA) $(OUTFLAGS) $(PATH_HASIL)

run: 
	$(JVM) $(CLASSPATH) $(PATH_HASIL) Main

test:
	

clean: 
	$(RM) *.class