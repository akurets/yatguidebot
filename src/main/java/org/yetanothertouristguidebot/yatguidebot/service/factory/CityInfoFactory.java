package org.yetanothertouristguidebot.yatguidebot.service.factory;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import org.yetanothertouristguidebot.yatguidebot.database.model.CityInfo;
import org.yetanothertouristguidebot.yatguidebot.service.dto.CityInfoDto;

@Component
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CityInfoFactory {
	
	public CityInfo create(CityInfoDto cityInfoDto) {
		CityInfo cityInfo = new CityInfo();
		cityInfo.setName(cityInfoDto.getName().toUpperCase());
		cityInfo.setCountry(cityInfoDto.getCountry().toUpperCase());
		cityInfo.setInfo(cityInfoDto.getInfo());
		return cityInfo;
	}
}
