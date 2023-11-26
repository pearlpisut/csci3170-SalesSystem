comp:
	javac.exe Main.java database/*.java menu/*.java
run: 
	java.exe -cp ./mysql-jdbc.jar:. Main

clear:
	rm *.class
	rm database/*.class
	rm menu/*.class

all:
	make clear
	make comp
	make run