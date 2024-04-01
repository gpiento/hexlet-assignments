@import gg.jte.Content
@param Content content

<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <title>Hexlet Javalin Example</title>
<%--
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
--%>
</head>
<body>
<p>
    <a href="/">/</a>
    <a href="/users">/users</a>
</p>
${content}
<p>
    <a href="https://github.com/gpiento">User repository</a>
</p>
<!-- Добавление Bootstrap JS и jQuery -->
<%--
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
--%>
</body>
</html>
