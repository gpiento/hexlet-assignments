@import exercise.dto.articles.BuildArticlePage
@param BuildArticlePage page

@template.layout.page(
content = @`
    <div class="mx-auto p-4 py-md-5">
        <main>
            <h1>Создание статьи</h1>

            <div class="mb-3">
                <a href="/articles">Все статьи</a>
            </div>

            <div>
                @if(page.getErrors() != null)
                    <ul>
                        @for(var validator : page.getErrors().values())
                            @for(var error : validator)
                                <li>${error.getMessage()}</li>
                            @endfor
                        @endfor
                    </ul>
                @endif
            </div>

            <form action="/articles" method="post">
                <div class="mb-3">
                    <label for="exampleFormControlInput1" class="form-label">Название статьи</label>
                    <input type="text" name="title" class="form-control"
                           id="exampleFormControlInput1"
                           placeholder="Название статьи" value="${page.getTitle()}"/>
                </div>
                <div class="mb-3">
                    <label for="exampleFormControlTextarea1" class="form-label">Текст статьи</label>
                    <textarea class="form-control" name="content"
                              id="exampleFormControlTextarea1">${page.getContent()}</textarea>
                </div>
                <input type="submit" value="Зарегистрировать"/>
            </form>
        </main>
    </div>
`
)
