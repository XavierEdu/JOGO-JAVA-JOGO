package tela;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Eva01 {

    // Atributos do jogador
    BufferedImage spriteEva;

    public int getX() {
        return x;
    }

    private int x;
    private int velocidade;
    private boolean podeAtirar;
    private int recarregar;

    // Carregar imagem do personagem
    public Eva01() {
        try {
            spriteEva = ImageIO.read(new File("sprites/EVA01/9.png"));
        } catch (IOException e) {
            System.out.println("Não foi possível");
            e.printStackTrace();
        }

        x = 590;
        velocidade = 3;
        podeAtirar = true; // O atributo podeAtirar começa verdadeiro
        recarregar = 0;

    }

    // Método para pintar o jogador na tela
    public void paint(Graphics2D g) {

        g.drawImage(spriteEva, x, 780 - 160, x + 60, 780 - 160 + 60, 0, 0, spriteEva.getWidth(), spriteEva.getHeight(), null);

    }

    // Método para realizar disparo
    public Tiro atirar(int angulo) {

        // Depois que o jogador realiza um tiro, o atributo podeAtirar se torna falso até que se passem 13 frames
        podeAtirar = false;

        recarregar = 0;

        Tiro novoTiro = new Tiro(x + 29, 780 - 160, angulo);

        return novoTiro;

    }

    // Este método irá receber como parâmetro a direção, caso seja 1, o personagem vai pra direita
    // Caso -1, o personagem vai para esquerda
    public void movimenta(int valor) {

        if (valor == 1) {
            x += velocidade;
        } else if (valor == -1) {
            x -= velocidade;
        }

        // Limite de tela
        if (x <= 0) {
            x = 0;
        }
        if (x >= 1210) {
            x = 1210;
        }

        //Verificação para ver se o personagem já pode atirar novamente, isso limita seus tiros
        if (recarregar >= 13) {
            podeAtirar = true;
            recarregar = 0;
        }

        recarregar++;

    }

    public boolean podeAtirar() {
        return podeAtirar;
    }

}
