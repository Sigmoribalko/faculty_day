#!/bin/bash

echo "Компиляция Kotlin архиватора..."
kotlinc src/main/kotlin/CustomArchiver.kt -include-runtime -d CustomArchiver.jar

if [ $? -eq 0 ]; then
    echo "Компиляция успешна. Запуск программы..."
    echo "========================================"
    java -jar CustomArchiver.jar
else
    echo "Ошибка компиляции!"
fi