[![Build status](https://ci.appveyor.com/api/projects/status/bpftet32q30a01st?svg=true)](https://ci.appveyor.com/project/Anasstaisha/selenide-debitcarddelivery)


# Задача №1: заказ доставки карты

Вам необходимо автоматизировать тестирование формы заказа доставки карты:

![image](https://github.com/netology-code/aqa-homeworks/raw/master/selenide/pic/order.png)

Требования к содержимому полей:

1. Город — [один из административных центров субъектов РФ.](https://ru.wikipedia.org/wiki/%D0%90%D0%B4%D0%BC%D0%B8%D0%BD%D0%B8%D1%81%D1%82%D1%80%D0%B0%D1%82%D0%B8%D0%B2%D0%BD%D1%8B%D0%B5_%D1%86%D0%B5%D0%BD%D1%82%D1%80%D1%8B_%D1%81%D1%83%D0%B1%D1%8A%D0%B5%D0%BA%D1%82%D0%BE%D0%B2_%D0%A0%D0%BE%D1%81%D1%81%D0%B8%D0%B9%D1%81%D0%BA%D0%BE%D0%B9_%D0%A4%D0%B5%D0%B4%D0%B5%D1%80%D0%B0%D1%86%D0%B8%D0%B8)
2. Дата — не ранее трёх дней с текущей даты.
3. В поле фамилии и имени разрешены только русские буквы, дефисы и пробелы.
4. В поле телефона — только 11 цифр, символ + на первом месте.
5. Флажок согласия должен быть выставлен.

Тестируемая функциональность: отправка формы.

Поля «Город» и «Дата» заполняются через прямой ввод значений без использования выбора из выпадающего списка и всплывающего календаря.

Условия: если все поля заполнены корректно, то форма переходит в состояние загрузки:

![image](https://github.com/netology-code/aqa-homeworks/raw/master/selenide/pic/loading.png)

Важно: состояние загрузки не должно длиться более 15 секунд.

После успешной отправки формы появится всплывающее окно об успешном завершении бронирования:
![image](https://github.com/netology-code/aqa-homeworks/raw/master/selenide/pic/popup.png)
