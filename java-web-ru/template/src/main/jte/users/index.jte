@import exercise.dto.users.UsersPage
@param UsersPage page

<body>
<main>
    @if(page.getUsers().isEmpty())
        <p>Пока не добавлено ни одного пользователя</p>
    @else
        <table>
            <tr>
                <th>User id</th>
                <th>Last Name</th>
                <th>First Name</th>
                <th>Email</th>
            </tr>
            @for(var user : page.getUsers())
                <tr>
                    <td><a href="/users/${user.getId()}">${user.getId()}</a></td>
                    <td>${user.getLastName()}</td>
                    <td>${user.getFirstName()}</td>
                    <td>${user.getEmail()}</td>
                </tr>
            @endfor
        </table>
    @endif
</main>
</body>