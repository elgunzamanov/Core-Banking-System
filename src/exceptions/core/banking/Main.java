package exceptions.core.banking;

import exceptions.core.banking.model.constant.CardType;
import exceptions.core.banking.model.dto.CardRequestDto;
import exceptions.core.banking.service.CardService;

public class Main {
	public static void main(String[] args) {
		// TODO: Register the user card.
		CardService cardService = new CardService();
		cardService.registerCard(new CardRequestDto(
			"Lionel Messi",
			"+994-010-100-10-10",
			"leomessi@gmail.com",
			CardType.VIRTUAL
		));

		cardService.registerCard(new CardRequestDto(
			"Cristiano Ronaldo",
			"+994-070-700-07-07",
			"cristianoronaldo@gmail.com",
			CardType.PHYSICAL
		));

		// TODO: Update cards to the file when delete the card.
		cardService.deleteCard("leomessi@gmail.com");
		
		// TODO: Load all cards from the file to the console.
		cardService.listAllCards();
	}
}