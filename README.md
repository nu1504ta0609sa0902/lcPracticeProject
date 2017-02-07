DON'T:
	1. Don't Use Implicit waits like : 
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		Use : SlowLoadableComponents OR WebDriverWait() where possible
		mvn test -Dtest=CucumberTestSuite
		OR
		mvn test -Dcucumber.options="--format json-pretty --glue classpath:cucumber/examples/java/helloworld src/test/resources"

Install Cucumber Editor : 
	1. Eclipse : https://raw.github.com/matthewpietal/Eclipse-Plugin-for-Cucumber/master/info.cukes.editor.ide.site_4.x/
	2. Or something similiar
	3. Or use Ruby Mine
	
test_nan.properties:
    -ea -Dspring.profiles.active=mhratest -Dcurrent.browser=gc -Dgenerate.report=false

    OR via properties file :

	1. Change Environment by changing : env.current=tst02
	3. Which browser to test on : env.driver.browser=firefox
	
STEPS:
	1. Group Them Accordingly
	2. Keep steps simple
	3. Use Page Objects 
	4. Don't add selenium code here

Pages: 
	Page Object : Keep it simple
	
	1. Structure it properly
	2. Get rid of implicitly wait
	3. Make use of SlowLoadableComponents
	4. Divide Page Into Sections:
		- Section1
		- Section2 etc
	5. Group your images for each page objects
	6. PUT SELENIUM CODE HERE
	
Features: 
	1. Keep scenarios simple
	2. Make use of scenario outline
	3. Make use of tags to group tests 
	