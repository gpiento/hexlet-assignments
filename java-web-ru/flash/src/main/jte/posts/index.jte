@import exercise.dto.posts.PostsPage
@import exercise.model.Post
@import exercise.util.NamedRoutes
@param PostsPage postsPage

@template.layout.page(
content = @`
    <div class="mx-auto p-4 py-md-5">
        <main>
            @if(postsPage != null && postsPage.getFlash() != null)
                <div class="alert alert-primary alert-dismissible fade show" role="alert">
                    ${postsPage.getFlash()}
                </div>
            @endif
            <div class="d-grid gap-2 d-md-block">
                <a href="${NamedRoutes.rootPath()}" class="btn btn-primary" role="button">Начало</a>
                <a href="${NamedRoutes.buildPostPath()}" class="btn btn-primary" role="button">Создать новую статью</a>
            </div>
            <br>

            <div class="table-responsive">
                <table class="table table-striped table-bordered">
                    <thead>
                    <tr>
                        <%--                    <th scope="col">#</th>--%>
                        <th scope="col">Название статьи</th>
                        <th scope="col">Текст статьи</th>
                    </tr>
                    </thead>
                    <tbody>
                    @for(Post post : postsPage.getPosts())
                        <tr>
                            <td>
                                <a href="${NamedRoutes.postPath(post.getId())}">${post.getName()}</a>
                            </td>
                            <td>
                                ${post.getBody()}
                            </td>
                        </tr>
                    @endfor
                    </tbody>
                </table>
            </div>
        </main>
    </div>
`,
)
