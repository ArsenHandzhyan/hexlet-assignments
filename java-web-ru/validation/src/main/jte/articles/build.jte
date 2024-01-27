@import exercise.dto.articles.BuildArticlePage
@param BuildArticlePage page

@template.layout.page(
content = @`
    <div class="mx-auto p-4 py-md-5">
        <main>
            <h1>Создание новой статьи</h1>

            @if(page.getErrors() != null)
                <ul>
                    @for(var validator : page.getErrors().values())
                        @for(var error : validator)
                            <li>${error.getMessage()}</li>
                            <li>${page.getContent()}</li>
                            <li>${page.getTitle()}</li>
                        @endfor
                    @endfor
                </ul>
            @endif

            <form action="/articles" method="post">
                <div>
                    <label>
                        title
                        <input type="text" name="title" value="${page.getTitle()}" />
                    </label>
                </div>
                <div>
                    <label>
                        content
                        <textarea name="content" required>${page.getContent()}</textarea>
                    </label>
                </div>
                <input type="submit" value="Создать" />
            </form>
        </main>
    </div>
`
)