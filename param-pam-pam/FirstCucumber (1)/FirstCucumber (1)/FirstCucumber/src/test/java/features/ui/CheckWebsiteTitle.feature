#language:en
Feature: Проверка возможностей UI

  # Базовый тест с использованием Selenium
  @ui_01 @ui @correct
  Scenario: Тайтл верен
    Given перехожу на сайт 'https://bellintegrator.ru/'
    Then тайтл верен 'Bell Integrator'
