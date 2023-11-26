comp:
	javac Main.java database/*.java menu/*.java
run: 
	java -cp ./mysql-jdbc.jar:. Main

up:
	make clear
	make comp

clear:
	rm *.class
	rm database/*.class
	rm menu/*.class

all:
	make clear
	make comp
	make run