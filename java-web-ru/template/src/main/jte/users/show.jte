@import exercise.dto.users.UserPage
@param UserPage page

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User Details</title>
    <!-- Добавление Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
<div class="container mt-5">
    <h2>User Details</h2>
    <div class="card">
        <div class="card-body">
            <h5 class="card-title">User ID: ${page.getUser().getId()}</h5>
            <p class="card-text">First Name: ${page.getUser().getFirstName()}</p>
            <p class="card-text">Last Name: ${page.getUser().getLastName()}</p>
            <p class="card-text">Email: ${page.getUser().getEmail()}</p>
        </div>
    </div>
</div>
<!-- Добавление Bootstrap JS и jQuery -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
