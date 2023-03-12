<!DOCTYPE html>
<html>
<head>
    <title>SHELTER</title>
</head>
<body>
<form action="/transfer" method="post">
<h1><a href="/">SHELTER</a></h1>
<hr>
<h4> Передаём животное: </h4>
<b> ID животного: </b> ${animal.getId()}<br>
<b> Тип животного: </b> ${animal.species.description}<br>
<b> Город приюта: </b>${animal.shelter.city}<br>
<b> Номер приюта: </b>${animal.shelter.phone}<br>
<b> Цвет животного: </b> ${animal.colorAnimal.description}<br>
<b> Описание животного: </b> ${animal.description}<br>
<b> Статус животного: </b> ${animal.animalsStatus.description}<br><br>

<h4> Владельцу: </h4>
<b> Имя: </b> ${user.name}<br>
<b> Фамилия: </b> ${user.surname}<br>
<b> Город: </b> ${user.city}<br>
<b> Телефон: </b> ${user.phone}<br>
<b> email: </b> ${user.email}<br>
<hr>
    <input type="hidden" name="animalId" value = "${animal.id}">
    <input type="hidden" name="userId" value = "${user.id}">
    <input type="hidden" name="_csrf" value="${_csrf.token}">
    <input type="submit" value="подтвердить"/>
</form>
<form action="/profile" target="_blank">
    <button>Отмена</button></form>
</body>
</html>