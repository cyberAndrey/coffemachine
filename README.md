Программа для работы кофеварки на SpringBoot.
Учет работы в PostgesSQL. Для разворачивания БД Flyway.
Тест запросов через SwaggerUI.

Машина 1 делает кофе 1, 2, 3
Машина 2 делает кофе 4, 5
Эндпойнты:
/machine/on/{id} - включить машину на выбор
/machine/off - отключить выбранную машину
/machine/pour/{id} - сварить кофе на выбор
/machine/refresh - обновить воду и кофе в машинах
/machine/info - информация о состоянии машин

Данные подключения бд в application.properties
Тест через Swagger по адресу http://localhost:8080/swagger-ui/index.html в разделе machine-controller