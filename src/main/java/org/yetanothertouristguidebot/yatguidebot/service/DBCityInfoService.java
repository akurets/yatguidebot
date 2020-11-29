package org.yetanothertouristguidebot.yatguidebot.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yetanothertouristguidebot.yatguidebot.database.model.CityInfo;
import org.yetanothertouristguidebot.yatguidebot.database.repository.CityInfoRepository;
import org.yetanothertouristguidebot.yatguidebot.exceptions.CityAlreadyExistException;
import org.yetanothertouristguidebot.yatguidebot.exceptions.CityNotFoundException;
import org.yetanothertouristguidebot.yatguidebot.service.dto.CityInfoDto;
import org.yetanothertouristguidebot.yatguidebot.service.factory.CityInfoFactory;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class DBCityInfoService implements CityInfoService {

	private final CityInfoRepository cityInfoRepository;
	private final CityInfoFactory cityInfoFactory;

	@Override
	public CityInfo add(CityInfoDto cityInfoDto) throws CityAlreadyExistException {
		String upperCasedName = cityInfoDto.getName().toUpperCase();
		String upperCasedCountry = cityInfoDto.getCountry().toUpperCase();

		Optional<CityInfo> cityInfo = cityInfoRepository.findByNameAndCountryEquals(upperCasedName, upperCasedCountry);
		if (cityInfo.isPresent()) {
			throw new CityAlreadyExistException();
		}

		CityInfo newCityInfo = cityInfoFactory.create(cityInfoDto);

		return cityInfoRepository.save(newCityInfo);
	}

	@Override
	public CityInfo update(Long id, String info) throws CityNotFoundException {
		Optional<CityInfo> cityInfo = cityInfoRepository.findById(id);
		CityInfo modCityInfo = cityInfo.orElseThrow(CityNotFoundException::new);
		modCityInfo.setInfo(info);
		return cityInfoRepository.save(modCityInfo);
	}

	@Override
	public void delete(Long id) throws CityNotFoundException {
		cityInfoRepository.findById(id).orElseThrow(CityNotFoundException::new);
		cityInfoRepository.deleteById(id);
	}

	@Override
	public List<CityInfo> getAll() {
		return cityInfoRepository.findAll();
	}

	@Override
	public CityInfo getById(Long id) throws CityNotFoundException {
		return cityInfoRepository.findById(id).orElseThrow(CityNotFoundException::new);
	}

	@Override
	public List<CityInfo> getByName(String name) throws CityNotFoundException {
		String upperCasedName = name.toUpperCase();
		Optional<List<CityInfo>> cityInfos = cityInfoRepository.findAllByNameEquals(upperCasedName);
		return cityInfos.orElseThrow(CityNotFoundException::new);
	}

	@Override
	public CityInfo getByNameAndCountry(String name, String country) throws CityNotFoundException {
		String upperCasedName = name.toUpperCase();
		String upperCasedCountry = country.toUpperCase();
		Optional<CityInfo> cityInfo = cityInfoRepository.findByNameAndCountryEquals(upperCasedName, upperCasedCountry);
		return cityInfo.orElseThrow(CityNotFoundException::new);
	}
}
