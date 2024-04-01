@import exercise.dto.users.UserPage
@param UserPage page

@template.layout.page(
content = @`
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
`
)