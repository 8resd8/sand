package com.ssafy.sandbox.fcm;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class FcmController {

	private final FirebaseCloudMessageService firebaseCloudMessageService;

	@PostMapping("/push")
	public void fcmTest(@RequestBody FcmServiceDto request) {
		FcmServiceDto dto = FcmServiceDto.of(request.getUsername(), 1L, "제목", "콘텐츠");
		firebaseCloudMessageService.sendByToken(dto);
	}
}