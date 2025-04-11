import java.io.*;
import java.sql.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class EmployeeServlet extends HttpServlet {
    private Connection conn;

    public void init() {
        try {
            Properties props = new Properties();
            props.load(getServletContext().getResourceAsStream("/WEB-INF/db-config.properties"));
            String url = props.getProperty("url");
            String user = props.getProperty("user");
            String pass = props.getProperty("password");

            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, pass);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idParam = request.getParameter("id");
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");

        try {
            Statement stmt = conn.createStatement();
            ResultSet rs;

            if (idParam != null && !idParam.isEmpty()) {
                rs = stmt.executeQuery("SELECT * FROM employees WHERE id=" + idParam);
            } else {
                rs = stmt.executeQuery("SELECT * FROM employees");
            }

            out.println("<h3>Employee Records:</h3><table border='1'>");
            out.println("<tr><th>ID</th><th>Name</th><th>Department</th><th>Email</th></tr>");
            while (rs.next()) {
                out.println("<tr><td>" + rs.getInt("id") + "</td><td>" +
                        rs.getString("name") + "</td><td>" +
                        rs.getString("department") + "</td><td>" +
                        rs.getString("email") + "</td></tr>");
            }
            out.println("</table>");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void destroy() {
        try {
            if (conn != null) conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
