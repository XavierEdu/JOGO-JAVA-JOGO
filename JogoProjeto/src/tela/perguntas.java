package tela;

import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;

import classesauxiliares.Pergunta;

public class perguntas extends JDialog {
    private static long x;
    private JPanel contentPane;
    private JButton btn1;
    private JButton btn2;
    private JButton btn3;
    private JButton btn4;
    private JLabel labelPergunta;
    public Pergunta perguntaAtual;
    public boolean acertou = true;

    public perguntas() {
        setContentPane(contentPane);
        setModal(true);
        btn1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });

        btn2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        btn1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(perguntaAtual.getRespostaCerta()==1){
                    EvaInSpace.acertou = true;
                }else{
                    EvaInSpace.acertou = false;
                }

                onOK();
            }
        });
        btn2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(perguntaAtual.getRespostaCerta());
                if(perguntaAtual.getRespostaCerta()==2){
                    EvaInSpace.acertou = true;
                }else{
                    EvaInSpace.acertou = false;
                }

                onOK();
            }
        });
        btn3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(perguntaAtual.getRespostaCerta()==3){
                    System.out.println("alo");
                    EvaInSpace.acertou = true;
                }else{
                    EvaInSpace.acertou = false;
                }

                onOK();
            }
        });
        btn4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(perguntaAtual.getRespostaCerta());
                if(perguntaAtual.getRespostaCerta()==4){
                    EvaInSpace.acertou = true;
                }else{
                    EvaInSpace.acertou = false;
                }
                onOK();
            }
        });
    }

    private void onOK() {
        // add your code here
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public void iniciar(){
        System.out.println("entrou");
        ArrayList<Pergunta> alPergunta = new ArrayList<Pergunta>();
        alPergunta.add(new Pergunta("pergunta 1", "1", "2", "3", "4", 3));
        alPergunta.add(new Pergunta("pergunta 2", "1", "2", "3", "4", 3));
        Random rand = new Random();
        x=rand.nextInt(alPergunta.size());
        // /\ parte braçal
        perguntaAtual = alPergunta.get((int) x); //cast long  -> int
        labelPergunta.setText(perguntaAtual.getQuestao());
        btn1.setText(perguntaAtual.getAlternativa1());
        btn2.setText(perguntaAtual.getAlternativa2());
        btn3.setText(perguntaAtual.getAlternativa3());
        btn4.setText(perguntaAtual.getAlternativa4());
        System.out.println("questao correta: " + perguntaAtual.getRespostaCerta());

        this.pack();
        this.setVisible(true);
    }
    public static long getRandomIntegerBetweenRange(double min, double max){
        long x = Math.round(Math.random()*((max-min)+1)+min);
        return x;
    }
}
