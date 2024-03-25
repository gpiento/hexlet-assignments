@import exercise.dto.users.UsersPage
@import exercise.model.User
@param UsersPage page

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User List</title>
    <!-- Добавление Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
<div class="container mt-5">
    <h2>User List</h2>
    <table class="table table-striped">
        <thead>
        <tr>
            <th scope="col">ID</th>
            <th scope="col">First Name</th>
            <th scope="col">Last Name</th>
            <th scope="col">Email</th>
        </tr>
        </thead>
        <tbody>
        @if(page.getUsers().isEmpty())
            <p>List users empty</p>
        @else
            @for(User user : page.getUsers())
                <tr>
                    <th scope="row"><a href="/users/${user.getId()}">${user.getId()}</a></th>
                    <td>${user.getFirstName()}</td>
                    <td>${user.getLastName()}</td>
                    <td>${user.getEmail()}</td>
                </tr>
            @endfor
        @endif
        </tbody>
    </table>
</div>
<!-- Добавление Bootstrap JS и jQuery -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
