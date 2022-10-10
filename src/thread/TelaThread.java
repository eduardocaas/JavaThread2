package src.thread;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class TelaThread extends JDialog{

    private JPanel jPanel = new JPanel(new GridBagLayout()); //painel de componentes
    private JLabel descricaoHora = new JLabel("Time Thread 1");
    private JTextField mostraTempo = new JTextField();
    private JLabel descricaoHora2 = new JLabel("Time Thread 2");
    private JTextField mostraTempo2 = new JTextField();
    private JButton jButton = new JButton("Start");
    private JButton jButton2 = new JButton("Stop");

    private Runnable thread1 = new Runnable() {
        @Override
        public void run() {
            while (true) {
                mostraTempo.setText(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss")
                        .format(Calendar.getInstance().getTime()));
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    };

    private Runnable thread2 = new Runnable() {
        @Override
        public void run() {
            while (true) {
                mostraTempo2.setText(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss")
                        .format(Calendar.getInstance().getTime()));
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    };

    private Thread thread1Time; //instanciado aqui para ser acessivel e criado dentro de todas classes
    private Thread thread2Time;

    public TelaThread(){

        setTitle("Threads");
        setSize(new Dimension(240,240));
        setLocationRelativeTo(null);
        setResizable(false);
        //Primeira parte

        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2; //cria com 2 posições
        gridBagConstraints.insets = new Insets(5, 10, 5 , 5); //semelhante ao padding CSS
        gridBagConstraints.anchor = gridBagConstraints.WEST; //orienta elementos

        descricaoHora.setPreferredSize(new Dimension(200, 25));
        jPanel.add(descricaoHora, gridBagConstraints);

        mostraTempo.setPreferredSize(new Dimension(200, 25));
        mostraTempo.setEditable(false); //nao permite escrita no campo
        gridBagConstraints.gridy++;
        jPanel.add(mostraTempo, gridBagConstraints);

        descricaoHora2.setPreferredSize(new Dimension(200, 25));
        gridBagConstraints.gridy++;
        jPanel.add(descricaoHora2, gridBagConstraints);

        mostraTempo2.setPreferredSize(new Dimension(200, 25));
        mostraTempo2.setEditable(false); //nao permite escrita no campo
        gridBagConstraints.gridy++;
        jPanel.add(mostraTempo2, gridBagConstraints);

        gridBagConstraints.gridwidth = 1; //retorna o valor de 1 para o conteudo abaixo

        jButton.setPreferredSize(new Dimension(92, 25));
        gridBagConstraints.gridy++;
        jPanel.add(jButton, gridBagConstraints);

        jButton2.setPreferredSize(new Dimension(92, 25));
        gridBagConstraints.gridx++;
        jPanel.add(jButton2, gridBagConstraints);

        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { //executa o clique no botao

                thread1Time = new Thread(thread1);
                thread2Time = new Thread(thread2);
                thread1Time.start();
                thread2Time.start();

                jButton.setEnabled(false);
                jButton2.setEnabled(true);

            }
        });

        jButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                thread1Time.stop();
                thread2Time.stop();

                jButton.setEnabled(true);
                jButton2.setEnabled(false);
            }
        });

        jButton2.setEnabled(false);

        add(jPanel, BorderLayout.WEST);
        setVisible(true); //ultimo a ser executado

}
}
