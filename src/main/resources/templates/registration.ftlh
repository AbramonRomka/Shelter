<!DOCTYPE html>
<html>
<head>
    <title>Shelter</title>
</head>
<body>
<h1><a href="/">SHELTER</a></h1>
<hr>
<h4>Регистрация</h4>
<form action="/registration" method="post">
    Имя: <input type="text" name="name" required/><br><br>
    Фамилия: <input type="text" name="surname" required/><br><br>
    <#if citieslenght == 0>
        Приютов не существует<br><br>
    <#else >
        Город: <select name="city">
        <#list cities as city>
            <option value="${city.city}">${city.city}</option>
        </#list>
    </select>
        <br><br>
    </#if>
        <input type="checkbox" name="patrol"> Патрульный <font
                color="red"><br>
            Отмечаясь патрульным, Вы даёте согласие на получение заявлений на поиск животных в Вашем городе <br>
            Обязательно заполните поле "email"</font><br><br>
        Email: <input type="text" name="email"/><br><br>
        Телефон: <input type="number" name="phone" required/><br><br>
        пароль: <input type="password" name="password" required/><br><br>
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <input type="submit" value="Зарегистрироваться"/>
</form>

</body>
</html>