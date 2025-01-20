package exceptions.core.banking.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class CardUtil {
	private static final String CARD_PREFIX = "4169";
	private static final Random RANDOMIZER = new Random();
	private static final int INITIAL_EXPIRATION = 5;
	
	public static String cardNumberGenerator() {
		StringBuilder cardNumber = new StringBuilder();
		cardNumber.append(CARD_PREFIX);
		for (int i = 4; i < 16; i++) {
			int digit = RANDOMIZER.nextInt(10);
			cardNumber.append(i % 4 == 0 ? "-" : "").append(digit);
		}
		
		return cardNumber.toString();
	}
	
	public static String expireDateGenerator() {
		LocalDate currentDate = LocalDate.now();
		LocalDate expireDate = currentDate.plusYears(INITIAL_EXPIRATION);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/yy");
		return expireDate.format(formatter);
	}
	
	public static String cvvGenerator() {
		StringBuilder cvv = new StringBuilder();
		
		for (int i = 0; i < 3; i++) {
			int digit = RANDOMIZER.nextInt(10);
			cvv.append(digit);
		}
		
		return cvv.toString();
	}
}