package delivery.app.user.service;

import java.util.Collection;

import delivery.app.user.dto.Authority;
import reactor.core.publisher.Mono;

public interface AuthenticationService {

  Mono<Collection<Authority>> authenticate(String username, CharSequence password);
}
