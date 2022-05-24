<html>
<head>
    <title></title>
</head>
<body>

<div class="container">
    <ul>
        <h1>List of all users</h1>
        <#list users as user>
            <div class="username">
                <li>${user.userName}</li>
                <button class="delete-btn">delete</button>
            </div>
        </#list>
    </ul>
</div>


</body>

<style>

    body{
        font-family: Montserrat, sans-serif;
        background: #28223f;
    }

    .container{
        user-select: none;
        margin: 100px auto;
        background: #231e39;
        color: #b3b8cd;
        border-radius: 5px;
        width: 35%;
        text-align: center;
        box-shadow: 0 10px 20px -10px rgba(0,0,0,.75);
        padding: 50px;
    }

    ul {
        list-style-type: none;
    }

    li {
        margin: 15px;
        padding: 5px;
    }

    button {
        background: #03bfbc;
        border: 1px solid #03bfbc;
        padding: 10px 25px;
        color: #231e39;
        border-radius: 3px;
        font-family: Montserrat, sans-serif;
        cursor: pointer;
    }

    .username {
        display: flex;
        flex-direction: row;
        justify-content: space-evenly;
        align-items: center;
        row-gap: 10px;
    }



</style>
</html>