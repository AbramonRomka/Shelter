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

<form action="/inshelterimpl" method="post">
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
    Описание животного: <label>
        <textarea name="description"></textarea>
    </label> <br>
    Статус животного: <select name="animalsStatus">
        <#list animalStatus as status>
            <#if status_index == 0 || status_index == 2>
            <option value="${status}">${status.description}</option>
            </#if>
        </#list>
    </select>
    <br>
    <input type="hidden" name="_csrf" value="${_csrf.token}">
    <input type="submit" value="Добавить животное"/>
</form>
</body>
</html>
