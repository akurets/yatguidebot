package org.yetanothertouristguidebot.yatguidebot.service.handler;

import org.telegram.telegrambots.meta.api.objects.Update;

public interface UpdateHandler<T> {
	
	T processUpdate(Update update);
}
