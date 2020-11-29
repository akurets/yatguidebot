package org.yetanothertouristguidebot.yatguidebot.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.yetanothertouristguidebot.yatguidebot.controller.dto.ResponseDto;
import org.yetanothertouristguidebot.yatguidebot.controller.factory.ResponseDtoFactory;
import org.yetanothertouristguidebot.yatguidebot.exceptions.CityAlreadyExistException;
import org.yetanothertouristguidebot.yatguidebot.exceptions.CityNotFoundException;
import org.yetanothertouristguidebot.yatguidebot.service.CityInfoService;
import org.yetanothertouristguidebot.yatguidebot.service.dto.CityInfoDto;

import static org.yetanothertouristguidebot.yatguidebot.controller.factory.ResponseDtoMessages.ALREADY_EXIST;
import static org.yetanothertouristguidebot.yatguidebot.controller.factory.ResponseDtoMessages.CITY_INFO;
import static org.yetanothertouristguidebot.yatguidebot.controller.factory.ResponseDtoMessages.DELETED;
import static org.yetanothertouristguidebot.yatguidebot.controller.factory.ResponseDtoMessages.NO_CONTENT;
import static org.yetanothertouristguidebot.yatguidebot.controller.factory.ResponseDtoMessages.SUCCESSFULLY_CREATED;
import static org.yetanothertouristguidebot.yatguidebot.controller.factory.ResponseDtoMessages.UPDATED;

@Controller
@RequestMapping("api/v1/city_infos")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class CityInfoController {

	private final CityInfoService dbCityInfoService;
	private final ResponseDtoFactory responseDtoFactory;

	@GetMapping
	public ResponseEntity<ResponseDto> get() {
		return ResponseEntity.ok(responseDtoFactory.createWithPayload(dbCityInfoService.getAll()));
	}

	@GetMapping("{id}")
	public ResponseEntity<ResponseDto> getById(@PathVariable Long id) {
		try {
			return ResponseEntity.ok(responseDtoFactory.createWithPayload(dbCityInfoService.getById(id), CITY_INFO));
		} catch (CityNotFoundException e) {
			return ResponseEntity.ok(responseDtoFactory.createWithEmptyPayload(NO_CONTENT));
		}
	}

	@PostMapping("add")
	public ResponseEntity<ResponseDto> add(@RequestBody CityInfoDto cityInfoDto) {
		try {
			return ResponseEntity
					.status(HttpStatus.CREATED)
					.body(responseDtoFactory.createWithPayload(dbCityInfoService.add(cityInfoDto), SUCCESSFULLY_CREATED));
		} catch (CityAlreadyExistException e) {
			return ResponseEntity
					.status(HttpStatus.UNPROCESSABLE_ENTITY)
					.body(responseDtoFactory.createWithEmptyPayload(ALREADY_EXIST));
		}
	}

	@PutMapping("{id}/update")
	public ResponseEntity<ResponseDto> update(@PathVariable Long id, @RequestBody String info) {
		try {
			return ResponseEntity.ok(responseDtoFactory.createWithPayload(dbCityInfoService.update(id, info), UPDATED));
		} catch (CityNotFoundException e) {
			return ResponseEntity
					.status(HttpStatus.UNPROCESSABLE_ENTITY)
					.body(responseDtoFactory.createWithEmptyPayload(NO_CONTENT));
		}
	}

	@DeleteMapping("{id}/delete")
	public ResponseEntity<ResponseDto> delete(@PathVariable Long id) {
		try {
			dbCityInfoService.delete(id);
			return ResponseEntity.ok(responseDtoFactory.createWithEmptyPayload(DELETED));
		} catch (CityNotFoundException e) {
			return ResponseEntity
					.status(HttpStatus.UNPROCESSABLE_ENTITY)
					.body(responseDtoFactory.createWithEmptyPayload(NO_CONTENT));
		}
	}
}