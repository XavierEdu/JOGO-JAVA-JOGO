package tela;

import java.awt.Color;
import java.awt.Graphics2D;

public class Tiro {

    // Atributos do projétil
    private int x;
    private int y;
    private int velocidadeTiro;
    private int tamX = 6;
    private int tamY = 8;
    private int angulo;

    //o tiro anda a 10tiles, o jogo tem 720tiles, sendo  assim em  72ticks o tiro chegará ate o final
    // O construtor irá receber os valores iniciais de X e Y, que é onde o personagem está, aproximadamente
    public Tiro(int inicioX, int inicioY, int angulo) {
        this.x = inicioX;
        this.y = inicioY;
        this.angulo = angulo;
        velocidadeTiro = 10;

    }

    // Fazendo o projétil com um retângulizinho
    public void paint(Graphics2D g) {
        g.setColor(Color.ORANGE);

        g.fillRect(x, y, tamX, tamY);
    }

    // Movimentação do tiro
    public void atualizar() {
        if (angulo == 90) {
            y -= velocidadeTiro;
        } else if (angulo > 90 && angulo <= 105) {
            y -= velocidadeTiro;
            x -= 2;
        } else if (angulo > 100 && angulo <= 120) {
            y -= velocidadeTiro;
            x -= 4;
        } else if (angulo > 110 && angulo <= 135) {
            y -= velocidadeTiro;
            x -= 12;
        } else if (angulo < 90 && angulo >= 75) {
            y -= velocidadeTiro;
            x += 2;
        } else if (angulo < 75 && angulo >= 60) {
            y -= velocidadeTiro;
            x += 4;
        } else if (angulo < 60 && angulo <= 45) {
            y -= velocidadeTiro;
            x += 12;
        }
    }

// Retorna um boolean caso o tiro já tenha saído da tela
    public boolean apagar() {
        return y < 0;
    }

    // Verificação para provar colisão do projetil com o inimigo
    public boolean colide(Angel angels) {
        if (x >= angels.getX() && x + tamX < angels.getX() + angels.getTam()) {
            if (y <= angels.getY() + angels.getTam()) {
                return true;
            }
        }

        return false;
    }

}
