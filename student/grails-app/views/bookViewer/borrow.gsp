<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Pinjam Buku</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/flatpickr/dist/flatpickr.min.css">
    <script src="https://cdn.jsdelivr.net/npm/flatpickr"></script>
</head>

<body>
<h1>Pinjam Buku ${params.title}</h1>
<g:if test="${flash.message}">
    ${flash.message}
</g:if>

<!-- Form untuk peminjaman, dengan hidden input untuk bookId -->
<g:form controller="bookViewer" action="borrowed">
    <!-- Hidden input untuk bookId -->
    <g:hiddenField name="bookId" value="${bookId}"/>

    <div>
        <label for="name">Nama:</label>
        <g:textField name="name" required="true"/>
    </div>

    <div>
        <label for="address">Alamat:</label>
        <g:textField name="address" required="true"/>
    </div>

    <div>
        <label for="email">Email:</label>
        <g:textField name="email" required="true"/>
    </div>

    <div>
        <label for="startDate">Tanggal Pinjam:</label>
        <input type="text" id="startDate" name="startDate" placeholder="dd/mm/yyyy" required
               class="form-control datepickers"/>
    </div>

    <div>
        <p>Batas Pengembalian:</p>
    </div>

    <div>
        <g:submitButton name="submit" value="Save"/>
    </div>

</g:form>

</body>
<script>
    flatpickr("#startDate", {
        dateFormat: 'Y-m-d',
        minDate: "today",
        onchange: function (selectedDate, dateStr, instance) {
            if (selectedDate.length > 0) {
                var startDate = selectedDate[0];
                var returnDate = new Date(startDate)
                returnDate.setDate(startDate.getDate() + 7);

                document.getElementById('returnDate').textContent = returnDate.toLocaleDateString('id-ID');
            }
        }
    });
</script>
</html>
