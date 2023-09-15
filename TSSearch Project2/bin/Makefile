default: runTests

run: compile
	java BackendDeveloperTests
	java PairBD
	java PairInterface
	java PairReaderBD
	java PairReaderInterface
	java RedBlackTreeADT
	java RedBlackTreeBD
	java SortedCollectionInterface
	java StudentBD
	java StudentInterface
	java TSSBackendBD
	java TSSBackendInterface

compile: BackendDeveloperTests.class PairBD.class PairInterface.class PairReaderBD.class PairReaderInterface.class RedBlackTreeADT.class RedBlackTreeBD.class SortedCollectionInterface.class StudentBD.class StudentInterface.class TSSBackendBD.class TSSBackendInterface.class

runBackendDeveloperTests:
	javac -cp .:junit5.jar BackendDeveloperTests.java
	java -jar junit5.jar -cp . --select-class=BackendDeveloperTests
runFrontendDeveloperTests:FrontendDeveloperTests.java
	javac -cp .:junit5.jar FrontendDeveloperTests.java
	java -jar junit5.jar  -cp . --select-class=FrontendDeveloperTests

clean:
	rm *.class
