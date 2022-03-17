package delivery.app.user.service;

import delivery.app.user.dto.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserService {

  Mono<User> find(String username);

  Flux<User> findAll();
}
