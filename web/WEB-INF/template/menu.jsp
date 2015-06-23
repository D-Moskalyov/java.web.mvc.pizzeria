<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<tiles:insertDefinition name="defaultTemplate">
    <tiles:putAttribute name="body">
<nav class="navbar navbar-inverse">
    <div class="container">

        <ul class="nav navbar-nav">
            <%--<li><a href="<c:url value="/user/login" />"> Login</a> </li>  --%>
            <li><a href="<c:url value="/shop" />">Browse categories</a></li>
            <%--<li><a href="<c:url value="/user/show" />">Profile</a></li>--%>
            <%--<li><a href="<c:url value="/user/logout" />">Logout</a></li>--%>
        </ul>
    </div>
</nav>
    </tiles:putAttribute>
</tiles:insertDefinition>