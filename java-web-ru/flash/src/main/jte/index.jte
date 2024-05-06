@import exercise.util.NamedRoutes

@template.layout.page(
content = @`
    <h2>Welcome to Hexlet</h2>
    <div class="mx-auto p-4 py-md-5">
        <main>
            <div class="d-grid gap-2 d-md-block">
                <a href="${NamedRoutes.postsPath()}" class="btn btn-primary" role="button">Все посты</a>
            </div>
            <br>
        </main>
    </div>
`
)
