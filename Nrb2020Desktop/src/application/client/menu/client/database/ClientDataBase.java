package application.client.menu.client.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import application.client.menu.client.pojo.Client;
import application.setting.database.DataBaseConnect;

public class ClientDataBase {

	// adatbazis lekerdezes
	public ArrayList<Client> getAllWorker() {
		Connection con = DataBaseConnect.getConnection();
		String sql = null;
		sql = "SELECT * FROM `ugyfel`";

		ArrayList<Client> client = null;
		Statement createStatement = null;
		ResultSet rs = null;
		try {
			createStatement = con.createStatement();
			rs = createStatement.executeQuery(sql);
			client = new ArrayList<>();
			while (rs.next()) {
				Client actualCLient = new Client(rs.getInt("ugyfel_id"), rs.getString("ugyfel_neve"),
						rs.getString("iranyitoszam"), rs.getString("telepules"), rs.getString("cim"),
						rs.getString("email"), rs.getString("mobil"), rs.getString("szamlazo_kod"));
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

	// Ugyfel adatbazis feltoltve
	public void addClient(Client client) {
		Connection con = DataBaseConnect.getConnection();
		PreparedStatement preparedStatement = null;
		try {
			String sql = "INSERT INTO `ugyfel` (ugyfel_neve, iranyitoszam, telepules, cim,"
					+ " email, mobil, szamlazo_kod) VALUES (?,?,?,?,?,?,?)";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setString(1, client.getClientName());
			preparedStatement.setString(2, client.getClientZipCode());
			preparedStatement.setString(3, client.getClientSettlement());
			preparedStatement.setString(4, client.getClientTitle());
			preparedStatement.setString(5, client.getClientEmail());
			preparedStatement.setString(6, client.getClientMobil());
			preparedStatement.setString(7, client.getClientBilling());
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
	public void updateClient(Client client) {
		Connection conn = DataBaseConnect.getConnection();
		PreparedStatement pr = null;
		try {
			String sqlClient = "UPDATE `ugyfel` SET iranyitoszam = ?, telepules = ?, cim = ?,"
					+ "email = ?, mobil = ?, szamlazo_kod = ? WHERE ugyfel_id = ?";

			pr = conn.prepareStatement(sqlClient);
			pr.setString(1, client.getClientZipCode());
			pr.setString(2, client.getClientSettlement());
			pr.setString(3, client.getClientTitle());
			pr.setString(4, client.getClientEmail());
			pr.setString(5, client.getClientMobil());
			pr.setString(6, client.getClientBilling());
			pr.setInt(7, Integer.parseInt(client.getClientId()));
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
