package tela;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

// O nome do inimigo é Angel, referência a Neon Genesis Evangelion
public class Angel {

    // Atributos dos inimigos
    BufferedImage spriteAngel;
    private int x;
    private int y;
    private float velocidade;
    private int direcao;

    public Angel(BufferedImage spriteAngel, int inicioX, int inicioY, int direcao) {

        this.spriteAngel = spriteAngel;
        this.x = inicioX;
        this.y = inicioY;
        this.direcao = direcao;

        this.velocidade = 1;
    }

    // Pintando o inimigo
    public void paint(Graphics2D g) {
        g.drawImage(spriteAngel, x, y, x + 45, y + 45, 0, 0, spriteAngel.getWidth(), spriteAngel.getHeight(), null);
    }

    // Os dois próximos métodos orientam a movimentação do inimigo
    public void atualizar() {
        x += velocidade * direcao;
    }

    public void trocaDirecao() {
        direcao = direcao * -1;

        y += 15;

        velocidade += 0.15f; // Sempre que o inimigo troca de direção ele fica mais rápido. OBS: não tá funcionando bem

    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getTam() {
        return 55;
    }

}
