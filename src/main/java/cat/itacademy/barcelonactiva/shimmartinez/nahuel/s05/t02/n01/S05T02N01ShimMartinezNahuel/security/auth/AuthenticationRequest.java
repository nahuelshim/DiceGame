package cat.itacademy.barcelonactiva.shimmartinez.nahuel.s05.t02.n01.S05T02N01ShimMartinezNahuel.security.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationRequest {

    private String email;
    private String password;
}
