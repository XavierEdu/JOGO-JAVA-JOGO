package tela;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

class Explosao {

    // Atributos da explosão
    private BufferedImage imagemExp;
    private int x;
    private int y;
    private int duracaoExp;
    private int tempoAnimacao;
    private int linha;
    private int coluna;

    public Explosao(BufferedImage imagem, int x, int y) {
        this.imagemExp = imagem;
        this.x = x;
        this.y = y;

        duracaoExp = 0;

        linha = 0;
        coluna = 0;

        tempoAnimacao = 30; // Definindo quantos frames a animação irá durar
    }

    // Pintar a explosão
    public void paint(Graphics g) {
        g.drawImage(imagemExp, x, y, x + 50, y + 50, 192 * coluna, 192 * linha, (192 * coluna) + 192, (192 * linha) + 192, null);
    }

    // Resposável pelo controle da explosão
    public void atualiza() {
        duracaoExp += 2;

        linha = duracaoExp / 6;

        coluna = duracaoExp % 5;
    }

    // Acabar a explosão
    public boolean acabarExplosao() {
        return duracaoExp >= tempoAnimacao;
    }

}
