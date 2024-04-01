@import exercise.dto.users.UsersPage
@import exercise.model.User
@param UsersPage page

@template.layout.page(
content = @`
    <div class="container mt-5">
        <h2>Users List</h2>
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
`
)