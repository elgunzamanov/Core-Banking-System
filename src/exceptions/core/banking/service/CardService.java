package exceptions.core.banking.service;

import exceptions.core.banking.exceptions.CardAlreadyExistsException;
import exceptions.core.banking.exceptions.NoCardFoundException;
import exceptions.core.banking.mapper.CardMapper;
import exceptions.core.banking.model.constant.CardType;
import exceptions.core.banking.model.dto.CardRequestDto;
import exceptions.core.banking.model.dto.CardUser;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;
import java.util.regex.Pattern;

public class CardService {
	private static final List<CardUser> CARD_USERS = new ArrayList<>();
	private static final Logger LOGGER = Logger.getLogger(CardService.class.getName());
	private static final String FILE_PATH = "src/card-folder/cards.txt";
	
	public void registerCard(CardRequestDto requestDto) {
		if (!isRequestValid(requestDto)) {
			throw new IllegalArgumentException("Invalid card request details!");
		}
		
		CardUser cardUser = CardMapper.toCardUser(requestDto);
		checkIfCardAlreadyExists(cardUser);
		
		CARD_USERS.add(cardUser);
		LOGGER.info("Card registered successfully!");
		writeRegisteredCardToFile(cardUser, true);
	}

	public void listAllCards() {
		File file = new File(FILE_PATH);
		
		if (!file.exists()) {
			LOGGER.warning("File not found!");
		} else {
			if (file.length() == 0) {
				throw new NoCardFoundException("No card found in the file!");
			}
		}
		
		try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
			String data;
			while (Objects.nonNull((data = bufferedReader.readLine()))) {
				System.out.println(data);
			}
		} catch (IOException ioException) {
			System.err.println("Error reading file: " + ioException.getMessage());
		}
	}
	
	public void deleteCard(String email) {
		for (CardUser cardUser : CARD_USERS) {
			if (email.equals(cardUser.getEmail())) {
				CARD_USERS.remove(cardUser);
			} else {
				throw new NoCardFoundException("No such user card found in the file!");
			}
		}
		updateUserCards();
	}
	
	private boolean isRequestValid(CardRequestDto requestDto) {
		if (Objects.isNull(requestDto)) return false;
		
		return isValidFullName(requestDto.getFullName())
			&& isValidPhoneNumber(requestDto.getPhoneNumber())
			&& isValidEmail(requestDto.getEmail())
			&& isValidCardType(requestDto.getCardType());
	}
	
	private boolean isValidFullName(String fullName) {
		if (Objects.nonNull(fullName)
			&& fullName.length() >= 3
			&& Pattern.matches("^[a-zA-Z]+ [a-zA-Z]+$", fullName)) {
			return true;
		} else {
			throw new IllegalArgumentException("Valid fullname is required!");
		}
	}
	
	private boolean isValidPhoneNumber(String phoneNumber) {
		if (Objects.nonNull(phoneNumber)
			&& Pattern.matches("^\\+994-(010|050|051|055|070|077)-\\d{3}-\\d{2}-\\d{2}$", phoneNumber)) {
			return true;
		} else {
			throw new IllegalArgumentException("Valid phone number is required!");
		}
	}
	
	private boolean isValidEmail(String email) {
		if (Objects.nonNull(email)
			&& Pattern.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{3,}$", email)) {
			return true;
		} else {
			throw new IllegalArgumentException("Valid email is required!");
		}
	}
	
	private boolean isValidCardType(CardType cardType) {
		if (cardType == CardType.PHYSICAL || cardType == CardType.VIRTUAL) {
			return true;
		} else {
			throw new IllegalArgumentException("Valid card type is required!");
		}
	}
	
	public void writeRegisteredCardToFile(CardUser card, boolean append) {
		String data = formatData(maskCardNumber(card), maskCvv(card), card);
		File file = createFile();
		
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, append))) {
			writer.write(data);
			writer.newLine();
			LOGGER.info("Card information successfully written to the file!");
		} catch (IOException e) {
			System.err.println("Error writing to file: " + e.getMessage());
		}
	}
	
	private void checkIfCardAlreadyExists(CardUser cardUser) {
		for (CardUser card : CARD_USERS) {
			if (card.getCardNumber().equals(cardUser.getCardNumber())
				&& card.getEmail().equals(cardUser.getEmail())
				&& card.getPhoneNumber().equals(cardUser.getPhoneNumber())) {
				throw new CardAlreadyExistsException("Card already exists in the system!");
			}
		}
	}
	
	private String maskCardNumber(CardUser card) {
		return "****-****-****-" + card.getCardNumber()
			.substring(card.getCardNumber().length() - 4);
	}
	
	private String maskCvv(CardUser card) {
		return "*".repeat(card.getCvv().length());
	}
	
	private String formatData(String maskedCardNumber, String maskedCvv, CardUser card) {
		return ("""
			Card number: %s
			CVV: %s
			Fullname: %s
			Email: %s
			Phone number: %s
			Expire date: %s
			Card type: %s
			"""
		).formatted(maskedCardNumber, maskedCvv, card.getFullName(),
			card.getEmail(), card.getPhoneNumber(), card.getExpireDate(), card.getCardType()
		);
	}
	
	private File createFile() {
		File file = new File(FILE_PATH);
		File directory = file.getParentFile();
		if (!directory.exists()) directory.mkdirs();
		return file;
	}
	
	private void updateUserCards() {
		for (CardUser cardUser : CARD_USERS) {
			writeRegisteredCardToFile(cardUser, false);
		}
	}
}