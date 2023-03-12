<!DOCTYPE html>
<html>
<head>
    <title>SHELTER</title>
</head>
<body>
<h1><a href="/">SHELTER</a></h1>
<hr>
<h4> Подробная информация о животном </h4>
<b> ID животного: </b> ${animal.getId()}<br>
<b> Тип животного: </b> ${animal.species.description}<br>
<b> Город приюта: </b>
<#if animal.shelter??>
    ${animal.shelter.city}
<#else> ${animal.clinic.city}
</#if>
<br>
<b> Номер приюта: </b>
<#if animal.shelter??>
    ${animal.shelter.phone}
<#else> ${animal.clinic.phone}
</#if>
<br>
<b> Цвет животного: </b> ${animal.colorAnimal.description}<br>
<b> Описание животного: </b> ${animal.description}<br>
<b> Статус животного: </b> ${animal.animalsStatus.description}<br>
<hr>
</form>
</body>
</html>