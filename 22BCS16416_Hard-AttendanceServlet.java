import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class AttendanceServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("name");
        String date = request.getParameter("date");
        String status = request.getParameter("status");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/your_database", "root", "yourpassword");

            String sql = "INSERT INTO attendance (name, date, status) VALUES (?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, date);
            ps.setString(3, status);

            ps.executeUpdate();
            ps.close();
            conn.close();

            request.getRequestDispatcher("attendance-success.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Error saving attendance.");
        }
    }
}
