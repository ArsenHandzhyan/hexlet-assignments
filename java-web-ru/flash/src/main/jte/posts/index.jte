@import exercise.util.NamedRoutes
@import exercise.dto.posts.PostsPage
@param PostsPage page

@template.layout.page(
content = @`
    <div>
        <p><a href="${NamedRoutes.buildPostPath()}">add new post</a></p>
    </div>
    <form action="${NamedRoutes.postsPath()}" method="get">
        <label>
            <input type="search" name="term" placeholder="Search by term"/>
        </label>
        <label>
            <input type="search" name="description" placeholder="Search by description"/>
        </label>
        <input type="submit" value="Search"/>
        <p>Result of searching</p>
        @for(var post : page.getPosts())
            <div>
                <p><a href="${NamedRoutes.postsPath()}/${post.getId()}"><div>${post.getName()} ${post.getBody()}</div></a></p>
            </div>
        @endfor
    </form>
    @if(page != null && page.getFlash() != null)
        <p>${page.getFlash()}</p>
    @endif
`
)
