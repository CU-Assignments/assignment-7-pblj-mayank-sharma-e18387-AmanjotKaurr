<%@ page language="java" contentType="text/html" %>
<html>
<head><title>Student Attendance</title></head>
<body>
    <h2>Attendance Form</h2>
    <form action="AttendanceServlet" method="post">
        Name: <input type="text" name="name"/><br/><br/>
        Date: <input type="date" name="date"/><br/><br/>
        Status:
        <select name="status">
            <option value="Present">Present</option>
            <option value="Absent">Absent</option>
        </select><br/><br/>
        <input type="submit" value="Submit Attendance"/>
    </form>
</body>
</html>
