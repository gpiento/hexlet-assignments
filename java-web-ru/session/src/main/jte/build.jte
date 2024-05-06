@import exercise.util.NamedRoutes
@import exercise.dto.LoginPage
@param LoginPage page

@template.layout.page(
content = @`
    <div class="container mx-auto p-4 py-md-5">
        <div class="row align-items-start">
            <div class="col"></div>
            <div class="col"></div>
            <div class="col"></div>
        </div>
        <div class="row align-items-center">
            <div class="col"></div>
            <div class="col">
                <form action="${NamedRoutes.loginPath()}" method="post">
                    <div>
                        @if(page.getError() != null)
                            <p>Wrong username or password.</p>
                        @endif
                    </div>
                    <div class="mb-3">
                        <label class="form-label" for="inputLogin">Login</label>
                        <input type="text" class="form-control" id="inputLogin" placeholder="Nickname" name="name"
                               value="${page.getName()}"/>
                    </div>
                    <div class="mb-3">
                        <label class="form-label" for="inputPassword">Password</label>
                        <input type="password" class="form-control" id="inputPassword" placeholder="Password"
                               name="password"/>
                    </div>
                    <input type="submit" class="btn btn-primary" value="Войти"/>
                </form>
            </div>
            <div class="col"></div>
        </div>
        <div class="row align-items-end">
            <div class="col"></div>
            <div class="col"></div>
            <div class="col"></div>
        </div>
    </div>
`
)