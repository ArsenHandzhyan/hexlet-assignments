@template.layout.page(
content = @`
<!DOCTYPE html>
    <html lang="ru">
    <head>
        <meta charset="utf-8"/>
        <meta name="viewport" content="width=device-width, initial-scale=1"/>
        <title>Hexlet Users</title>
    </head>
    <form action="/users" method="post">
        <div>
            <label>
                Имя
                <input type="text" required name="firstName"/>
            </label>
        </div>
        <div>
            <label>
                Фамилия
                <input type="text" required name="LastName"/>
            </label>
        </div>
        <div>
            <label>
                Email
                <input type="email" required name="email"/>
            </label>
        </div>
        <div>
            <label>
                Пароль
                <input type="password" required name="password"/>
            </label>
        </div>
        <input type="submit" value="Зарегистрировать"/>
    </form>
    <div>
        <p><a href="/users">return to users page</a></p>
    </div>
    </html>
`
)
