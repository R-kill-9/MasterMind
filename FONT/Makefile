JAVAC=javac 
JAVA=java
LIB_PATH=lib/
CLASSPATH=$(LIB_PATH)junit-4.13.1.jar
PACKAGE=main.domain.tests
PACKAGE2=main.domaincontrollers.tests
.SUFFIXES: .java .class
.java.class:
	$(JAVAC) $*.java

DOMAIN_DIR=main/domain/
DOMAINCONTROLLERS_DIR=main/domaincontrollers/
DOMAINDRIVERS_DIR=main/drivers/
DOMAIN_TESTS_DIR=main/domain/tests/
DOMAINCONTROLLERS_TESTS_DIR=main/domaincontrollers/tests/
PRESENTATION_DIR=main/presentation/



DOMAIN_SRCS=$(wildcard $(DOMAIN_DIR)*.java)
DOMAINCONTROLLERS_SRCS=$(wildcard $(DOMAINCONTROLLERS_DIR)*.java)
DOMAINDRIVERS_SRCS=$(wildcard $(DOMAINDRIVERS_DIR)*.java)
DOMAIN_TESTS_SRCS=$(wildcard $(DOMAIN_TESTS_DIR)*.java)
DOMAINCONTROLLERS_TESTS_SRCS=$(wildcard $(DOMAINCONTROLLERS_TESTS_DIR)*.java)
PRESENTATION_SRCS=$(wildcard $(PRESENTATION_DIR)*.java)

DOMAIN_TESTS_test=$(basename $(notdir $(wildcard ./main/domain/tests/*.java)))
DOMAINCONTROLLERS_TESTS_test=$(basename $(notdir $(wildcard ./main/domaincontrollers/tests/*.java)))



DOMAIN_CLASSES=$(DOMAIN_SRCS:.java=.class)
DOMAINCONTROLLERS_CLASSES=$(DOMAINCONTROLLERS_SRCS:.java=.class)
DOMAINDRIVERS_CLASSES=$(DOMAINDRIVERS_SRCS:.java=.class)
DOMAIN_TESTS_CLASSES=$(DOMAIN_TESTS_SRCS:.java=.class)
DOMAINCONTROLLERS_TESTS_CLASSES=$(DOMAINCONTROLLERS_TESTS_SRCS:.java=.class)
PRESENTATION_CLASSES=$(PRESENTATION_SRCS:.java=.class)



CLASSES=$(DOMAIN_CLASSES) $(DOMAINCONTROLLERS_CLASSES) $(DOMAINDRIVERS_CLASSES) $(PRESENTATION_CLASSES)

JAR_FILE=mastermind.jar

$(info $(PACKAGE).$(DOMAIN_TESTS_test))

default: classes

classes: $(CLASSES)

run_driver: classes
	$(JAVA) main/drivers/DriverDomain

test_classes: $(CLASSES)
	$(JAVAC) -cp lib/junit-4.13.1.jar::main/domain/::main/domaincontrollers/  $(DOMAIN_TESTS_SRCS) $(DOMAINCONTROLLERS_TESTS_SRCS)

run_test_domain: test_classes
	@for test in $(DOMAIN_TESTS_test); do \
		echo "Haciendo el test de $$test"; \
		$(JAVA) -cp lib/junit-4.13.1.jar::lib/hamcrest-core-1.3.jar::main/domain/tests/ org.junit.runner.JUnitCore $(PACKAGE).$$test; \
	done	
run_test_domainControllers: test_classes
	@for test in $(DOMAINCONTROLLERS_TESTS_test); do \
		echo "Haciendo el test de $$test"; \
		$(JAVA) -cp lib/junit-4.13.1.jar::lib/hamcrest-core-1.3.jar::main/domaincontrollers/tests/ org.junit.runner.JUnitCore $(PACKAGE2).$$test; \
	done

$(JAR_FILE): classes
	jar cfe $(JAR_FILE) main.presentation.LoginScreen main/presentation/*.class main/domain/*.class main/domaincontrollers/*.class main/persistence/*.class

jar: $(JAR_FILE)

run_jar: jar
	java -jar $(JAR_FILE)

clean:
	rm -f main/persistence/*.class
	rm -f main/presentation/*.class
	rm -f main/domain/*.class
	rm -f main/domaincontrollers/*.class
	rm -f main/drivers/*.class
	rm -f main/domain/tests/*.class
	rm -f main/domaincontrollers/tests/*.class
	rm -f $(JAR_FILE)
	