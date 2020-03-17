# Freecharge-Assignment

Run testng.xml file under src/test/resources as TestNG.xml
Add to cart for myntra script is under src/test/java/com/freecharge/tests/myntra/AddToCart.java

Framework itself is thread-safe and can tests in parallel following page factory design pattern
Build tool is gradle and can be integrated with CI/CD
We can control which tests to run by managing data in Config.xlsx under src/main/java/com/freecharge/testdata
We can add test data to scenarios from TestData.xlsx file under src/main/java/com/freecharge/testdata
It can support multiple OS and browsers
Extent Reports are created under ExtentReports folder which supports multiple levels of loggings and screenshots for warning,
fatal, failure
We can take fullpage srollable screenshots if needed to check UI
