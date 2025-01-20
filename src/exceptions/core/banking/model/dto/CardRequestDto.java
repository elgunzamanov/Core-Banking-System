package exceptions.core.banking.model.dto;

import exceptions.core.banking.model.constant.CardType;

public class CardRequestDto {
	private String fullName;
	private String phoneNumber;
	private String email;
	private CardType cardType;
	
	public CardRequestDto() {
	}
	
	public CardRequestDto(String fullName, String phoneNumber, String email, CardType cardType) {
		this.fullName = fullName;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.cardType = cardType;
	}
	
	public String getFullName() {
		return fullName;
	}
	
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	
	public String getPhoneNumber() {
		return phoneNumber;
	}
	
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public CardType getCardType() {
		return cardType;
	}
	
	public void setCardType(CardType cardType) {
		this.cardType = cardType;
	}
}