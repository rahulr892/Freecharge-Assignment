# Freecharge-Assignment

1. Run "gradle runTests" from root folder via cmd line to run tests
2. Add to cart for myntra script is under src/test/java/com/freecharge/tests/myntra/AddToCart.java

3. Framework itself is thread-safe and can tests in parallel following page factory design pattern
4. Build tool is gradle and can be integrated with CI/CD
5. We can control which tests to run by managing data in Config.xlsx under src/main/java/com/freecharge/testdata
6. We can add test data to scenarios from TestData.xlsx file under src/main/java/com/freecharge/testdata
7. It can support multiple OS and browsers
8. Extent Reports are created under ExtentReports folder which supports multiple levels of loggings and screenshots for warning, fatal, failure, etc.
9. We can take fullpage srollable screenshots if needed to check UI
