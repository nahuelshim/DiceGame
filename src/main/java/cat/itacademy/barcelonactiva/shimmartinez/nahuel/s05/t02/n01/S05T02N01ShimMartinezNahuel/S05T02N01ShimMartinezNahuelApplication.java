package cat.itacademy.barcelonactiva.shimmartinez.nahuel.s05.t02.n01.S05T02N01ShimMartinezNahuel;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(title = "DICE GAME", version = "1.0.0"),
		servers = {@Server(url = "http://localhost:9000")},
		tags = {@Tag(name = "Dice Game API", description = "This is an API CRUD to Manage USERS and Game results in MySQL")}
)
@SecurityScheme(
		name = "bearerAuth",
		type = SecuritySchemeType.HTTP,
		scheme = "bearer",
		bearerFormat = "JWT"
)
public class S05T02N01ShimMartinezNahuelApplication {

	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}

	public static void main(String[] args) {
		SpringApplication.run(S05T02N01ShimMartinezNahuelApplication.class, args);
	}
}
