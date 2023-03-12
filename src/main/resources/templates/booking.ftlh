<!DOCTYPE html>
<html>
<head>
    <title>SHELTER</title>
</head>
<body>
<h1><a href="/">SHELTER</a></h1><hr>


<br><br>Забронировать животное

<table border="1">
    <tr>
        <th>Тип</th>
        <th>Цвет</th>
        <th>Описание</th>
        <th>Подробная информация</th>
        <th>Забронировать</th>
    </tr>
    <#list animals as animal>
        <#if animal.animalsStatus == "IN_SHELTER">
        <tr>
            <th>${animal.species.getDescription()}</th>
            <th>${animal.colorAnimal.getDescription()}</th>
            <th>${animal.description}</th>
            <th>
                <a href="/animal/${animal.id}">Подробная информация</a>
            </th>
            <th><a href="/bookingimpl/${animal.id}">Забронировать</a></th>
        </tr>
        </#if>
    <#else>
        <h3>животных нет</h3>
    </#list>
</table>


</body>
</html>
