package application.worker.menu.newworksheet.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import application.setting.database.DataBaseConnect;
import application.worker.menu.newworksheet.pojo.NewWorksheet;

public class WorksheetDataBase {

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

}
