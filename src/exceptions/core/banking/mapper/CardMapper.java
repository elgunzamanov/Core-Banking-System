package exceptions.core.banking.mapper;

import exceptions.core.banking.model.dto.CardRequestDto;
import exceptions.core.banking.model.dto.CardUser;
import exceptions.core.banking.util.CardUtil;

public class CardMapper {
	public static CardUser toCardUser(CardRequestDto requestDto) {
		return new CardUser(
			CardUtil.cardNumberGenerator(),
			CardUtil.cvvGenerator(),
			requestDto.getFullName(),
			requestDto.getEmail(),
			requestDto.getPhoneNumber(),
			CardUtil.expireDateGenerator(),
			requestDto.getCardType()
		);
	}
}