package org.yetanothertouristguidebot.yatguidebot.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.yetanothertouristguidebot.yatguidebot.database.model.CityInfo;

import java.util.List;

@Data
@AllArgsConstructor
public class ResponseDto {

	private String message;
	private List<CityInfo> payload;
}
