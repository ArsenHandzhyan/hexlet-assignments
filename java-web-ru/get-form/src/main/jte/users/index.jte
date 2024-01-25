@import exercise.dto.users.UsersPage
@param UsersPage page

<form action="/users" method="get">
    <input type="search" name="term" value="${page.getTerm()}" placeholder="Search by name" />
    <input type="submit" value="Search" />
    @for(var user : page.getUsers())
        <div>
            <div>${user.getFirstName()} ${user.getLastName()}</div>
        </div>
    @endfor
</form>