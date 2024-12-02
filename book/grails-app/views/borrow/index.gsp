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

<g:link controller="book" action="index">List Book</g:link>

<table border="1">
    <thead>
    <tr>
        <th>id</th>
        <th>Title Book</th>
        <th>Borrower</th>
        <th>Email</th>
        <th>Address</th>
        <th>Date borrow</th>
        <th>Date return</th>
    </tr>
    </thead>
    <tbody>
    <g:each in="${borrowList}" var="borrow">
        <tr>
            <td>${borrow.id}</td>
            <td>${borrow.book.title}</td>
            <td>${borrow.name}</td>
            <td>${borrow.email}</td>
            <td>${borrow.address}</td>
            <td>${borrow.dateBorrow}</td>
            <td>${borrow.dateReturn}</td>
        </tr>
    </g:each>
    </tbody>
</table>
</body>
</html>