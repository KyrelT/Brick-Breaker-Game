package main.View;

import main.Controller.PlayerControl;
import main.Model.Player.PlayerModel;

import java.awt.*;

public class PlayerView {
    public void drawPlayer(PlayerControl p, Graphics2D g2d){
        Color tmp = g2d.getColor();

        Shape s = p.getPlayerFace();
        g2d.setColor(p.getInnerColor());
        g2d.fill(s);

        g2d.setColor(p.getBorderColor());
        g2d.draw(s);

        g2d.setColor(tmp);
    }
}
