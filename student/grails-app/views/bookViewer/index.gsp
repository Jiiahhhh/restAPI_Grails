<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Daftar Buku</title>
</head>
<body>
<h1>Student - Daftar Buku</h1>
<g:if test="${flash.message}">
    ${flash.message}
</g:if>

<g:link controller="bookViewer" action="listBorrow">List Borrower</g:link>
<g:if test="${bookList}">
    <table border="1">
        <thead>
        <tr>
            <th>ID</th>
            <th>Title</th>
            <th>Author</th>
            <th>Quantity</th>
            <th>Year</th>
            <th>Action</th>
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
                <!-- Link untuk Borrow dengan passing book.id sebagai parameter -->
                <td><g:link controller="bookViewer" action="borrow" id="${book.id}">Borrow</g:link></td>
            </tr>
        </g:each>
        </tbody>
    </table>
</g:if>

<g:else>
    <p>No books found.</p>
</g:else>
</body>
</html>
