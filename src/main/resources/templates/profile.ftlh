<!DOCTYPE html>
<html>
<head>
    <title>SHELTER</title>
</head>
<body>
<h1>Страница профиля пользователя ${user.name} | <a href="/">На главную</a>
</h1>
<form action="/logout" method="POST">
    <input type="hidden" name="_csrf" value="${_csrf.token}">
    <input type="submit" value="Выйти"/>
</form>
<hr>


<br> <a href="/editprofile">редактировать профиль</a> <br>


<#if user.isEmployee()>
    <h4> Все животные приюта </h4>
    <table border="1">
        <tr>
            <th>Тип животного</th>
            <th>Город приюта</th>
            <th>Номер приюта</th>
            <th>Цвет животного</th>
            <th>Описание животного</th>
            <th>Статус животного</th>
            <th>Владелец</th>
        </tr>
        <#list shelteranimals as animal>
            <#if animal.animalsStatus != "BOOKING">
                <tr>
                    <th>${animal.species.getDescription()}</th>
                    <th>${animal.shelter.city}</th>
                    <th>${animal.shelter.phone}</th>
                    <th>${animal.colorAnimal.getDescription()}</th>
                    <th>${animal.description}</th>
                    <th>${animal.animalsStatus.getDescription()}</th>
                    <th><#if animal.user??>
                    <a href="/user/info/${animal.user.id}">${animal.user.phone}</a>
                    <#else>
                    —
                    </#if>
                    </th>
                    <th><a href="/animal/edit/${animal.id}">Редактирование</a></th>
                    <th><a href="/animal/info/${animal.id}">Подробная информация</a></th>
                    <#if animal.animalsStatus == "IN_SHELTER">
                    <th><a href="/outshelter/${animal.id}">Отдать животное</a></th>
                    </#if>
                </tr>
            </#if>
        <#else>
            <h3>Животных нет</h3>
        </#list>
    </table>
        <h4> Все животные клиники </h4>
        <table border="1">
            <tr>
                <th>Тип животного</th>
                <th>Город приюта</th>
                <th>Номер приюта</th>
                <th>Цвет животного</th>
                <th>Описание животного</th>
                <th>Статус животного</th>
            </tr>
            <#list clinicanimals as animal>
                <#if animal.animalsStatus != "BOOKING">
                    <tr>
                        <th>${animal.species.getDescription()}</th>
                        <th>${animal.clinic.city}</th>
                        <th>${animal.clinic.phone}</th>
                        <th>${animal.colorAnimal.getDescription()}</th>
                        <th>${animal.description}</th>
                        <th>${animal.animalsStatus.getDescription()}</th>
                        <th><a href="/animal/edit/${animal.id}">Редактирование</a></th>
                        <th><a href="/animal/info/${animal.id}">Подробная информация</a></th>
                    </tr>
                </#if>
            <#else>
                <h3>Животных в клинике нет / или клиники не существует</h3>
            </#list>
        </table>







    <h4> Забронированные животные приюта </h4>
    <table border="1">
        <tr>
            <th>Тип животного</th>
            <th>Город приюта</th>
            <th>Номер приюта</th>
            <th>Цвет животного</th>
            <th>Описание животного</th>
            <th>Статус животного</th>
            <th>Кто забронировал</th>
        </tr>
        <#list shelteranimals as animal>
            <#if animal.animalsStatus == "BOOKING">
                <tr>
                    <th>${animal.species.getDescription()}</th>
                    <th>${animal.shelter.city}</th>
                    <th>${animal.shelter.phone}</th>
                    <th>${animal.colorAnimal.getDescription()}</th>
                    <th>${animal.description}</th>
                    <th>${animal.animalsStatus.getDescription()}</th>
                    <th><a href="/animal/edit/${animal.id}">Редактирование</a></th>
                    <th><a href="/animal/info/${animal.id}">Подробная информация</a></th>
                    <th><a href="/user/info/${animal.userId}">Информация о брони</a></th>
                    <th><a href="/outshelter/${animal.id}">Отдать животное</a></th>
                </tr>
            </#if>
        <#else>
            <h3>Забронированных животных нет</h3>
        </#list>
    </table>

<#else>






    <h4> Все животные пользователя </h4>
    <table border="1">
        <tr>
            <th>Тип животного</th>
            <th>Город приюта</th>
            <th>Номер приюта</th>
            <th>Цвет животного</th>
            <th>Описание животного</th>
            <th>Статус животного</th>
        </tr>
        <#list animals as animal>
            <tr>
                <th>${animal.species.getDescription()}</th>
                <th>${animal.shelter.city}</th>
                <th>${animal.shelter.phone}</th>
                <th>${animal.colorAnimal.getDescription()}</th>
                <th>${animal.description}</th>
                <th>${animal.animalsStatus.getDescription()}</th>
                <th>
                    <#if animal.animalsStatus == "MISSING" || animal.animalsStatus == "CANCELED">
                        <a href="/animal/edit/${animal.id}">Редактирование</a>
                    </#if>
                </th>
                <th><a href="/animal/${animal.id}">Подробная информация</a></th>
            </tr>
        <#else>
            <h3>Животных нет</h3>
        </#list>
    </table>
    <h4> Забронированные животные пользователя </h4>
    <table border="1">
        <tr>
            <th>Тип животного</th>
            <th>Город приюта</th>
            <th>Номер приюта</th>
            <th>Цвет животного</th>
            <th>Описание животного</th>
            <th>Статус животного</th>
        </tr>
        <#list booking as animal>
            <tr>
                <th>${animal.species.getDescription()}</th>
                <th>${animal.shelter.city}</th>
                <th>${animal.shelter.phone}</th>
                <th>${animal.colorAnimal.getDescription()}</th>
                <th>${animal.description}</th>
                <th>${animal.animalsStatus.getDescription()}</th>
                <th><a href="/animal/edit/${animal.id}">Редактирование</a></th>
                <th><a href="/animal/${animal.id}">Подробная информация</a></th>
            </tr>
        <#else>
            <h3>Забронированных животных нет</h3>
        </#list>
    </table>
</#if>
<hr>
</body>
</html>