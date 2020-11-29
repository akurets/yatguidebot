package org.yetanothertouristguidebot.yatguidebot.bots;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.yetanothertouristguidebot.yatguidebot.service.handler.UpdateHandler;

@Component
@PropertySource("classpath:bot.properties")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class GuideBot extends TelegramLongPollingBot {

	private final UpdateHandler<SendMessage> sendMessageUpdateHandler;

	@Value("${bot.username}")
	private String botUsername;
	@Value("${bot.token}")
	private String botToken;

	@Override
	public String getBotUsername() {
		return botUsername;
	}

	@Override
	public String getBotToken() {
		return botToken;
	}

	@Override
	public void onUpdateReceived(Update update) {
		SendMessage answer = sendMessageUpdateHandler.processUpdate(update);
		try {
			execute(answer);
		} catch (TelegramApiException e) {
			e.printStackTrace();
		}
	}
}
