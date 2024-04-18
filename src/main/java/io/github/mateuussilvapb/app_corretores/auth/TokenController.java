package io.github.mateuussilvapb.app_corretores.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

@RestController
@RequestMapping("/token")
public class TokenController {

    private final WebClient webClient;

    public TokenController(WebClient webClient) {
        this.webClient = webClient;
    }

    @PostMapping()
    public TokenResponse token(@RequestBody User user) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        var formData = BodyInserters.fromFormData("username", user.username()).with("password", user.password()).with("client_id", user.clientID()).with("grant_type", user.grantType());

        return webClient.post().uri("http://localhost:8080/realms/manager/protocol/openid-connect/token").headers(httpHeaders -> httpHeaders.addAll(headers)).body(formData).retrieve().bodyToMono(TokenResponse.class).block();
    }

    public record User(String username, String password, String clientID, String grantType) {
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    private static class TokenResponse {
        public String access_token;
        public int expires_in;
        public int refresh_expires_in;
        public String refresh_token;
        public String token_type;
        public String session_state;
        public String scope;
    }
}