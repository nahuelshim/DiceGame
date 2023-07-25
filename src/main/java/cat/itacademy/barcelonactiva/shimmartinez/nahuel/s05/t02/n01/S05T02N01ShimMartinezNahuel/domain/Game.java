package cat.itacademy.barcelonactiva.shimmartinez.nahuel.s05.t02.n01.S05T02N01ShimMartinezNahuel.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import java.util.Random;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "Game")
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long gameId;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Player player;

    @Column(name = "Dice1")
    private int dice1 = generateRandomNumber();
    @Column(name = "Dice2")
    private int dice2 = generateRandomNumber();
    @Column(name = "Result")
    private GameResult gameResult;

    enum GameResult {
        WINNER, LOSER
    }

    public Game(Player player) {
        this.player = player;
        diceResult();
    }

    private int generateRandomNumber() {
        Random random = new Random();
        return random.nextInt(6) + 1;
    }

    private void diceResult() {
        if (dice1 + dice2 == 7) {
            this.gameResult = GameResult.WINNER;
        } else {
            this.gameResult = GameResult.LOSER;
        }
    }

    @Override
    public String toString() {
        return "GameSql{" +
                "gameId=" + gameId +
                ", userSql=" + player +
                ", dice1=" + dice1 +
                ", dice2=" + dice2 +
                ", resultGame=" + gameResult +
                '}';
    }

}

