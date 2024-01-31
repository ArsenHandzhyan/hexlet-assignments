@import exercise.dto.MainPage
@import exercise.util.NamedRoutes
@param MainPage page

@template.layout.page(
content = @`
    <main>
        @if(page.getCurrentUser() != null)
            <p>Добро пожаловать, ${page.getCurrentUser()}.</p>
            <p><a href="${NamedRoutes.logoutPath()}">Выйти</a></p>
        @else
            <a href="${NamedRoutes.buildSessionPath()}">Войти</a>
        @endif
        <h1>Привет, Хекслет!</h1>
    </main>
`
)
