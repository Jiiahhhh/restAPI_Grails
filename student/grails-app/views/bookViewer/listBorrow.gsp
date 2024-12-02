<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Daftar Pinjam kamu</title>
</head>

<body>
<table border="1">
    <thead>
    <tr>
        <th>ID</th>
        <th>Title</th>
        <th>Name</th>
        <th>Address</th>
        <th>Email</th>
        <th>dateBorrow</th>
        <th>dateReturn</th>
    </tr>
    </thead>
    <tbody>
    <g:each in="${bookList}" var="borrow">
        <tr>
            <td>${borrow.id}</td>
            <td>${borrow.bookTitle}</td>
            <td>${borrow.name}</td>
            <td>${borrow.address}</td>
            <td>${borrow.email}</td>
            <td>${borrow.dateBorrow}</td>
            <td>${borrow.dateReturn}</td>
        </tr>
    </g:each>
    </tbody>
</table>
</body>
</html>