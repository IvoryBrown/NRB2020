package application.client.menu.client.pojo;

import javafx.beans.property.SimpleStringProperty;

public class Client {

	private final SimpleStringProperty clientId;
	private final SimpleStringProperty clientName;
	private final SimpleStringProperty clientZipCode;
	private final SimpleStringProperty clientSettlement;
	private final SimpleStringProperty clientTitle;
	private final SimpleStringProperty clientEmail;
	private final SimpleStringProperty clientMobil;
	private final SimpleStringProperty clientBilling;

	public Client(Integer clientId, String clientName, String clientZipCode, String clientSettlement,
			String clientTitle, String clientEmail, String clientMobil, String clientBilling) {
		this.clientId = new SimpleStringProperty(String.valueOf(clientId));
		this.clientName = new SimpleStringProperty(clientName);
		this.clientZipCode = new SimpleStringProperty(clientZipCode);
		this.clientSettlement = new SimpleStringProperty(clientSettlement);
		this.clientTitle = new SimpleStringProperty(clientTitle);
		this.clientEmail = new SimpleStringProperty(clientEmail);
		this.clientMobil = new SimpleStringProperty(clientMobil);
		this.clientBilling = new SimpleStringProperty(clientBilling);
	}

	public Client(String clientName, String clientZipCode, String clientSettlement, String clientTitle,
			String clientEmail, String clientMobil, String clientBilling) {
		this.clientId = new SimpleStringProperty(String.valueOf(""));
		this.clientName = new SimpleStringProperty(clientName);
		this.clientZipCode = new SimpleStringProperty(clientZipCode);
		this.clientSettlement = new SimpleStringProperty(clientSettlement);
		this.clientTitle = new SimpleStringProperty(clientTitle);
		this.clientEmail = new SimpleStringProperty(clientEmail);
		this.clientMobil = new SimpleStringProperty(clientMobil);
		this.clientBilling = new SimpleStringProperty(clientBilling);
	}

	public String getClientId() {
		return this.clientId.get();
	}

	public void setClientId(String clientId) {
		this.clientId.set(clientId);
	}

	public String getClientName() {
		return this.clientName.get();
	}

	public void setClientName(String clientName) {
		this.clientName.set(clientName);
	}

	public String getClientZipCode() {
		return this.clientZipCode.get();
	}

	public void setClientZipCode(String clientIdentification) {
		this.clientZipCode.set(clientIdentification);
	}

	public String getClientSettlement() {
		return this.clientSettlement.get();
	}

	public void setClientSettlement(String clientPostcode) {
		this.clientSettlement.set(clientPostcode);
	}

	public String getClientTitle() {
		return this.clientTitle.get();
	}

	public void setClientTitle(String clientAddress) {
		this.clientTitle.set(clientAddress);
	}

	public String getClientEmail() {
		return this.clientEmail.get();
	}

	public void setClientEmail(String clientEmail) {
		this.clientEmail.set(clientEmail);
	}

	public String getClientMobil() {
		return this.clientMobil.get();
	}

	public void setClientMobil(String clientMobil) {
		this.clientMobil.set(clientMobil);
	}

	public String getClientBilling() {
		return this.clientBilling.get();
	}

	public void setClientBilling(String clientBilling) {
		this.clientBilling.set(clientBilling);
	}

}
