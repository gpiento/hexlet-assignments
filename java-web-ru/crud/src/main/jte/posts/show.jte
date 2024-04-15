@import exercise.util.NamedRoutes
@import exercise.dto.posts.PostPage
@param PostPage postPage

@template.layout.page(
content = @`
    <div class="mx-auto p-4 py-md-5">
        <main>
            <div class="d-grid gap-2 d-md-block">
                <a href="${NamedRoutes.postsPath()}" class="btn btn-primary" role="button">Все посты</a>
            </div>
            <br>
            <div class="card">
                <div class="card-header">
                    <h5 class="card-title">${postPage.getPost().getName()}</h5>
                </div>
                <div class="card-body">
                    <p class="card-text">${postPage.getPost().getBody()}</p>
                </div>
            </div>

        </main>
    </div>
`
)
