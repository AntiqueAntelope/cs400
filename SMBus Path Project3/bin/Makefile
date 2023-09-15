default: compile
runFrontendDeveloperTests: 
	java -jar junit5.jar -cp . -c FrontendDeveloperTests
FrontendDeveloperTests.class: 
	javac -cp .:junit5.jar FrontendDeveloperTests.java

compileAll: BusPathInterface.java BusRouteReaderInterface.java BusStopFD.java SMBPBackendFD.java SMBPBackendInterface.java SMBPFrontendFD.java SMBPFrontendInterface.java TextUITester.java
	javac BusPathInterface.java
	javac BusRouteReaderInterface.java
	javac BusStopFD.java
	javac SMBPBackendFD.java
	javac SMBPBackendInterface.java
	javac SMBPFrontendFD.java
	javac SMBPFrontendInterface.java
	javac TextUITester.java

# targets to run tests
runTests: DataWranglerTests.class
	java -jar junit5.jar -cp . -c DataWranglerTests

DataWranglerTests.class: DataWranglerTests.java BusPathDW.class BusRouteReaderDW.class BusStopDW.class
	javac -cp .:junit5.jar DataWranglerTests.java

# targets to compile classes and interfaces
BusPathDW.class: BusPathDW.java BusPathInterface.class
	javac BusPathDW.java

BusPathInterface.class: BusPathInterface.java
	javac BusPathInterface.java

BusRouteReaderDW.class: BusRouteReaderDW.java BusRouteReaderInterface.class
	javac BusRouteReaderDW.java

BusRouteReaderInterface.class: BusRouteReaderInterface.java
	javac BusRouteReaderInterface.java

BusStopDW.class: BusStopDW.java BusStopInterface.class
	javac BusStopDW.java

BusStopInterface.class: BusStopInterface.java
	javac BusStopInterface.java

# clear out all compiled .class files and temporary~ files
runBackendDeveloperTests: BackendDeveloperTests.java
	javac -cp .:junit5.jar BackendDeveloperTests.java
	java -jar junit5.jar -cp . --select-class=BackendDeveloperTests	



compile:
	javac BaseGraph.java
	javac BusPathBD.java
	javac BusRouteReaderBD.java
	javac BusStopBD.java
	javac -cp .:junit5.jar DijkstraGraphBD.java
	javac SMBPBackendBD.java

runAlgorithmEngineerTests: AlgorithmEngineerTests.java
	javac -cp .:junit5.jar AlgorithmEngineerTests.java
	java -jar junit5.jar -cp . --select-class=AlgorithmEngineerTests

clean:
	rm *.class




