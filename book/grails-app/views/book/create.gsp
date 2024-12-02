<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Create Book</title>
</head>

<body>
<h1>Create a New Book</h1>
<g:if test="${flash.message}">
    <div class="alert alert-info">
        ${flash.message}
    </div>
</g:if>
<g:form controller="book" action="${book ? 'update' : 'save'}" method='POST'>
    <g:hiddenField name="id" value="${book?.id}"/>
    <div>
        <label for="title">Title:</label>
        <g:textField name="title" required="true" value="${book?.title ?: ''}"/>
    </div>

    <div>
        <label for="author">Author:</label>
        <g:textField name="author" required="true" value="${book?.author ?: ''}"/>
    </div>

    <div>
        <label for="year">Year:</label>
        <g:textField name="year" required="true" value="${book?.year ?: ''}"/>
    </div>

    <div>
        <g:submitButton name="submit" value="Save"/>
    </div>
</g:form>

</body>
</html>