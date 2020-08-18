package application.worker.menu.newworksheet.description.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import application.setting.database.DataBaseConnect;
import application.worker.menu.newworksheet.description.pojo.Description;

public class DescriptionDB {

	// adatbazis lekerdezes
	public ArrayList<Description> getAllWorker(String id) {
		Connection con = DataBaseConnect.getConnection();
		String sql = null;
		sql = "SELECT * FROM `leiras` WHERE munkalap_munkalap_id = " + id ;

		ArrayList<Description> client = null;
		Statement createStatement = null;
		ResultSet rs = null;
		try {
			createStatement = con.createStatement();
			rs = createStatement.executeQuery(sql);
			client = new ArrayList<>();
			while (rs.next()) {
				Description actualCLient = new Description(rs.getInt("leiras_id"), rs.getInt("munkalap_munkalap_id"),
						rs.getString("leiras_datum"), rs.getString("hibaleiras"), rs.getString("leiras_allapot"));
				client.add(actualCLient);
			}
		} catch (SQLException e) {
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (createStatement != null) {
					createStatement.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
			}
		}
		return client;
	}

	// Munkalap adatbazis feltoltve
	public void addDescription(Description newDescription) {
		Connection con = DataBaseConnect.getConnection();
		PreparedStatement preparedStatement = null;
		try {
			String sql = "INSERT INTO `leiras` (munkalap_munkalap_id, leiras_datum, hibaleiras, leiras_allapot"
					+ ") VALUES (?,?,?,?)";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setString(1, newDescription.getWorksheetId());
			preparedStatement.setString(2, newDescription.getDescriptionStartDate());
			preparedStatement.setString(3, newDescription.getDescriptionComment());
			preparedStatement.setString(4, newDescription.getDescriptionStatus());
			preparedStatement.execute();
		} catch (SQLException ex) {
			System.out.println("Valami baj van a contact hozzáadásakor");
			System.out.println("" + ex);
		} finally {
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException ex) {
			}
		}
	}
}
