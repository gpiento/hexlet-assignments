@import exercise.util.NamedRoutes
@import exercise.dto.MainPage
@param MainPage page

@template.layout.page(content = @`
    <div class="mx-auto p-4 py-md-5">
        <h1>Привет, Хекслет!</h1>
        <div>
            @if(page.getName() != null)
                <h2>Добро пожаловать, ${page.getName().toString()}</h2>
                <form action="${NamedRoutes.logoutPath()}" method="post">
                    <button type="submit" class="btn btn-primary">Выйти</button>
                </form>
            @else
                <form action="${NamedRoutes.buildSessionPath()}" method="get">
                    <button type="submit" class="btn btn-primary">Войти</button>
                </form>
            @endif
        </div>
    </div>
`
)
