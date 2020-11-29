package org.yetanothertouristguidebot.yatguidebot.service.handler;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.yetanothertouristguidebot.yatguidebot.database.model.CityInfo;
import org.yetanothertouristguidebot.yatguidebot.exceptions.CityNotFoundException;
import org.yetanothertouristguidebot.yatguidebot.service.CityInfoService;

import java.util.List;
import java.util.StringJoiner;

import static org.yetanothertouristguidebot.yatguidebot.service.handler.StringConstants.CITY_NOT_FOUND;
import static org.yetanothertouristguidebot.yatguidebot.service.handler.StringConstants.SPECIFY_COUNTRY;
import static org.yetanothertouristguidebot.yatguidebot.service.handler.StringConstants.START_MSG_ANSWER;
import static org.yetanothertouristguidebot.yatguidebot.service.handler.StringConstants.START_MSG_REQUEST;

@Service
@Data
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class SendMessageUpdateHandler implements UpdateHandler<SendMessage> {

	private final CityInfoService dbCityInfoService;
	private String tempNameHolder = Strings.EMPTY;

	@Override
	public SendMessage processUpdate(Update update) {
		SendMessage answer = new SendMessage();
		if (update.hasMessage() && update.getMessage().hasText()) {
			answer.setChatId(String.valueOf(update.getMessage().getChatId()));
			String answerText = getAnswer(update.getMessage().getText());
			answer.setText(answerText);
		}
		return answer;
	}

	private String getAnswer(String message) {
		String answer = START_MSG_ANSWER;
		if (!START_MSG_REQUEST.equals(message)) {
			try {
				answer = tempNameHolder.isBlank() ? getInfoByCity(message) : getInfoByCountry(message);
			} catch (CityNotFoundException ex) {
				answer = CITY_NOT_FOUND;
			}
		}
		return answer;
	}

	private String getInfoByCountry(String message) throws CityNotFoundException {
		CityInfo cityInfo = dbCityInfoService.getByNameAndCountry(tempNameHolder, message);
		tempNameHolder = Strings.EMPTY;
		return cityInfo.getInfo();
	}

	private String getInfoByCity(String message) throws CityNotFoundException {
		String answer;
		List<CityInfo> cityInfos = dbCityInfoService.getByName(message);
		if (cityInfos.size() > 1) {
			tempNameHolder = message;
			answer = getClarifyingQuestion(cityInfos);
		} else {
			CityInfo cityInfo = cityInfos.get(0);
			answer = cityInfo.getInfo();
		}
		return answer;
	}

	private String getClarifyingQuestion(List<CityInfo> cityInfos) {
		StringJoiner question = new StringJoiner("\n");
		question.add(SPECIFY_COUNTRY);
		cityInfos.stream()
				.map(CityInfo::getCountry)
				.forEach(question::add);
		return question.toString();
	}
}
