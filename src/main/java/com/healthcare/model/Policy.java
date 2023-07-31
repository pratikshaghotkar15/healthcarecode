package com.healthcare.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Table
@Entity
public class Policy {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int pid;
	private String policyName;
	private String insurancefor ;
	private String insurancecover;
	private int policyperiod;
	private String plan;
	private boolean safeguard;
	private boolean acutecare;
	private boolean diseasemanagement;
	private String date;
	
	@ManyToOne
	private User user;

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public String getInsurancefor() {
		return insurancefor;
	}

	public void setInsurancefor(String insurancefor) {
		this.insurancefor = insurancefor;
	}

	public String getInsurancecover() {
		return insurancecover;
	}

	public void setInsurancecover(String insurancecover) {
		this.insurancecover = insurancecover;
	}

	public int getPolicyperiod() {
		return policyperiod;
	}

	public void setPolicyperiod(int policyperiod) {
		this.policyperiod = policyperiod;
	}

	public String getPlan() {
		return plan;
	}

	public void setPlan(String plan) {
		this.plan = plan;
	}

	public boolean isSafeguard() {
		return safeguard;
	}

	public void setSafeguard(boolean safeguard) {
		this.safeguard = safeguard;
	}

	public boolean isAcutecare() {
		return acutecare;
	}

	public void setAcutecare(boolean acutecare) {
		this.acutecare = acutecare;
	}

	public boolean isDiseasemanagement() {
		return diseasemanagement;
	}

	public void setDiseasemanagement(boolean diseasemanagement) {
		this.diseasemanagement = diseasemanagement;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getPolicyName() {
		return policyName;
	}

	public void setPolicyName(String policyName) {
		this.policyName = policyName;
	}
	
	
	
	
	

}
