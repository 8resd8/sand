package com.ssafy.sandbox.social.service;


import com.ssafy.sandbox.social.entity.User;
import com.ssafy.sandbox.social.dto.KakaoUserInfoResponse;
import com.ssafy.sandbox.social.dto.LoginResponse;
import com.ssafy.sandbox.social.dto.ReIssueToken;
import com.ssafy.sandbox.social.dto.VerifyTokenResponse;
import com.ssafy.sandbox.social.repository.UserRepository;
import com.ssafy.sandbox.social.util.JWTUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final JWTUtil jwtUtil;

    public LoginResponse getToken(KakaoUserInfoResponse userInfo) {
        if (!isUser(userInfo.getId())) {
            save(userInfo);
        }

        return jwtUtil.getTokens(userInfo.getId());
    }

    private void save(KakaoUserInfoResponse userInfo) {
        User newUser = User.builder()
                .id(userInfo.getId())
                .nickname(userInfo.getProperties().getNickname())
                .build();
        userRepository.save(newUser);
    }

    private boolean isUser(Long id) {
        return userRepository.existsById(id);
    }

    public VerifyTokenResponse getNickname(String token) {
        String[] data = token.split(" ");
        token = data[1];

        Long userId = jwtUtil.getUserId(token);
        User user = userRepository.findById(userId).orElseThrow();
        return new VerifyTokenResponse(user.getNickname());
    }

    public ReIssueToken reissueToken(String token) {
        String accessToken = jwtUtil.reissue(token);
        return new ReIssueToken(accessToken);
    }
}