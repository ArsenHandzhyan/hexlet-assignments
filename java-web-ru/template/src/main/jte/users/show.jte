@import exercise.dto.users.UserPage
@param UserPage page

<body>
<main>
    <table>
        <tr>
            <th>User id</th>
            <th>Last Name</th>
            <th>First Name</th>
            <th>Email</th>
        </tr>
        <tr>
            <td>${page.getUser().getId()}</td>
            <td>${page.getUser().getLastName()}</td>
            <td>${page.getUser().getFirstName()}</td>
            <td>${page.getUser().getEmail()}</td>
        </tr>
    </table>
</main>
</body>
