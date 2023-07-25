package cat.itacademy.barcelonactiva.shimmartinez.nahuel.s05.t02.n01.S05T02N01ShimMartinezNahuel.security.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository <User, Long> {

    Optional<User> findByEmail (String email);
}
