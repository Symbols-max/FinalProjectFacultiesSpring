<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Crystal Fitness</title>
    <link rel="stylesheet" type="text/css" href="css/main.css">
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@400;500;600;700&display=swap" rel="stylesheet">
</head>
<body>
<header id="header" class="header">
    <div class="container">
        <p class="logo">Crystal Fitness</p>
        <h1>
            Начни<br>меняться<br>уже<br>сегодня
        </h1>
        <ul class="circles">
            <li>
                <a href="#"><img src="../img/instagram.png" alt="instagram" class="instagram">
                </a>
            </li>
            <li>
                <a href="#"><img src="../img/youtube.png" alt="youtube" class="youtube"></a>
            </li>
            <li>
                <a href="#"><img src="../img/facebook.png" alt="facebook" class="facebook">
                </a>
            </li>
        </ul>
        <br><br>
    </div>
</header>

<section id="about" class="about">
    <div class="container">
        <p class="text1">
            Сеть фитнес клубов №1
        </p>
        <p class="text2">О НАС:</p>
        <p class="text3">
            - американское оборудование<br>
            - высококвалифицированный<br>
            тренерский состав<br>
            - лучшая ценовая политика<br>
            - место отдыха после тренировки
        </p>
        <p class="button"><a href="#contact" class="but">
            Потренять
        </a></p>
        <br>
    </div>
</section>

<section id="long" class="long">
    <div class="container">
        <div class="price">
            <p class="theme">Цены</p>
            <table class="prices">
                <tr>
                    <td>Вид абонемента:</td>
                    <td>Время посещения:</td>
                    <td class="green">Цена:</td>
                </tr>
                <tr>
                    <td> Дневной</td>
                    <td>С 8:00(9:00) до 17:00</td>
                    <td class="green">350 грн<br><pre class="small">(+30 грн допл. после 17:00)</pre>
                    </td>
                </tr>
                <tr>
                    <td>Безлим</td>
                    <td>С 8:00(9:00) до 22:00</td>
                    <td class="green">450 грн<br><pre class="small">(+персональная
            <br>тренировка с тренером)
            </pre>
                    </td>
                </tr>
                <tr>
                    <td>Школьный<br><pre class="small">(Студенческий)</pre>
                    </td>
                    <td>С 8:00(9:00) до 22:00</td>
                    <td class="green">350 грн</td>
                </tr>
                <tr>
                    <td> Выходного дня<br><pre class="small">(суббота и воскресенье)
            </pre></td>
                    <td>9:00 до 22:00</td>
                    <td class="green">199 грн</td>
                </tr>
                <tr>
                    <td>Тр.зал +групповые<br>занятия</td>
                    <td>С 8:00(9:00) до 22:00</td>
                    <td class="green">1000 грн</td>
                </tr>
            </table>
            <p class="personal">Персональные тренировки: от 1 - 150 грн, 5 - 750 грн, 10 - 1500 грн</p>
            <img class="promotion" src="../img/attention.png" alt="Promotion">
        </div>
        <hr color="#8FBB4B">
        <a class="link" href="/team"><pre>Команда</pre></a>
        <hr color="#8FBB4B">
        <a class="link" href="/gallery"><pre>Наши клубы</pre></a>
        <hr color="#8FBB4B">
        <a class="link" href="/news"><pre>Новости</pre></a>
        <hr color="#8FBB4B">
        <div class="contacts">
            <p class="theme" id="contact">Контакты</p>
            <p class="contactstext" >Не стесняйтесь!!! Укажите Ваше<br>имя и телефон, и мы свяжемся<br>с Вами для консультации.</p>
            <div class="forms">
                <form name="contact" method="post" action="contacts/add">
                    <input type="text"
                           class="singleform" name="name" placeholder="Укажите ваше имя">
                    <input type="tel" class="singleform" name="phone" placeholder="+380(99)999-99-99">
                    <br>
                    <input class="button2" type="submit" value="Отправить">
                </form>
            </div>

            <div class="bottom">
                <table class="adress">
                    <tr>
                        <td>
                            <p class="adres">ул. Н. Закревского 93 А</p></td>
                        <td>
                            <p class="number">тел: +38(098)97-11-113</p>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <p class="adres">ул. Вышгородская 45 А</p>
                        </td>
                        <td>
                            <p class="number">тел: +38(097)99-41-000</p>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <p class="adres">ул. Саперно-Слободская 10</p>
                        </td>
                        <td>
                            <p class="number">тел: +38(096)85-55-527</p>
                        </td>
                    </tr>
                </table>
            </div>
        </div>
    </div>
</section>
</body>
</html>
