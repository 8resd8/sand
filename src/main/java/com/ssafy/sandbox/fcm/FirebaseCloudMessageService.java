package com.ssafy.sandbox.fcm;

import org.springframework.stereotype.Component;

import com.google.firebase.messaging.AndroidConfig;
import com.google.firebase.messaging.AndroidNotification;
import com.google.firebase.messaging.ApnsConfig;
import com.google.firebase.messaging.Aps;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class FirebaseCloudMessageService {

	public void sendByToken(FcmServiceDto dto) {
		// String token = getToken(dto.getUsername());
		String token = dto.getUsername();

		Message message = Message.builder()
			.setToken(token)
			.setNotification(
				Notification.builder()
					.setTitle(dto.getTitle())
					.setBody(dto.getContent())
					.build()
			)
			.setAndroidConfig(
				AndroidConfig.builder()
					.setNotification(
						AndroidNotification.builder()
							.setTitle(dto.getTitle())
							.setBody(dto.getContent())
							.setClickAction("push_click")
							.build()
					)
					.build()
			)
			.setApnsConfig(
				ApnsConfig.builder()
					.setAps(Aps.builder()
						.setCategory("push_click")
						.build())
					.build()
			)
			.putData("contentId", dto.getContentId().toString())
			.build();

		try {
			String response = FirebaseMessaging.getInstance().send(message);
			log.info("Success: FCM send- {}", response);
		} catch (FirebaseMessagingException e) {
			log.info("Error: FCM except- {}", e.getMessage());
		}
	}

	// private String getToken(String username) {
	// 	Token token = tokenRepository.findByUsername(username).orElse(null);
	// 	return token.getTokenValue();
	// }
}