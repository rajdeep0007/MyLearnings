import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcConnectionExecution {

	// Note:add mysql connector jar in the build path
	public static void main(String[] args) {

		Connection con = null;
		Statement stmt = null;

		try {
			// throws class not found exception
			Class.forName("com.mysql.jdbc.Driver");

			// throws sql exception
			con = DriverManager.getConnection("jdbc:mysql://localhost/sys", "root", "mysql");

			stmt = con.createStatement();

			// int rs = stmt.executeUpdate("create table car (id varchar(20) , name
			// varchar(20));");
			// int x = stmt.executeUpdate("Insert into car values (12,10)");
			// System.out.println(x);

			ResultSet rs = stmt.executeQuery("select * from car");
			while (rs.next()) {
				String s = rs.getString(1);
				String s1 = rs.getString(2);
				System.out.println(" row = " + s + "|" + " " + s1);
			}

			PreparedStatement stm = con.prepareStatement("insert into car values (?,?)");

			stm.setString(1, "22");
			stm.setString(2, "36");

			stm.executeUpdate();

			DatabaseMetaData dbmd = con.getMetaData();
			System.out.println(dbmd.getDriverName());
			System.out.println(dbmd.getDatabaseProductName());

		} catch (ClassNotFoundException | SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if (stmt != null)
					con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}
}
