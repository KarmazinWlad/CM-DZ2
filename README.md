Разработать инструмент командной строки для визуализации графа
зависимостей, включая транзитивные зависимости. Сторонние средства для
получения зависимостей использовать нельзя.
Зависимости определяются по имени пакета языка Java (Maven). Для
описания графа зависимостей используется представление Graphviz.
Визуализатор должен выводить результат в виде сообщения об успешном
выполнении и сохранять граф в файле формата png.
Конфигурационный файл имеет формат json и содержит:
• Путь к программе для визуализации графов.
• Имя анализируемого пакета.
• Путь к файлу с изображением графа зависимостей.
• Максимальная глубина анализа зависимостей.
• URL-адрес репозитория.
Все функции визуализатора зависимостей должны быть покрыты тестами.

Резултат работы программы
![dependencies](https://github.com/user-attachments/assets/96d69f91-0432-4d57-a11f-d6facecf3ee6)

В качестве Тестирования запустим файлы и увидим вывод:
ConfigLoaderTest.java
![image](https://github.com/user-attachments/assets/61c42c5a-4c87-4252-89f6-55872d3c906c)

DependencyAnalyzerTest.java
![image](https://github.com/user-attachments/assets/3fea922b-7418-48c5-8943-43ebd7ba5c72)

GraphGeneratorTest.java
![image](https://github.com/user-attachments/assets/2cc83240-cc6f-41a4-b8f3-07829da7ec2d)

GraphVisualizerTest.java
![image](https://github.com/user-attachments/assets/5a225f3a-e367-4eae-b0ec-dd8028b198e0)

DependencyVisualizerIntegrationTest.java
![image](https://github.com/user-attachments/assets/a0b1e459-5336-44ea-a1c0-ae96cc6f7e33)
![image](https://github.com/user-attachments/assets/598ce60d-9794-47d6-8bd1-5da1078b4d83)
