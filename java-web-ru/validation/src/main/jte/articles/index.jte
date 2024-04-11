@import exercise.dto.articles.ArticlesPage
@param ArticlesPage page

@template.layout.page(
content = @`
    <div class="mx-auto p-4 py-md-5">
        <main>
            <h1>Все статьи</h1>

            <div class="mb-3">
                <a href="/articles/build">Создать новую статью</a>
            </div>

            <table class="table table-success table-striped table-bordered">
                <thead>
                <tr>
                    <th scope="col">#</th>
                    <th scope="col">Название статьи</th>
                    <th scope="col">Текст статьи</th>
                </tr>
                </thead>
                <tbody>
                @for(var article : page.getArticles())
                    <tr>
                        <th scope="row">
                            ${article.getId()}
                        </th>
                        <td>
                            ${article.getTitle()}
                        </td>
                        <td>
                            ${article.getContent()}
                        </td>
                    </tr>
                @endfor
                </tbody>
            </table>
        </main>
    </div>
`
)
