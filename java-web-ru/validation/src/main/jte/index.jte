@import exercise.dto.articles.ArticlesPage
@param  ArticlesPage articlesPage
@template.layout.page(
content = @`
    <div class="mx-auto p-4 py-md-5">
        <main>
            <a href="/articles">Все статьи</a>
        </main>
    </div>
    @if(articlesPage != null && articlesPage.getArticles() != null)
        @for(var article : articlesPage.getArticles())
            <div>
                <p><a href="/users/${article.getId()}">
                        <div>${article.getTitle()} ${article.getContent()}</div>
                    </a></p>
            </div>
        @endfor
    @endif

`
)
