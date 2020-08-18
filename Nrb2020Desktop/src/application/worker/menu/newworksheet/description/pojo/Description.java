package application.worker.menu.newworksheet.description.pojo;

import javafx.beans.property.SimpleStringProperty;

public class Description {

	private final SimpleStringProperty descriptionId;
	private final SimpleStringProperty worksheetId;
	private final SimpleStringProperty descriptionStartDate;
	private final SimpleStringProperty descriptionStatus;
	private final SimpleStringProperty descriptionComment;

	public Description(Integer descriptionId, Integer worksheetId, String descriptionStartDate,
			String descriptionStatus, String descriptionComment) {
		this.descriptionId = new SimpleStringProperty(String.valueOf(descriptionId));
		this.worksheetId = new SimpleStringProperty(String.valueOf(worksheetId));
		this.descriptionStartDate = new SimpleStringProperty(descriptionStartDate);
		this.descriptionStatus = new SimpleStringProperty(descriptionStatus);
		this.descriptionComment = new SimpleStringProperty(descriptionComment);
	}
	//add data
	public Description( Integer worksheetId, String descriptionStartDate,
			String descriptionStatus, String descriptionComment) {
		this.descriptionId = new SimpleStringProperty("");
		this.worksheetId = new SimpleStringProperty(String.valueOf(worksheetId));
		this.descriptionStartDate = new SimpleStringProperty(descriptionStartDate);
		this.descriptionStatus = new SimpleStringProperty(descriptionStatus);
		this.descriptionComment = new SimpleStringProperty(descriptionComment);
	}
//Table 


	public String getDescriptionId() {
		return this.descriptionId.get();
	}

	public void setDescriptionId(String descriptionId) {
		this.descriptionId.set(descriptionId);
	}

	public String getWorksheetId() {
		return this.worksheetId.get();
	}

	public void setWorksheetId(String worksheetId) {
		this.worksheetId.set(worksheetId);
	}

	public String getDescriptionStartDate() {
		return this.descriptionStartDate.get();
	}

	public void setDescriptionStartDate(String descriptionStartDate) {
		this.descriptionStartDate.set(descriptionStartDate);
	}

	public String getDescriptionStatus() {
		return this.descriptionStatus.get();
	}

	public void setDescriptionStatus(String descriptionStatus) {
		this.descriptionStatus.set(descriptionStatus);
	}

	public String getDescriptionComment() {
		return this.descriptionComment.get();
	}

	public void setDescriptionComment(String descriptionComment) {
		this.descriptionComment.set(descriptionComment);
	}
}