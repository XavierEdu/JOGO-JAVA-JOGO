package tela;

import java.awt.Color;
import java.awt.Graphics2D;

public class Tiro {

    // Atributos do projétil
    private int x;
    private int y;
    private int velocidadeTiro;
    private int tamX = 4;
    private int tamY = 15;

    public void setDistancia(int distancia) {
        this.distancia = distancia;
    }

    int distancia = 0;
    int distanciAtual =0;
    //o tiro anda a 10tiles, o jogo tem 720tiles, sendo  assim em  72ticks o tiro chegará ate o final
    // O construtor irá receber os valores iniciais de X e Y, que é onde o personagem está, aproximadamente
    public Tiro(int inicioX, int inicioY) {
        this.x = inicioX;
        this.y = inicioY;
        velocidadeTiro = 10;

    }

    // Fazendo o projétil com um retângulizinho
    public void paint(Graphics2D g) {
        g.setColor(Color.ORANGE);

        g.fillRect(x, y, tamX, tamY);
    }

    // Movimentação do tiro
    public void atualizar() {
        distanciAtual+=10;
        y -= velocidadeTiro;
        try {
            x += distanciAtual / distancia;
        }catch(Exception e){
            x+=0;
            //angulo 90 =dividir por 0
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
