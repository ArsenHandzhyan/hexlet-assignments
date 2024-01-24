@import exercise.dto.users.UserPage
@param UserPage page

<body>
<div>
    <h2>${page.getUser().getFirstName()} ${page.getUser().getLastName()}</h2>
</div>
<p><a href="/users">Return to users page</a></p>
</body>