@import exercise.model.User
@param User page

@template.layout.page(
content = @`
    <h2 class="text-body-emphasis">Hello, world!</h2>
<%--
    @if(page.getFirstName() != null)
        Welcome, ${page.getFirstName()}. if you want to sign out remove cookie called JSESSIONID
    @endif
--%>
`
)