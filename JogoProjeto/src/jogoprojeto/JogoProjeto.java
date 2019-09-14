package jogoprojeto;

import java.awt.DisplayMode;
import java.awt.GraphicsEnvironment;
import javax.swing.JFrame;
import tela.EvaInSpace;

public class JogoProjeto {

    //Biblioteca do JAVA para "pegar" a dimensão do monitor do usuário
    //monitorPc sempre será chamada quando algo for colocado na tela
    public static DisplayMode monitorPc = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode();

    public static void main(String[] args) {

        //Criação da tela
        JFrame tela = new JFrame("Eva In Space");

        //tela.setSize(1366, 768);
        tela.setExtendedState(JFrame.MAXIMIZED_BOTH);
        tela.setLayout(null);
        tela.setLocationRelativeTo(null);
        tela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        EvaInSpace eva = new EvaInSpace();

        eva.setBounds(0, 0, monitorPc.getWidth(), monitorPc.getHeight());

        tela.add(eva);

        tela.addKeyListener(eva);

        tela.setVisible(true);

    }

}
