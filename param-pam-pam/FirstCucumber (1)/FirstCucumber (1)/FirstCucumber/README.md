# Фреймворк АТ, стек Cucumber-junit4-selenium-Rest_assured

### <a href ="https://cucumber.io/docs/cucumber/">Ссылка на документацию (требуется подключение по VPN)</a> 

### Примеры запуска теста с помощью maven-команды (такую возможность дает плагин maven-surefire-plugin)->

````
mvn test -D"cucumber.filter.tags=@api_01"

mvn test -D"cucumber.filter.tags=@ui"

mvn test -D"cucumber.filter.tags=@ui and not @ui_02"

mvn test -D"cucumber.filter.tags=@ui_01 or @api_01"

````

## <u> Внимание! Для возможности запуска из maven-команды имя Runner-класса должно заканчиваться на "Test"!</u>

## Allure-отчеты

### Отчеты формируются при запуске тестов из maven-команды или при запуске тестов из класса RunnerTest

### Тесты можно запускать из фича-файла для отладки, но отчет не будет формироваться