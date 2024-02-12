JC = javac
JD = javadoc
JV = java
SRC = src/vlille/*.java src/vlille/decorator/*.java src/vlille/observer/*.java src/vlille/strategy/*.java src/vlille/visitor/*.java
TESTSRC = tests/vlille/*.java
JARSDIR = jars
JUNIT_JAR = $(JARSDIR)/junit-console.jar
CLASSDIR = classes
DOCSDIR = docs

compile-class:
	$(JC) -d $(CLASSDIR) $(SRC)

compile-docs:
	$(JD) -d $(DOCSDIR) $(SRC)

compile-test:
	$(JC) -d $(CLASSDIR) -cp $(CLASSDIR):$(JUNIT_JAR) $(TESTSRC)

clean:
	rm -rf $(CLASSDIR) $(DOCSDIR)

compile: compile-class compile-docs compile-test

launch:
	$(JV) -cp $(CLASSDIR) vlille.Main

start: compile launch

run-tests:
	java -cp $(CLASSDIR):$(JUNIT_JAR) org.junit.platform.console.ConsoleLauncher --scan-classpath
