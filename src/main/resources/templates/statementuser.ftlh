<!DOCTYPE html>
<html>
<head>
    <title>SHELTER</title>
</head>
<body>
<h1>создать заявление на утерю | <a href="/profile">Профиль</a> <br><br> <a href="/">На главную</a></h1>
<form action="/logout" method="POST">
    <input type="hidden" name="_csrf" value="${_csrf.token}">
    <input type="submit" value="Выйти"/>
</form>

<form action="/statementCreateEmployee" method="post">
    <#if user.isEmployee()>
        <input type="hidden" name="flag" value="true">
        Укажите пользователя
        Имя: <input type="text" name="name"/><br><br>
        Фамилия: <input type="text" name="surname"/><br><br>
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
        Телефон: <input type="number" name="phone"/><br><br>
        <input type="checkbox" name="patrol"> Патрульный <font
            color="red"><br><br>
        Отмечаясь патрульным, Вы даёте согласие на получение заявлений на поиск животных в Вашем городе <br>
        Обязательно заполните поле "эл. почта"</font><br><br>
        email: <input type="text" name="email"/><br><br>
        пароль: <input type="text" name="password"/><br><br>
    </#if>
    Укажите животное
    Тип животного: <select name="species">
        <option value="CAT">Кошка</option>
        <option value="DOG">Собака</option>
        <option value="PARROT">Птица</option>
        <option value="RAT">Мышь</option>
        <option value="RACCOON">Енот</option>
        <option value="LIZARD">Ящерица</option>
    </select><br><br>
    Цвет животного: <select name="colorAnimal">
        <option value="WHITE">Белый</option>
        <option value="BLACK">Черный</option>
        <option value="GRAY">Серый</option>
        <option value="MULTICOLOUR">Многоцветный</option>
    </select><br><br>
    Описание животного: <textarea name="description"></textarea> <br>
    <input type="hidden" name="flag" value="false">
    <input type="hidden" name="_csrf" value="${_csrf.token}">
    <input type="submit" value="Добавить заявление"/>
</form>
</body>
</html>