package org.yetanothertouristguidebot.yatguidebot.controller.factory;

import org.springframework.stereotype.Component;
import org.yetanothertouristguidebot.yatguidebot.controller.dto.ResponseDto;
import org.yetanothertouristguidebot.yatguidebot.database.model.CityInfo;

import java.util.List;

import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static org.yetanothertouristguidebot.yatguidebot.controller.factory.ResponseDtoMessages.CITY_INFO_LIST;

@Component
public class ResponseDtoFactory {

	public ResponseDto createWithPayload(List<CityInfo> cityInfos) {
		return new ResponseDto(CITY_INFO_LIST, cityInfos);
	}

	public ResponseDto createWithPayload(CityInfo cityInfo, String message) {
		return new ResponseDto(message, singletonList(cityInfo));
	}

	public ResponseDto createWithEmptyPayload(String message) {
		return new ResponseDto(message, emptyList());
	}
}
