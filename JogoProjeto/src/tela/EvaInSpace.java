package tela;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Timer;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class EvaInSpace extends JPanel implements Runnable, KeyListener {
    // Atributos do jogo

    private Eva01 eva01; // Objeto do jogador
    private int direcao; // Direção de movimento do jogador
    private ArrayList<Tiro> tiros; // Array de projeteis
    private ArrayList<Angel> angels; // Array de inimigos
    private ArrayList<Explosao> explosao; // Array de explosões
    private PlanoDeFundo fundo; // Objeto do plano de fundo
    private boolean ganhou; // Boolean resposável pela vitória
    private boolean perder; // Boolean responsável pela derrota
    private Font fonte = new Font("TimesRoman", Font.BOLD, 20); // Fonte que irá aparecer g.drawString
    private BufferedImage imagemExp; // Carregar a imagem da explosão
    private float tempoFechar = 5; // O tempo que o jogo ficará aberto até que feche autormaticamente

    String easter = null; // Easter egg
    int angulo = 90;
    int score = 0; //score
    int vida = 2; //vidas
    long lastTime = System.currentTimeMillis();
    long tempoDecorrido = 0;
    int inimigosMortos = 0;
    public boolean taQuaseMorrendo = false;
    public static boolean acertou = false;
    BufferedImage spriteAngel = null; //sprite do inimigo
    public EvaInSpace() {
        ganhou = false;

        perder = false;

        fundo = new PlanoDeFundo();

        eva01 = new Eva01();

        tiros = new ArrayList<Tiro>();

        angels = new ArrayList<Angel>();

        explosao = new ArrayList<Explosao>();

        // Neste caso, a imagem do inimigo está sendo carregada nesta classe,
        // pois como o número de inimigos é grande, é melhor deixar com que o jogo já tenha
        // a imagem carregada para que toda vez a classe Angel não tenha que gerar
        try {
            spriteAngel = ImageIO.read(new File("sprites/inimigos/Angel03/0.png"));
            imagemExp = ImageIO.read(new File("sprites/explosao.png"));
        } catch (IOException e) {
            System.out.println("Não foi possível");
            e.printStackTrace();
        }

        // Lógica utilizada para spawnar os inimigos em fileiras
        for (int i = 0; i < 60; i++) {
            angels.add(new Angel(spriteAngel, 50 + (i % 20) * 60, 17 + (i / 20) * 60, 1));
        }

        Thread repeatJogo = new Thread(this); // Thread que irá rodar o jogo
        lastTime = System.nanoTime();
        repeatJogo.start();
    }

    @Override
    public void run() {

        // Neste método chamamos os métodos repaint() e update() responsáveis por estarem atualizando o jogo
        while (true) {

            long tempoInicio = System.currentTimeMillis();

            update();

            repaint();

            long tempoFinal = System.currentTimeMillis();
            //deltatime = lastTime - (System.nanoTime() - lastTime);
            tempoDecorrido = System.nanoTime() - lastTime;
            tempoDecorrido = tempoDecorrido/1000000000;
            long diferenca = 16 - (tempoFinal - tempoInicio); // Isto fará com o que o jogo mantenha
            //System.out.println(tempoDecorrido*0.000000001);
            try {
                score = Math.toIntExact((inimigosMortos * (1000 / tempoDecorrido)));
            }catch(Exception e ){
                //gambiarra fodase ( nos primeiros minutos vai dividir por zero fodase)
            }
            if (diferenca > 0) {
                dormir(diferenca); // Ele irá dormir a diferença para garantir que o jogo rode da mesma maneira em outros computadores
            }
        }

    }

    private void update() {

        // Se o número de inimigos na tela for igual a 0, o jogo termina com vitória
        if (angels.size() == 0) {
            ganhou = true;
        }

        eva01.movimenta(direcao); // Vai movimentar de acordo com o valor de direção, atribuido nos métodos de KeyPressed

        for (int i = 0; i < angels.size(); i++) {
            angels.get(i).atualizar(); // Movimentação do inimigo

            // Quando os inimigos atingirem certa posição na tela, o jogador perde o jogo
            if (angels.get(i).getY() >= 720 - 125) {

                vida--;
                if(vida ==0&&taQuaseMorrendo==false){
                    perguntas pergunta = new perguntas();
                    pergunta.iniciar();
                    if(acertou == true){
                        System.out.println("yareyare");
                        taQuaseMorrendo=true;
                        vida++;
                    }else{
                        System.out.println("zipzopperdi");
                        perder = true;
                    }
                }
                if(taQuaseMorrendo==true&& vida == 0) {
                    perder = true;
                }
                else{
                    //resetar o arraylist de inimigos
                    angels = new ArrayList<Angel>();
                    for (int j = 0; j < 60; j++) {
                        angels.add(new Angel(spriteAngel, 50 + (j % 20) * 60, 17 + (j / 20) * 60, 1));
                    }
                }
            }

        }

        for (int i = 0; i < tiros.size(); i++) {
            tiros.get(i).atualizar(); // Movimentação do tiro

            if (tiros.get(i).apagar()) {
                tiros.remove(i); // Remove o tiro quando sai da tela

                i--;
            } else {

                for (int j = 0; j < angels.size(); j++) {

                    // Quando o projétil colide com o inimigo ele desaparece e o inimigo também
                    if (tiros.get(i).colide(angels.get(j))) {

                        //Quando o inimigo é destruído, se cria uma animação de explosão na mesma posição onde o inimigo destruído estava
                        explosao.add(new Explosao(imagemExp, angels.get(j).getX(), angels.get(j).getY()));

                        angels.remove(j);

                        inimigosMortos++;

                        j--;

                        tiros.remove(i);

                        break;

                    }

                }

            }

        }

        // Aqui, o inimigo irá trocar a direção de sua movimentação
        for (int i = 0; i < angels.size(); i++) {
            if (angels.get(i).getX() <= 0 || angels.get(i).getX() >= 1280 - 53) {
                for (int j = 0; j < angels.size(); j++) {
                    angels.get(j).trocaDirecao();
                }

                break;

            }

        }

        // O for irá percorrer a lista de explosão e a condicional irá remover ela da tela
        // depois de certo limite de tempo
        for (int i = 0; i < explosao.size(); i++) {
            explosao.get(i).atualiza();

            if (explosao.get(i).acabarExplosao()) {
                explosao.remove(i);

                i--;
            }
        }
    }

    // Método resposável por desenhar tudo na tela
    @Override
    public void paintComponent(Graphics g2) {
        super.paintComponent(g2);

        Graphics2D g = (Graphics2D) g2.create();

        // As duas próximas linhas nem eu entendi, peguei da net
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        fundo.paint(g); // Pintar fundo

        eva01.paint(g); // Pintar jogador

        g.setColor(Color.GREEN);
        g.setFont(fonte);
        g.drawString("ÂNGULO: " + angulo + "°", (1280 / 2) - 620, 720 / 2 + 300);



        g.drawString("SCORE: " + score, (1280-200) , (720-300) );

        g.drawString("VIDAS: " + vida, (1280 / 2) - 620 , 40);
        // Pintando os inimigos
        for (int i = 0; i < angels.size(); i++) {
            angels.get(i).paint(g);

        }

        // Pintando os tiros
        for (int i = 0; i < tiros.size(); i++) {
            tiros.get(i).paint(g);

        }

        // Pintando as explosões
        for (int i = 0; i < explosao.size(); i++) {
            explosao.get(i).paint(g);
        }

        // Pintando texto avisando sobre a vitória do jogador
        if (ganhou) {
            g.setColor(Color.white); // Colocando cor do texto como branco
            g.setFont(fonte); // Setando a fonte

            g.drawString("YOU WIN!! FECHANDO EM: " + tempoFechar, (1280 / 2) - 165, 720 / 2);

            tempoFechar -= 0.01666f;

            if (tempoFechar <= 0) {
                System.exit(0); // Fecha o jogo
            }

        }

        // Pintando texto avisando sobre a derrota do jogador
        if (perder) {
            g.setColor(Color.white); // Colocando cor do texto como branco
            g.setFont(fonte); // Setando a fonte

            g.drawString("VOCÊ É HORRÍVEL!! FECHANDO EM: " + tempoFechar, (1280 / 2) - 180, 720 / 2);

            tempoFechar -= 0.01666f;

            if (tempoFechar <= 0) {
                System.exit(0); // Fecha o jogo
            }
        }

    }

    // O método dormir é utilizado para que o jogo rode em certa "velocidade"
    private void dormir(long duracao) {
        try {
            Thread.sleep(duracao);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    // Métodos para as teclas apertadas
    @Override
    public void keyPressed(KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_D) {
            direcao = 1;
        }

        if (e.getKeyCode() == KeyEvent.VK_A) {
            direcao = -1;
        }

        if (e.getKeyCode() == KeyEvent.VK_SPACE && eva01.podeAtirar()) {
            Tiro aux = eva01.atirar(angulo);
            tiros.add(aux);

            eva01.podeAtirar();
        }

        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            angulo += 15;

            if (angulo > 135) {
                angulo = 135;
            }
        }

        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            angulo -= 15;

            if (angulo < 45) {
                angulo = 45;
            }
        }
        if(e.getKeyCode() == KeyEvent.VK_O){
            perguntas pergunta1 = new perguntas();
            pergunta1.iniciar();
            if(acertou == true){
                System.out.println("yareyare");

            }else{
                System.out.println("zipzopperdi");
            }
        }

        // Easter Egg
        if (e.getKeyCode() == KeyEvent.VK_X) {
            easter = "X";
        }

        if (e.getKeyCode() == KeyEvent.VK_A && "X".equals(easter)) {
            easter += "A";
        }

        if (e.getKeyCode() == KeyEvent.VK_V && "XA".equals(easter)) {
            easter += "V";
        }

        if (e.getKeyCode() == KeyEvent.VK_I && "XAV".equals(easter)) {
            easter += "I";
        }

        if (e.getKeyCode() == KeyEvent.VK_E && "XAVI".equals(easter)) {
            easter += "E";
        }

        if (e.getKeyCode() == KeyEvent.VK_R && "XAVIE".equals(easter)) {
            easter += "R";
        }

        // Se acertar o Easter Egg, todos os inimigos são destruídos e você ganha automaticamente :D
        if ("XAVIER".equals(easter)) {
            for (int i = 0; i < angels.size(); i++) {
                angels.remove(i);

                i--;
            }

            ganhou = true;
        }

    }

    // Quando a tecla não está pressionada, o personagem para
    @Override
    public void keyReleased(KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_D) {
            direcao = 0;
        }

        if (e.getKeyCode() == KeyEvent.VK_A) {
            direcao = 0;
        }

    }

}
