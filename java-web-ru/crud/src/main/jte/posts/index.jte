@import exercise.util.NamedRoutes
@import exercise.dto.posts.PostsPage
@param PostsPage postsPage

@template.layout.page(
content = @`
    <div class="mx-auto p-4 py-md-5">
        <main>
            <div class="d-grid gap-2 d-md-block">
                <a href="${NamedRoutes.rootPath()}" class="btn btn-primary" role="button">Начало</a>
                <a href="${NamedRoutes.buildPostPath()}" class="btn btn-primary" role="button">Создать новую статью</a>
                <a href="${NamedRoutes.postsPagePath()}${postsPage.getPage() - 1}" class="btn btn-primary"
                   role="button">Назад</a>
                <a href="${NamedRoutes.postsPagePath()}${postsPage.getPage() + 1}" class="btn btn-primary"
                   role="button">Вперед</a>
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
                    @for(var post : postsPage.getPosts())
                        <tr>
                            <%--
                                                    <th scope="row" style="width: auto;">
                                                        @if(post.getId() != null)
                                                            ${post.getId()}
                                                        @endif
                                                    </th>
                            --%>
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
`
)
