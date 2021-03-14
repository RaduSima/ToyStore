run: build
	java -cp ./class_files:./jars/* Main_Logic/Application

build:
	javac -cp .:./jars/* ./src/Main_Logic/*.java ./src/Exceptions/*.java ./src/Commands/*.java -d class_files
	
clean:
	rm ./class_files/* -r
