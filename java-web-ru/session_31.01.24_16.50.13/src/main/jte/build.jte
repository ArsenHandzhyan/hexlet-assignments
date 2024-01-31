@import exercise.util.NamedRoutes
@import exercise.dto.LoginPage
@param LoginPage page

@template.layout.page(
content = @`
    <form action="${NamedRoutes.loginPath()}" method="post">
        <label for="name">Username:</label>
        <input type="text" id="name" name="name" required>
        <br>
        <label for="password">Password:</label>
        <input type="password" id="password" name="password" required>
        <br>
        <button type="submit">Login</button>
        <br>
        @if(page.getError() != null)
            <p style="color: red;">${page.getError()}</p>
        @endif
    </form>
`
)