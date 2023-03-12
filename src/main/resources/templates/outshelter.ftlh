<!DOCTYPE html>
<html>
<head>
    <title>SHELTER</title>
</head>
<body>
<h1><a href="/">SHELTER</a></h1>
<hr>

Передать животное клиенту

КАРОЧЕ ДЕЛАЕМ ЧЕРЕЗ ФОРМУ, КОТОРАЯ БУДЕТ ПЕРЕДАВАТЬ ТЕЛЕФОН КЛИЕНТА. НАВЕРН

<form action="/outshelterimpl" method="post">
    Номер нового владельца: <input type="text" name="phone" required/><br><br>
    <input type="hidden" name="animalId" value = "${animal.id}">
    <input type="hidden" name="_csrf" value="${_csrf.token}">
    <input type="submit" value="далее"/>
    </form>
</body>
</html>
