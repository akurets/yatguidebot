package org.yetanothertouristguidebot.yatguidebot.controller.factory;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ResponseDtoMessages {
	
	public static final String CITY_INFO_LIST = "Take your cities list";
	public static final String CITY_INFO = "Here you city";
	public static final String NO_CONTENT = "City not found";
	public static final String SUCCESSFULLY_CREATED = "Successfully created";
	public static final String ALREADY_EXIST = "City already exist in database";
	public static final String UPDATED = "CityInfo updated";
	public static final String DELETED = "City deleted";
}
