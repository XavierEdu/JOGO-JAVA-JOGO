package tela;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class PlanoDeFundo {

    private BufferedImage imagem;
    private int y;

    public PlanoDeFundo() {

        // Carregando plano de fundo
        this.imagem =loadImage("/sprites/fundo.png");
        y = 0;
    }
    public BufferedImage loadImage(String fileName){

        BufferedImage buff = null;
        try {
            buff = ImageIO.read(getClass().getResourceAsStream(fileName));
        } catch (IOException e) {
            System.out.println("fudeu1");
            e.printStackTrace();
        }
        return buff;

    }

    // (Gambiarra) colocando imagem na tela para que ela se repita
    // OBS: não está funcionando bem
    public void paint(Graphics2D g) {

        int altura = 720;

        g.drawImage(imagem, 0, y - altura * 2, imagem.getWidth(), imagem.getHeight(), null);
        g.drawImage(imagem, 0, y, imagem.getWidth(), -imagem.getHeight(), null);
        g.drawImage(imagem, 0, y, imagem.getWidth(), imagem.getHeight(), null);

        y++;

        if (y > altura * 2) {
            y = 0;
        }

    }

}
