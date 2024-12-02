<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Books</title>
</head>

<body>
<h1>Book List</h1>
<g:if test="${flash.message}">
    <div class="alert alert-info">
        ${flash.message}
    </div>
</g:if>

<g:link controller="book" action="create">Create New Book</g:link>
<g:link controller="borrow" action="index">List Borrower</g:link>

<table border="1">
    <thead>
    <tr>
        <th>id</th>
        <th>Title</th>
        <th>Author</th>
        <th>Quantity</th>
        <th>Year</th>
        <th>Actions</th>
    </tr>
    </thead>
    <tbody>
    <g:each in="${bookList}" var="book">
        <tr>
            <td>${book.id}</td>
            <td>${book.title}</td>
            <td>${book.author}</td>
            <td>${book.quantity}</td>
            <td>${book.year}</td>
            <td>
                <g:link controller="book" action="edit" id="${book.id}">Edit</g:link> |
                <g:link controller="book" action="delete" id="${book.id}">Delete</g:link>
            </td>
        </tr>
    </g:each>
    </tbody>
</table>
</body>
</html>