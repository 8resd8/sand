package com.ssafy.sandbox.email.controller;

import com.ssafy.sandbox.email.dto.Email;
import com.ssafy.sandbox.email.response.FailureResponse;
import com.ssafy.sandbox.email.response.ResponseEmail;
import com.ssafy.sandbox.email.response.SuccessResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class EmailController {

    @PostMapping("/{domain}/email")
    public ResponseEmail getEmail(@PathVariable String domain, @Validated @RequestBody Email email, BindingResult bindingResult) {

        ResponseEmail responseEmail = new ResponseEmail();
        log.info("Eamil: {}", email);
        if (bindingResult.hasErrors()) {
            log.info("error: {}", bindingResult);
            responseEmail.setMessage("이메일 인증 실패");
            return responseEmail;
        }

        log.info("성공, {}", domain);

        responseEmail.setOk(true);
        return responseEmail;
    }
}