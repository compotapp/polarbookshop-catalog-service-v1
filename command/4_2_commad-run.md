Обычный запуск 

* java -jar target/catalog-service-0.0.1-SNAPSHOT.jar

Настройка приложения с помощью аргументов командной строки

* java -jar target/catalog-service-0.0.1-SNAPSHOT.jar --polar.greeting="Добро пожаловать в каталог из CLI"

Настройка приложения через свойства системы JVM

* java -Dpolar.greeting="Добро пожаловать в каталог от JVM" -jar target/catalog-service-0.0.1-SNAPSHOT.jar

Настройка приложения с помощью переменных среды

* POLAR_GREETING="Добро пожаловать в каталог ENV" java -jar target/catalog-service-0.0.1-SNAPSHOT.jar
* env:POLAR_GREETING="Добро пожаловать в каталог ENV"; java -jar target/catalog-service-0.0.1-SNAPSHOT.jar windows