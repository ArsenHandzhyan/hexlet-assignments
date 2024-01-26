@import exercise.dto.users.UsersPage
@param UsersPage page

@template.layout.page(
content = @`
    <h1>Пользователи</h1>

    <div class="mb-3">
        <a href="/users/build">Зарегистрировать нового пользователя</a>
    </div>
    @for(var user : page.getUsers())
        <p>${user.getFirstName()} ${user.getLastName()}</p>
    @endfor
`
)
