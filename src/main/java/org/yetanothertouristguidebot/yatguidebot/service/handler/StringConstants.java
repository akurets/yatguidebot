package org.yetanothertouristguidebot.yatguidebot.service.handler;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class StringConstants {

	public static final String START_MSG_REQUEST = "/start";
	public static final String START_MSG_ANSWER = """
				Hey, folk!
				I am yet another stupid tourist guide bot.
				Just send me city name and i will tell you all about it
				...or will not.
			""";
	public static final String CITY_NOT_FOUND = """
				Sorry, nothing interesting about this city.
				Or city/country name was wrong.
			""";
	public static final String SPECIFY_COUNTRY = "City appears in few countries, please specify country:";
}
