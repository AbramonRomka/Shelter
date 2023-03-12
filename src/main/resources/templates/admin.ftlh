<!DOCTYPE html>
<html>
<head>
    <title>SHELTER</title>
</head>
<body>
<h1><a href="/">SHELTER</a></h1>
<hr>
<hr>
<h2>Панель администратора</h2>
<br><br>Списки пользователей
<a href="/registration">Создать пользователя</a>
<table border="1">
    <tr>
        <th>Email</th>
        <th>Номер телефона</th>
        <th>Активность</th>
        <th>Патрульный</th>
        <th>Роли</th>
        <th>Редактирование</th>
        <th>Подробная информация</th>
    </tr>
    <#list users as user>
    <tr>
        <th>${user.email}</th>
        <th>${user.phone}</th>
        <th><#if user.active>true<#else>false</#if></th>
        <th><#if user.patrol>да<#else>-</#if></th>
        <th><#list user.roles as role>${role.description}</#list></th>
        <th><a href="/admin/user/edit/${user.id}">Редактирование</a></th>
        <th><a href="/user/${user.id}">Подробная информация</a></th>
    </tr>
<#else>
    <h3>Пользователей нет</h3>
</#list>
</table>


<br><br>Список сотрудников
<#--<a href="/employee/create">Создать сотрудника</a>-->
    <table border="2">
        <tr>
            <th>Email</th>
            <th>Номер телефона</th>
            <th>Активность</th>
            <th>Патрульный</th>
            <th>Роли</th>
            <th>Редактирование</th>
            <th>Подробная информация</th>
        </tr>
        <#list employees as employee>
        <tr>
            <th>${employee.email}</th>
            <th>${employee.phone}</th>
            <th><#if employee.active>true<#else>false</#if></th>
            <th><#if employee.patrol>да<#else>-</#if></th>
            <th><#list employee.roles as role>${role.description}</#list></th>
            <th><a href="/admin/user/edit/${employee.id}">Редактирование</a></th>
            <th><a href="/user/${employee.id}">Подробная информация</a></th>
        </tr>
<#else>
    <h3>Сотрудников нет</h3>
</#list>
    </table>


<br><br>Список животных
<#--<a href="/animal/create">Создать животное</a>-->

    <table border="3">
        <tr>
            <th>Тип</th>
            <th>Цвет</th>
            <th>Описание</th>
            <th>Статус</th>
            <th>Редактирование</th>
            <th>Подробная информация</th>
        </tr>
        <#list animals as animal>
        <tr>
            <th>${animal.species.getDescription()}</th>
            <th>${animal.colorAnimal.getDescription()}</th>
            <th>${animal.description}</th>
            <th>${animal.animalsStatus.getDescription()}</th>
            <th><a href="/admin/animal/edit/${animal.id}">Редактирование</a></th>
            <th><a href="/animal/${animal.id}">Подробная информация</a></th>
        </tr>
<#else>
    <h3>животных нет</h3>
</#list>
    </table>

<br><br>Список приютов<br><br>
<form action="/admin/createshelter" method="post">
    Город: <input type="text" name="city"  required/><br><br>
    Телефон: <input type="number" name="phone"  required/><br><br>
    <input type="hidden" name="_csrf" value="${_csrf.token}">
    <input type="submit" value="Добавить"/>
</form><br><br>

<table border="5">
    <tr>
        <th>Город</th>
        <th>Номер телефона</th>
        <th>Статус</th>
        <th>Редактирование</th>
        <th>Подробная информация</th>
    </tr>
    <#list shelters as shelter>
        <tr>
            <th>${shelter.city}</th>
            <th>${shelter.phone}</th>
            <th>${shelter.status.getDescription()}</th>
            <th><a href="/admin/shelter/edit/${shelter.id}">Редактировние</a></th>
            <th><a href="/shelter/${shelter.id}">Подробная информация</a></th>
        </tr>
    <#else>
        <h3>Приютов нет</h3>
    </#list>
</table>

<br><br>Список клиник<br><br>
<form action="/admin/createclinic" method="post">
    <#if citieslenght == 0>
        Приютов не существует<br><br>
    <#else >
        Город: <label>
        <select name="city"  required>
            <#list shelters as city>
                <option
                        value ="${city.city}" >${city.city}
                </option>
            </#list>
        </select>
    </label><br><br>
    </#if>
    Телефон: <input type="number" name="phone"  required/><br><br>
    <input type="hidden" name="_csrf" value="${_csrf.token}">
    <input type="submit" value="Добавить"/>
</form><br><br>


    <table border="4">
        <tr>
            <th>Город</th>
            <th>Номер телефона</th>
            <th>Редактирование</th>
            <th>Подробная информация</th>
        </tr>
        <#list clinics as clinic>
        <tr>
            <th>${clinic.city}</th>
            <th>${clinic.phone}</th>
            <th>${clinic.status.getDescription()}</th>
            <th><a href="/admin/clinic/edit/${clinic.id}">Редактировние</a></th>
            <th><a href="/clinic/${clinic.id}">Подробная информация</a></th>
        </tr>
<#else>
    <h3>Клиник нет</h3>
</#list>
    </table>
</body>
</html>