package application.home.menu.worker.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import application.home.menu.worker.pojo.Worker;
import application.setting.database.DataBaseConnect;

//Dolgozi adatbazis

public class WorkerDataBase {

	// Osszes adatbazis lekerdezes
	public ArrayList<Worker> getAllWorker() {
		Connection con = DataBaseConnect.getConnection();
		String sql = null;
		sql = "SELECT * FROM `user`";

		ArrayList<Worker> worker = null;
		Statement createStatement = null;
		ResultSet rs = null;
		try {
			createStatement = con.createStatement();
			rs = createStatement.executeQuery(sql);
			worker = new ArrayList<>();
			while (rs.next()) {
				Worker actualCLient = new Worker(rs.getInt("id-username"), rs.getString("username"),
						rs.getString("nev"), rs.getString("email"), rs.getString("password"), rs.getString("beosztas"),
						rs.getString("azonosito"));
				worker.add(actualCLient);
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
		return worker;
	}

}
