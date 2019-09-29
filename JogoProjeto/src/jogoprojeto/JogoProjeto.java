package jogoprojeto;

import javax.swing.JFrame;
import tela.EvaInSpace;

public class JogoProjeto {

    public static void main(String[] args) {

        //Criação da tela
        JFrame tela = new JFrame("Eva In Space");

        tela.setSize(1280, 720);
        tela.setLayout(null);
        tela.setLocationRelativeTo(null);
        tela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        EvaInSpace eva = new EvaInSpace();

        eva.setBounds(0, 0, 1280, 720);

        tela.add(eva);

        tela.addKeyListener(eva);

        tela.setVisible(true);

    }

}
