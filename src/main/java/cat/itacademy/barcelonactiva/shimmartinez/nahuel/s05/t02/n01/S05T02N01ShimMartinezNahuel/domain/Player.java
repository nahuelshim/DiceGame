package cat.itacademy.barcelonactiva.shimmartinez.nahuel.s05.t02.n01.S05T02N01ShimMartinezNahuel.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "Player")
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "Name")
    @Schema(example = "Anonymous")
    private String name;

    @Column(name ="SuccessRate")
    @Builder.Default
    private double SuccessRate = 0.00;

    @Column (name="Registration")
    private Date dateRegistration= new Date();

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "UserGame",
            joinColumns = @JoinColumn(name = "id"),
            inverseJoinColumns = @JoinColumn(name = "game_id")
    )
    private List<Game> gameList;

    public Player() {
        this.gameList = new ArrayList<>();
    }

    public void addGames(Game game){
        gameList.add(game);
        setSuccessRate();
    }

    public void deleteGames(){
        gameList.clear();
    }

    public void setSuccessRate() {
        double wins = 0.00;
        for (Game game : gameList) {
            if (game.getGameResult() == Game.GameResult.WINNER) {
                wins++;
            }
        }
        double winRate =(double)Math.round( wins/ gameList.size()*100.0);
        this.SuccessRate = winRate;
    }

    public String getName() {
        if (name == null || name.isEmpty() || name.equalsIgnoreCase("string")) {
            return "anonymous";
        }
        return name;
    }

    @Override
    public String toString() {
        return "Player{" +
                "id=" + id +
                ", name='" + name + '\'' + "date registration: " +
                dateRegistration +
                '}';
    }
}

