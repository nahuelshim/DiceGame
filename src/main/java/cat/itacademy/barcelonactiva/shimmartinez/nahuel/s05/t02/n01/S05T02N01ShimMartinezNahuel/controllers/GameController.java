package cat.itacademy.barcelonactiva.shimmartinez.nahuel.s05.t02.n01.S05T02N01ShimMartinezNahuel.controllers;

import cat.itacademy.barcelonactiva.shimmartinez.nahuel.s05.t02.n01.S05T02N01ShimMartinezNahuel.domain.Game;
import cat.itacademy.barcelonactiva.shimmartinez.nahuel.s05.t02.n01.S05T02N01ShimMartinezNahuel.dto.GameDto;
import cat.itacademy.barcelonactiva.shimmartinez.nahuel.s05.t02.n01.S05T02N01ShimMartinezNahuel.dto.PlayerDto;
import cat.itacademy.barcelonactiva.shimmartinez.nahuel.s05.t02.n01.S05T02N01ShimMartinezNahuel.services.GameServices;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/")
public class GameController {
    @Autowired
    private GameServices gameServices;

    @PostMapping({"players"})
    @Operation(
            tags = "POST",
            operationId = "ADD",
            summary = "This action to add new player",
            description = "Please add a userSql and fill the parameters required in Request body.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "This the request body Description",
                    content = @Content(schema = @Schema(implementation = PlayerDto.class))
            ),
            security = {@SecurityRequirement(name = "bearerAuth")}
    )
    public ResponseEntity<String> savePlayer(@RequestBody PlayerDto playerDto) {
        try {
            gameServices.create(playerDto);
            return ResponseEntity.ok("Player saved successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to save.");
        }
    }

    @PutMapping({"players"})
    @Operation(
            tags="UPDATE",
            summary = "This action to update an userSql",
            description = "Please update an userSql and fill the parameters required in Request body : name",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "This the request body Description",
                    content = @Content(schema = @Schema(implementation = PlayerDto.class))),
            security = {@SecurityRequirement(name = "bearerAuth")}
    )
    public ResponseEntity<String> updatePlayer(
            @RequestBody PlayerDto playerDto) {
        try {
            gameServices.update(playerDto);
            return ResponseEntity.ok("Player updated successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to update: " + e.getMessage());
        }
    };

    @PostMapping({"players/{id}/games"})
    @Operation(
            tags="POST",
            operationId="PLAY A GAME",
            summary = "This action to play a game",
            security = {@SecurityRequirement(name = "bearerAuth")}
    )
    public ResponseEntity<String> PlayGame(@PathVariable Long id) {
        try {
            PlayerDto playerDto = gameServices.findPlayerById(id);
            gameServices.createGame(playerDto.getPlayerId());
            return ResponseEntity.ok("Player searched properly");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to search this player: " + e.getMessage());
        }
    };

    @DeleteMapping ({"players/{id}/games"})
    @Operation(
            tags="DELETE",
            operationId="DELETE GAMES OF A PLAYER",
            summary = "This action to delete games of players",
            description = "Please check id and delete all the games of a player",
            security = {@SecurityRequirement(name = "bearerAuth")}
    )
    public ResponseEntity<String> deleteGames(@PathVariable Long id) {

        try {
            gameServices.deleteGamesByPlayerId(id);
            return ResponseEntity.ok("Player searched properly");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to search this player: " + e.getMessage());
        }
    };

    @GetMapping ({"players"})
    @Operation(
            tags="GET",
            operationId="RETURN LIST OF PLAYERS",
            summary = "This action to get list of players",
            description = "Please see the list",
            security = {@SecurityRequirement(name = "bearerAuth")}
    )
    public ResponseEntity<List<PlayerDto>> getAllPlayers(){
        List<PlayerDto> playersDto = gameServices.findAllPlayers();
        if (!playersDto.isEmpty()) {
            return new ResponseEntity<>(playersDto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    };

    @GetMapping ({"players/{id}/games"})
    @Operation(
            tags="GET",
            operationId="RETURN LIST OF ALL GAMES FROM A PLAYER",
            summary = "This action to get list of games from a player",
            description = "Please see the list of games",
            security = {@SecurityRequirement(name = "bearerAuth")}
    )
    public ResponseEntity<List<Game>> getUserGamesById(@PathVariable Long id) {
        Optional<List<Game>> optionalGames = gameServices.findGamesByPlayerId(id);
        if (optionalGames.isPresent()) {
            return new ResponseEntity<>(optionalGames.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

    }

    @GetMapping({"players/ranking"})
    @Operation(
            tags = "GET",
            operationId = "RETURN LIST OF ALL PLAYERS ACCORDING RANKING",
            summary = "This action to get list of players ordered by score",
            description = "Please see the list of players",
            security = {@SecurityRequirement(name = "bearerAuth")}
    )
    public ResponseEntity<List<String>> getUsersRanking() {
        try {
            List<String> ranking = gameServices.calculatePlayersRank();
            if (ranking.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(ranking, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping({"players/ranking/winner"})
    @Operation(
            tags = "GET",
            operationId = "GET USER WINNER",
            summary = "Get the winner of the game",
            description = "Retrieve the username of the game winner",
            security = {@SecurityRequirement(name = "bearerAuth")}
    )
    public ResponseEntity<String> getUserWinner() {
        try {
            String winner = gameServices.rankingWinner();
            if (winner == null) {
                return new ResponseEntity<>("Winner not found", HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>("The winner is: " + winner, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping({"players/ranking/loser"})
    @Operation(
            tags = "GET",
            operationId = "GET USER LOSER",
            summary = "Get the loser of the game",
            description = "Retrieve the username of the game winner",
            security = {@SecurityRequirement(name = "bearerAuth")}
    )
    public ResponseEntity<String> getUserLoser() {
        try {
            String loser = gameServices.rankingLooser();
            if (loser == null) {
                return new ResponseEntity<>("Loser not found", HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>("The loser is: " + loser, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
