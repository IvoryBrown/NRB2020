package application.worker.menu.newworksheet.pojo;

import javafx.beans.property.SimpleStringProperty;

public class NewWorksheet {

	private final SimpleStringProperty worksheetId;
	private final SimpleStringProperty clientId;
	private final SimpleStringProperty clientName;
	private final SimpleStringProperty worksheetNumber;
	private final SimpleStringProperty worksheetStartDate;
	private final SimpleStringProperty worksheetEndDate;
	private final SimpleStringProperty worksheetStatus;
	private final SimpleStringProperty worksheetBillable;
	private final SimpleStringProperty worksheetBillableData;
	private final SimpleStringProperty worksheetComment;

	public NewWorksheet(Integer worksheetId, Integer clientId, String worksheetNumber, String worksheetStartDate,
			String worksheetEndDate, String worksheetStatus, String worksheetBillable, String worksheetBillableData,
			String worksheetComment) {
		this.worksheetId = new SimpleStringProperty(String.valueOf(worksheetId));
		this.clientId = new SimpleStringProperty(String.valueOf(clientId));
		this.clientName = new SimpleStringProperty("");
		this.worksheetNumber = new SimpleStringProperty(worksheetNumber);
		this.worksheetStartDate = new SimpleStringProperty(worksheetStartDate);
		this.worksheetEndDate = new SimpleStringProperty(worksheetEndDate);
		this.worksheetStatus = new SimpleStringProperty(worksheetStatus);
		this.worksheetBillable = new SimpleStringProperty(worksheetBillable);
		this.worksheetBillableData = new SimpleStringProperty(worksheetBillableData);
		this.worksheetComment = new SimpleStringProperty(worksheetComment);
	}

	// munkalap add
	public NewWorksheet(String clientId, String worksheetNumber, String worksheetStartDate, String worksheetStatus,
			String worksheetBillable, String worksheetComment) {
		this.worksheetId = new SimpleStringProperty(String.valueOf(""));
		this.clientId = new SimpleStringProperty(clientId);
		this.clientName = new SimpleStringProperty("");
		this.worksheetNumber = new SimpleStringProperty(worksheetNumber);
		this.worksheetStartDate = new SimpleStringProperty(worksheetStartDate);
		this.worksheetEndDate = new SimpleStringProperty("");
		this.worksheetStatus = new SimpleStringProperty(worksheetStatus);
		this.worksheetBillable = new SimpleStringProperty(worksheetBillable);
		this.worksheetBillableData = new SimpleStringProperty("");
		this.worksheetComment = new SimpleStringProperty(worksheetComment);
	}

	// Munkalap sql
	public NewWorksheet(String clientName, Integer worksheetId, String worksheetNumber, String worksheetStartDate,
			String worksheetEndDate, String worksheetStatus, String worksheetBillable, String worksheetBillableData,
			String worksheetComment) {
		this.clientName = new SimpleStringProperty(clientName);
		this.worksheetId = new SimpleStringProperty(String.valueOf(worksheetId));
		this.clientId = new SimpleStringProperty(String.valueOf(""));
		this.worksheetNumber = new SimpleStringProperty(worksheetNumber);
		this.worksheetStartDate = new SimpleStringProperty(worksheetStartDate);
		this.worksheetEndDate = new SimpleStringProperty(worksheetEndDate);
		this.worksheetStatus = new SimpleStringProperty(worksheetStatus);
		this.worksheetBillable = new SimpleStringProperty(worksheetBillable);
		this.worksheetBillableData = new SimpleStringProperty(worksheetBillableData);
		this.worksheetComment = new SimpleStringProperty(worksheetComment);
	}

	public String getWorksheetId() {
		return this.worksheetId.get();
	}

	public void setWorksheetId(String worksheetId) {
		this.worksheetId.set(worksheetId);
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

	public String getWorksheetNumber() {
		return this.worksheetNumber.get();
	}

	public void setWorksheetNumber(String worksheetNumber) {
		this.worksheetNumber.set(worksheetNumber);
	}

	public String getWorksheetStartDate() {
		return this.worksheetStartDate.get();
	}

	public void setWorksheetStartDate(String worksheetStartDate) {
		this.worksheetStartDate.set(worksheetStartDate);
	}

	public String getWorksheetEndDate() {
		return this.worksheetEndDate.get();
	}

	public void setWorksheetEndDate(String worksheetEndDate) {
		this.worksheetEndDate.set(worksheetEndDate);
	}

	public String getWorksheetStatus() {
		return this.worksheetStatus.get();
	}

	public void setWorksheetStatus(String worksheetStatus) {
		this.worksheetStatus.set(worksheetStatus);
	}

	public String getWorksheetBillable() {
		return this.worksheetBillable.get();
	}

	public void setWorksheetBillable(String worksheetBillable) {
		this.worksheetBillable.set(worksheetBillable);
	}

	public String getWorksheetBillableData() {
		return this.worksheetBillableData.get();
	}

	public void setWorksheetBillableData(String worksheetBillableData) {
		this.worksheetBillableData.set(worksheetBillableData);
	}

	public String getWorksheetComment() {
		return this.worksheetComment.get();
	}

	public void setWorksheetComment(String worksheetComment) {
		this.worksheetComment.set(worksheetComment);
	}

}
