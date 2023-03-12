<!DOCTYPE html>
<html>
<head>
    <title>SHELTER</title>
</head>
<body>
<h1><a href="/">SHELTER</a></h1>
<hr>

Редактировать профиль

<h2>Редактирование профиля</h2>
<form action="/editprofileimpl" method="post">
    Имя: <input type="text" value="${user.name}" name="name"/><br><br>
    Фамилия: <input type="text" value="${user.surname}" name="surname"/><br><br>

    Город: <label>
        <select name="city">
            <#list cities as city>
                <option
                ${(user.city == city.city)?string("selected value", "value")} ="${city.city}" >${city.city}
                </option>
            </#list>
        </select>
    </label><br><br>
    Телефон: ${user.phone} <a href="/editphone">изменить</a> <br><br>

    <input type="checkbox" name="patrol" ${(user.patrol==true)?string("checked","")}> Патрульный
    <font color="red"><br>
        Отмечаясь патрульным, Вы даёте согласие на получение заявлений на поиск животных в Вашем городе <br>
    Обязательно заполните поле "email"</font><br><br>
    Email: <input type="text" value="${user.email}" name="email"/><br><br>
    Пароль: <input type="password" name="password"/><br><br>
    <input type="hidden" name="_csrf" value="${_csrf.token}">
    <input type="submit" value="Обновить данные профиля"/>
</form>
</body>
</html>
