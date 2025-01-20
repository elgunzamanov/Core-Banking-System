package exceptions.core.banking.model.dto;

import exceptions.core.banking.model.constant.CardType;

public class CardUser {
	private String cardNumber;
	private String cvv;
	private String fullName;
	private String email;
	private String phoneNumber;
	private String expireDate;
	private CardType cardType;
	
	public CardUser() {
	}
	
	public CardUser(String cardNumber, String cvv, String fullName, String email, String phoneNumber, String expireDate, CardType cardType) {
		this.cardNumber = cardNumber;
		this.cvv = cvv;
		this.fullName = fullName;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.expireDate = expireDate;
		this.cardType = cardType;
	}
	
	public String getCardNumber() {
		return cardNumber;
	}
	
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	
	public String getCvv() {
		return cvv;
	}
	
	public void setCvv(String cvv) {
		this.cvv = cvv;
	}
	
	public String getFullName() {
		return fullName;
	}
	
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPhoneNumber() {
		return phoneNumber;
	}
	
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	public String getExpireDate() {
		return expireDate;
	}
	
	public void setExpireDate(String expireDate) {
		this.expireDate = expireDate;
	}
	
	public CardType getCardType() {
		return cardType;
	}
	
	public void setCardType(CardType cardType) {
		this.cardType = cardType;
	}
}