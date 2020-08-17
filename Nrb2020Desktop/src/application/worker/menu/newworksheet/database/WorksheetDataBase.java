package application.worker.menu.newworksheet.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import application.setting.database.DataBaseConnect;
import application.worker.menu.newworksheet.pojo.NewWorksheet;

public class WorksheetDataBase {

	// adatbazis lekerdezes
	public ArrayList<NewWorksheet> getAllWorker() {
		Connection con = DataBaseConnect.getConnection();
		String sql = null;
		sql = "SELECT * FROM `ugyfel` JOIN `munkalap` ON ugyfel_id = ugyfel_ugyfel_id";

		ArrayList<NewWorksheet> client = null;
		Statement createStatement = null;
		ResultSet rs = null;
		try {
			createStatement = con.createStatement();
			rs = createStatement.executeQuery(sql);
			client = new ArrayList<>();
			while (rs.next()) {
				NewWorksheet actualWorksheet = new NewWorksheet(rs.getString("ugyfel_neve"), rs.getInt("munkalap_id"),
						rs.getString("munk_azonosito"), rs.getString("munk_datum_nyitas"),
						rs.getString("munk_datum_lezaras"), rs.getString("munkalap_allapot"),
						rs.getString("szamlazhato"), rs.getString("szamlan_adatok"), rs.getString("munk_megjegyzes"));
				client.add(actualWorksheet);
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
	public void addClient(NewWorksheet newWorksheet) {
		Connection con = DataBaseConnect.getConnection();
		PreparedStatement preparedStatement = null;
		try {
			String sql = "INSERT INTO `munkalap` (ugyfel_ugyfel_id, munk_azonosito, munk_datum_nyitas, munkalap_allapot,"
					+ " szamlazhato, munk_megjegyzes) VALUES (?,?,?,?,?,?)";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setString(1, newWorksheet.getClientId());
			preparedStatement.setString(2, newWorksheet.getWorksheetNumber());
			preparedStatement.setString(3, newWorksheet.getWorksheetStartDate());
			preparedStatement.setString(4, newWorksheet.getWorksheetStatus());
			preparedStatement.setString(5, newWorksheet.getWorksheetBillable());
			preparedStatement.setString(6, newWorksheet.getWorksheetComment());
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

	// adatbazis javitas
	public void updateWorksheet(NewWorksheet newWorksheet) {
		Connection conn = DataBaseConnect.getConnection();
		PreparedStatement pr = null;
		try {
			String sqlClient = "UPDATE `munkalap` SET munkalap_allapot = ?, munk_datum_lezaras = ?, szamlazhato = ?, szamlan_adatok = ?, munk_megjegyzes = ? WHERE munkalap_id = ?";

			pr = conn.prepareStatement(sqlClient);
			pr.setString(1, newWorksheet.getWorksheetStatus());
			pr.setString(2, newWorksheet.getWorksheetEndDate());
			pr.setString(3, newWorksheet.getWorksheetBillable());
			pr.setString(4, newWorksheet.getWorksheetBillableData());
			pr.setInt(5, Integer.parseInt(newWorksheet.getWorksheetId()));
			pr.execute();
		} catch (SQLException ex) {
			System.out.println(ex);
		} finally {
			try {
				if (pr != null) {
					pr.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException ex) {
			}
		}
	}

}
