package com.adsr.learningspring.springlearning.business;

public class GuestInfo {
	private long id;
	private String lastName;
	private String firstName;
	private String emailAddress;
	private String phoneNumber;

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@Override
	public String toString() {
		return "GuestInfo{" +
				"lastName='" + lastName + '\'' +
				", firstName='" + firstName + '\'' +
				", emailAddress='" + emailAddress + '\'' +
				", phoneNumber='" + phoneNumber + '\'' +
				'}';
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
}
