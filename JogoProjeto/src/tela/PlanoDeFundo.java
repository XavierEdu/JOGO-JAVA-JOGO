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
        try {
            imagem = ImageIO.read(new File("sprites/fundo.png"));
        } catch (IOException e) {
            System.out.println("Não foi possível");
            e.printStackTrace();
        }

        y = 0;
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
