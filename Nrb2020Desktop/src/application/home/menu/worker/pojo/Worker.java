package application.home.menu.worker.pojo;

import javafx.beans.property.SimpleStringProperty;

//Worker class 
public class Worker {

	private final SimpleStringProperty workerId;
	private final SimpleStringProperty workerName;
	private final SimpleStringProperty workerUserName;
	private final SimpleStringProperty workerMail;
	private final SimpleStringProperty workerPassword;
	private final SimpleStringProperty workerPost;
	private final SimpleStringProperty workerIdentification;

	//Osszes 
	public Worker(Integer workerId, String workerUserName, String workerName,  String workerMail, String workerPassword,
			String workerPost, String workerIdentification) {
		this.workerId = new SimpleStringProperty(String.valueOf(workerId));
		this.workerName = new SimpleStringProperty(workerName);
		this.workerUserName = new SimpleStringProperty(workerUserName);
		this.workerMail = new SimpleStringProperty(workerMail);
		this.workerPassword = new SimpleStringProperty(workerPassword);
		this.workerPost = new SimpleStringProperty(workerPost);
		this.workerIdentification = new SimpleStringProperty(workerIdentification);
	}

	// Getter-Setter
	// id
	public String getWorkerId() {
		return this.workerId.get();
	}

	public void setWorkerId(String workerId) {
		this.workerId.set(workerId);
	}

	// nev
	public String getWorkerName() {
		return this.workerName.get();
	}

	public void setWorkerName(String workerName) {
		this.workerName.set(workerName);
	}

	// username
	public String getWorkerUserName() {
		return this.workerUserName.get();
	}

	public void setWorkerUserName(String workerUserName) {
		this.workerUserName.set(workerUserName);
	}

	// mail
	public String getWorkerMail() {
		return this.workerMail.get();
	}

	public void setWorkerMail(String workerMail) {
		this.workerMail.set(workerMail);
	}

	// Password
	public String getWorkerPassword() {
		return this.workerPassword.get();
	}

	public void setWorkerPassword(String workerPassword) {
		this.workerPassword.set(workerPassword);
	}

	// beosztas
	public String getWorkerPost() {
		return this.workerPost.get();
	}

	public void setWorkerPost(String workerPost) {
		this.workerPost.set(workerPost);
	}

	// Azzonosito
	public String getWorkerIdentification() {
		return this.workerIdentification.get();
	}

	public void setWorkerIdentification(String workerIdentification) {
		this.workerIdentification.set(workerIdentification);
	}
}
