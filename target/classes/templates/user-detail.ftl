<html>
<head>
    <title></title>
    <link href="/stylesheets/style.css" rel="stylesheet">
</head>
<body>


<div class="container">
    <div class="cover-photo">
        <img src="https://images.unsplash.com/photo-1565464027194-7957a2295fb7?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=800&q=80"
             class="profile">
    </div>
    <div class="profile-name">${userName}</div>
    <div class="profile-info">
        <h3>Profile information</h3>
        <ul class="info">
            <li class="li-info">Full name: ${firstName} ${lastName}</li>
            <li class="li-info">Email: ${email}</li>
            <li class="li-info">Username: ${userName}</li>
            <li class="li-info">Role: ${role}</li>
        </ul>
        <hr>
        <h3>Todo List</h3>
        <ul class="todo">
            <#list todoList as list>
                <div class="todo-list">
                    <li class="item">${list.title}</li>
                    <div class="list-btn">
                        <button class="btn ">open</button>
                        <button class="btn ">delete</button>
                    </div>
                </div>
            </#list>
        </ul>
        <hr>
        <div class="options">
            <button onclick="window.location.href='/logout'" class="btn">Logout</button>
            <#if role == "admin">
                <button onclick="window.location.href='/admin'" class="btn">Admin panel</button>
            </#if>
        </div>
    </div>
</div>


<style>
    body {
        font-family: Montserrat, sans-serif;
        background: #28223f;
    }

    .container {
        user-select: none;
        margin: 100px auto;
        background: #231e39;
        color: #b3b8cd;
        border-radius: 5px;
        max-width: 40%;
        min-width: 10%;
        text-align: center;
        box-shadow: 0 10px 20px -10px rgba(0, 0, 0, .75);
        padding-bottom: 50px;
    }

    .cover-photo {
        background: url(https://images.unsplash.com/photo-1540228232483-1b64a7024923?ixlib=rb-1.2.1&auto=format&fit=crop&w=967&q=80);
        height: 160px;
        width: 100%;
        border-radius: 5px 5px 0 0;
    }

    .profile {
        height: 120px;
        width: 120px;
        border-radius: 50%;
        margin: 93px 0 0 -175px;
        border: 1px solid #1f1a32;
        padding: 7px;
        background: #292343;
    }

    .profile-name {
        font-size: 25px;
        font-weight: bold;
        margin: 27px 0 0 120px;
    }

    h3 {
        margin-top: 25px;
        padding: 5px;
    }

    .info {
        list-style-type: none;
        margin: 0;
        padding: 0;

    }


    .li-info {
        margin: 15px;
        padding: 5px;
    }

    button {
        margin: 10px;

    }

    .container i {
        padding-left: 20px;
        font-size: 20px;
        margin-bottom: 20px;
        cursor: pointer;
        transition: .5s;
    }

    .container i:hover {
        color: #03bfbc;
    }

    .btn {
        background: #03bfbc;
        border: 1px solid #03bfbc;
        padding: 10px 25px;
        color: #231e39;
        border-radius: 3px;
        font-family: Montserrat, sans-serif;
        cursor: pointer;
    }

    a {
        text-decoration: none;
        color: #231e39;
    }

    .todo {
        list-style-type: none;

    }

    .todo-list {
        display: flex;
        flex-direction: row;
        justify-content: center;
        align-items: baseline;
        margin: auto;
        align-content: center;

    }

    .item {
        flex-basis: 50%;
    }


    .options {
        display: flex;
        margin: auto;
        flex-direction: row;
        justify-content: space-between;
        align-items: flex-end;
        align-content: flex-end;
    }

    .list-btn {
        flex-basis: 50%;

    }

</style>

</body>
</html>