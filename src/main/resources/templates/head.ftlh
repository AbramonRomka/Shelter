<!DOCTYPE html>
<html>
<head>
    <title>SHELTER</title>
</head>
<body>
<h1><a href="/">SHELTER</a></h1><hr>


<#if user.isAdmin()>
    <br>админ<br>
    <a href="/admin">панель администратора</a>
    <form action="/logout" method="POST">
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <input type="submit" value="Выйти"/>
    </form>
<#elseif user.isEmployee()>
    <br>сотрудник<br>

    <br> <a href="/statement">Подать заявление о пропаже</a> <br>
    <br> <a href="/inshelter">Принять животное в приют</a> <br>
    <br> <a href="/outshelter">Передать животное клиенту</a> <br>
<#--    если бронирует сотрудник, то он должен указать кто будущий пользователь?-->
<#--    Проводим проверку, чтобы животное обязательно было в приюте-->
    <br> <a href="/booking">Забронировать животное в приюте</a> <br>

    <form action="/logout" method="POST">
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <input type="submit" value="Выйти"/>
    </form>
    <a href="/profile">Профиль</a>
<#elseif user.phone??>
    <br>клиент<br>

    <br> <a href="/statement">Подать заявление о пропаже</a> <br>
    <br> <a href="/booking">Забронировать животное в приюте</a> <br>


    <form action="/logout" method="POST">
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <input type="submit" value="Выйти"/>
    </form>
    <a href="/profile">Профиль</a>
<#else >
    <br>гость<br>

    <br> <a href="/statement">Подать заявление о пропаже</a> <br>
    <a href="/login">Войти</a>
</#if>



<h3>
    <a href="/hello">Привет ADMIN</a><br>
    <a href="/hello1">Привет EMPLOYEE</a><br>
    <a href="/hello2">Привет CLIENT</a><br>
</h3>
</body>
</html>