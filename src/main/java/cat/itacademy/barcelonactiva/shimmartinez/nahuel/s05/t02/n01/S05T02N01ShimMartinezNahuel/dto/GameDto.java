package cat.itacademy.barcelonactiva.shimmartinez.nahuel.s05.t02.n01.S05T02N01ShimMartinezNahuel.dto;

import cat.itacademy.barcelonactiva.shimmartinez.nahuel.s05.t02.n01.S05T02N01ShimMartinezNahuel.domain.Player;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class GameDto {

    private Player player;
    private int dice1;
    private int dice2;
    private boolean winner;
    private String dateRegistration;

    @Override
    public String toString() {
        return "GameDtoSql(userSql=" + player +
                ", dice1=" + dice1 +
                ", dice2=" + dice2 +
                ", winner=" + winner +
                ", timeRegistration=" + dateRegistration +
                ")";
    }
}
