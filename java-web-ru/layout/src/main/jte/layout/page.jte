@import gg.jte.Content

@param Content content
@param Content footer


<!doctype html>
<html lang="en">
<head>
    <title>Users</title>
    <p href="/users">Users</p>
    <p href="/">main page</p>
</head>
<body>
<div class="content">
    ${content}
</div>
@if (footer != null)
    <div class="footer">
        ${footer}
    </div>
@endif
</body>
</html>