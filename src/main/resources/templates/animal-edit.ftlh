<!DOCTYPE html>
<html>
<head>
    <title>SHELTER</title>
</head>
<body>
<h1><a href="/">SHELTER</a></h1>
<hr>
<h3>Редактирование животного ${animal.species}</h3>
<form action="/animal/edit" method="post">
    Изменить статус <br>
    <#list roles as role>
        <div>
            <label>
                <#if user.isEmployee()>
                    <input type="radio" name="animalsStatus" value="${role}" ${(animal.animalsStatus == role)?string("checked", "")}> ${role.getDescription()}<Br>
                <#else>
                        <#if animal.animalsStatus == "MISSING" && (role_index == 3 || role_index == 5) >
                            <input type="radio" name="animalsStatus" value="${role}" ${(animal.animalsStatus == role)?string("checked", "")}> ${role.getDescription()}<Br>
                        <#elseif animal.animalsStatus == "BOOKING" && (role_index == 5 || role_index == 6) >
                            <input type="radio" name="animalsStatus" value="${role}" ${(animal.animalsStatus == role)?string("checked", "")}> ${role.getDescription()}<Br>
                        </#if>
                </#if>
            </label>
        </div>
    </#list>

    Изменить описание <br>
    <textarea name="description"> ${animal.description} </textarea> <br>
    <input type="hidden" value="${animal.id}" name="animalId">
    <input type="hidden" value="animalsStatus" name="animalsStatus">
    <input type="hidden" value="${_csrf.token}" name="_csrf">
    <button type="submit">Сохранить</button>
</form>
</body>
</html>