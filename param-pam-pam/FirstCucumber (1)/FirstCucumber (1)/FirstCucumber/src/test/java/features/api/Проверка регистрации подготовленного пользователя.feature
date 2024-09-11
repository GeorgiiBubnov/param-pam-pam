#language:ru

  @api_01 @api
  Функционал: Тестирование сервиса ReqRes api
    # Передача данных между шагами
    # предварительные шаги перед запуском сценариев (аналог @beforeEach Junit5)
    Предыстория:
    Дано Подготовить любого пользователя в списке сотрудников из отдела "AT" и сохранить как "Тестовый пользователь"

      Сценарий: регистрация пользователя и его поиск по фамилии в списке пользователей

        Когда добавляю пользователя "Тестовый пользователь" в сервисе ReqRes api и сохраняю id в сервисе как "Ответ"
        То при поиске пользователя "Ответ" в сервисе ReqRes api получаю ответ 404