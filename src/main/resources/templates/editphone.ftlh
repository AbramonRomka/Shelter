<!DOCTYPE html>
<html>
<head>
    <title>SHELTER</title>
</head>
<body>
<h1><a href="/">SHELTER</a></h1>
<hr>

<h2>Редактирование номера телефона</h2>
<form action="/editphoneimpl" method="post">
    Новый номер телефона: <input type="number" value="${user.phone}" name="phone"/><br><br>
    <input type="hidden" name="_csrf" value="${_csrf.token}">
    <input type="submit" value="Обновить номер"/>
</form>
</body>
</html>
