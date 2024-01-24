@import exercise.dto.users.UsersPage
@param UsersPage page

<body>
@if(page.getUsers().isEmpty())
    <p>Пока не добавлено ни одного user</p>
@else
    @for(var user : page.getUsers())
        <div>
            <h2><a href="/users/${user.getId()}">${user.getFirstName()} ${user.getLastName()}</a></h2>
        </div>
    @endfor
@endif
<p><a href="/">Return to start page</a></p>
</body>

