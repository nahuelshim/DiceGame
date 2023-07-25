package cat.itacademy.barcelonactiva.shimmartinez.nahuel.s05.t02.n01.S05T02N01ShimMartinezNahuel.services;

import cat.itacademy.barcelonactiva.shimmartinez.nahuel.s05.t02.n01.S05T02N01ShimMartinezNahuel.domain.Game;
import cat.itacademy.barcelonactiva.shimmartinez.nahuel.s05.t02.n01.S05T02N01ShimMartinezNahuel.domain.Player;
import cat.itacademy.barcelonactiva.shimmartinez.nahuel.s05.t02.n01.S05T02N01ShimMartinezNahuel.dto.GameDto;
import cat.itacademy.barcelonactiva.shimmartinez.nahuel.s05.t02.n01.S05T02N01ShimMartinezNahuel.dto.PlayerDto;
import cat.itacademy.barcelonactiva.shimmartinez.nahuel.s05.t02.n01.S05T02N01ShimMartinezNahuel.repository.GameRepository;
import cat.itacademy.barcelonactiva.shimmartinez.nahuel.s05.t02.n01.S05T02N01ShimMartinezNahuel.repository.PlayerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GameServices implements IGameServices {

    @Autowired
    private GameRepository gameRepository;
    @Autowired
    private PlayerRepository playerRepository;
    @Autowired
    private ModelMapper modelMapper;


    @Override
    public void create(PlayerDto playerDto) {
        playerRepository.save(modelMapper.map(playerDto, Player.class));
    }

    @Override
    public void update(PlayerDto playerDto) {
        Player player = convertPlayerDtoToEntity(playerDto);
        playerRepository.save(player);
    }

    @Override
    public PlayerDto findPlayerById(Long id) {
        Optional<Player> optionalPlayer = playerRepository.findById(id);
        PlayerDto playerDto = null;
        if (optionalPlayer.isPresent()) {
            playerDto = convertPlayerEntityToDto(optionalPlayer.get());
        }
        return playerDto;
    }

    @Override
    public GameDto createGame(Long id) {
        Optional<Player> optionalPlayer = playerRepository.findById(id);
        Game newGame = null;
        if (optionalPlayer.isPresent()) {
            Player player = optionalPlayer.get();
            newGame = new Game(player);
            player.addGames(newGame);
            gameRepository.save(newGame);
        }
        return convertGameEntityToDto(newGame);
    }

    @Override
    public void deleteGamesByPlayerId(Long id) {
        Optional<Player> optionalPlayer = playerRepository.findById(id);
        if (optionalPlayer.isPresent()) {
            Player playerSelected = optionalPlayer.get();
            playerSelected.deleteGames();
            playerRepository.save(playerSelected);
        }
    }

    @Override
    public List<PlayerDto> findAllPlayers() {
        return playerRepository.findAll()
                .stream()
                .map(this::convertPlayerEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<List<Game>> findGamesByPlayerId(Long id) {
        List<Game> games = gameRepository.findAllByPlayerId(id);
        return Optional.of(games);
    }

    @Override
    public List<GameDto> rankingAll() {
        return gameRepository.findAll()
                .stream()
                .map(this::convertGameEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<String> calculatePlayersRank() {
        List<Player> players = playerRepository.findAll();
        players.sort(Comparator.comparing(Player::getSuccessRate).reversed());

        List<String> playersRank = new ArrayList<>();
        int rank = 1;
        for (Player player : players) {
            String playerRate = " " + player.getSuccessRate() + " % " + player.getName();
            playersRank.add(rank + "." + playerRate);
            rank++;
        }
        return playersRank;
    }

    @Override
    public String rankingLooser() {
        List<String> rankedPlayers2 = calculatePlayersRank();
        String lastRankedPlayer = null;
        if (!rankedPlayers2.isEmpty()) {
            lastRankedPlayer = rankedPlayers2.get(rankedPlayers2.size() - 1);
        }
        return  lastRankedPlayer;
    }

    @Override
    public String rankingWinner() {
        List<String> rankedPlayers = calculatePlayersRank();
        String firstRankedPlayer = null;
        if (!rankedPlayers.isEmpty()) {
            firstRankedPlayer = rankedPlayers.get(0);
        }
        return firstRankedPlayer;
    }

    public PlayerDto convertPlayerEntityToDto(Player player) {
        return modelMapper.map(player, PlayerDto.class);
    }

    public Player convertPlayerDtoToEntity(PlayerDto playerDto) {
        return modelMapper.map(playerDto, Player.class);
    }

    public GameDto convertGameEntityToDto(Game game) {
        return modelMapper.map(game, GameDto.class);
    }

    public Game convertGameDtoToEntity(GameDto gameDto) {
        return modelMapper.map(gameDto, Game.class);
    }
}
