package delivery.app.gateway.user;

import delivery.app.user.AuthenticationServiceApi;
import delivery.app.user.dto.Authority;
import delivery.app.user.dto.UsernameAndPassword;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

@Service
class HttpAuthenticationService implements AuthenticationServiceApi {

  final WebClient    webClient;

  HttpAuthenticationService(WebClient.Builder webClientBuilder) {
    this.webClient = webClientBuilder
        .baseUrl("http://localhost:8081")
        .build();
  }

  @Override
  public Collection<Authority> authenticate(String username, CharSequence password) {
    final UsernameAndPassword payload = new UsernameAndPassword(username, password);

    return webClient
            .post()
            .uri("/api/authenticate")
            .bodyValue(payload)
            .retrieve()
            .bodyToFlux(Authority.class)
            .collectList()
            .block();
  }
}
