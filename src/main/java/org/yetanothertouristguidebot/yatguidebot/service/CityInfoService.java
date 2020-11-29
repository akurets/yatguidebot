package org.yetanothertouristguidebot.yatguidebot.service;

import org.yetanothertouristguidebot.yatguidebot.database.model.CityInfo;
import org.yetanothertouristguidebot.yatguidebot.exceptions.CityAlreadyExistException;
import org.yetanothertouristguidebot.yatguidebot.exceptions.CityNotFoundException;
import org.yetanothertouristguidebot.yatguidebot.service.dto.CityInfoDto;

import java.util.List;

public interface CityInfoService {

	CityInfo add(CityInfoDto cityInfoDto) throws CityAlreadyExistException;

	CityInfo update(Long id, String info) throws CityNotFoundException;

	void delete(Long id) throws CityNotFoundException;
	
	List<CityInfo> getAll();

	CityInfo getById(Long id) throws CityNotFoundException;

	List<CityInfo> getByName(String name) throws CityNotFoundException;

	CityInfo getByNameAndCountry(String name, String country) throws CityNotFoundException;
}
