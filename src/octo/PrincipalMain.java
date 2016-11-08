/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package octo;

import java.awt.BorderLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author anyel
 */
public class PrincipalMain implements MouseListener {

    public JFrame PantallaInicio = new JFrame("Octo");
    String[] f = new String[]{"f0.png", "f1.png", "f2.png", "f3.png", "f4.png", "f5.png"};
    static int FIL;
    static int COL;
    static int POS_INIT = 50;
    static int[][] TABLERO;
    Ficha[][] fich;
    JLabel[][] fix;
    JLabel containerFicha;
    static int vacio;
    static int turno;
    static int rival;
    static int dup;
    static int salt;
    static int nextPlay;
    static int posAI;
    static int posAJ;

    public PrincipalMain() {

        Tablero obj = new Tablero();
        FIL = obj.FIL;
        COL = obj.COL;
        TABLERO = obj.tablero;
        fich = new Ficha[FIL][];
        fix = new JLabel[FIL][];
        for (int i = 0; i < FIL; i++) {
            fich[i] = new Ficha[COL];
            fix[i] = new JLabel[COL];
        }
        containerFicha = new JLabel();
        turno = 2;
        rival = 3;
        dup = 4;
        salt = 5;
        nextPlay = 0;
        posAI = 0;
        posAJ = 0;
        vacio = 1;
    }

    public void principal() {
        PantallaInicio.setLocation(0, 0);
        PantallaInicio.setSize(850, 800);

        containerFicha.setBounds(5, 5, 850, 800);
        initTable(fich, containerFicha, fix);
        containerFicha.setVisible(true);
        PantallaInicio.add(containerFicha);

        containerFicha.addMouseListener(this);
        for (int i = 0; i < FIL; i++) {
            for (int j = 0; j < COL; j++) {
                fix[i][j].addMouseListener(this);
            }
        }
        PantallaInicio.setVisible(true);
        CerrarFrame();
    }

    public void initTable(Ficha[][] fich, JLabel containerFicha, JLabel[][] fix) {
        int posX = POS_INIT;
        int posY = POS_INIT;
        Ficha objFich = new Ficha();
        for (int i = 0; i < FIL; i++) {
            posX = POS_INIT;
            for (int j = 0; j < COL; j++) {
                switch (TABLERO[i][j]) {
                    case 0:
                        fich[i][j] = new Ficha();
                        fix[i][j] = fich[i][j].setFicha(posX, posY, f[0], containerFicha, fix[i][j]);
                        fix[i][j].setVisible(true);
                        break;
                    case 1:
                        fich[i][j] = new Ficha();
                        fix[i][j] = fich[i][j].setFicha(posX, posY, f[1], containerFicha, fix[i][j]);
                        fix[i][j].setVisible(true);
                        break;
                    case 2:
                        fich[i][j] = new Ficha();
                        fix[i][j] = fich[i][j].setFicha(posX, posY, f[2], containerFicha, fix[i][j]);
                        fix[i][j].setVisible(true);
                        break;
                    case 3:
                        fich[i][j] = new Ficha();
                        fix[i][j] = fich[i][j].setFicha(posX, posY, f[3], containerFicha, fix[i][j]);
                        fix[i][j].setVisible(true);
                        break;
                    case 4:
                        fich[i][j] = new Ficha();
                        fix[i][j] = fich[i][j].setFicha(posX, posY, f[4], containerFicha, fix[i][j]);
                        fix[i][j].setVisible(true);
                        break;
                    case 5:
                        fich[i][j] = new Ficha();
                        fix[i][j] = fich[i][j].setFicha(posX, posY, f[5], containerFicha, fix[i][j]);
                        fix[i][j].setVisible(true);
                        break;

                }
                posX += objFich.TAM_H;
                System.out.print("(" + i + "," + j + ") " + TABLERO[i][j] + " ");
            }
            posY += objFich.TAM_V;
            System.out.println("");
        }
    }

    public void CerrarFrame() {
        PantallaInicio.addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent evt) {
                System.exit(0);
            }
        });

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        /*  if (e.getSource() == containerFicha){
                    JOptionPane.showMessageDialog(null, "¡INICIO!");
                }*/
 /* System.out.println(e.getSource());
       if (e.getSource() == fix[0][0]){
            JOptionPane.showMessageDialog(null, "¡INICIO!");
        }*/
        Boolean valFront = false;
        for (int i = 0; i < FIL; i++) {
            for (int j = 0; j < COL; j++) {
                /*
                    //validar cambio de fichas
                    *1) Establecer las barreras 
                    *2) Establecer multiplicado de pieza
                    *3) Establecer salto de pieza
                 */
                if (TABLERO[i][j] != 0) {
                    if (e.getSource() == fix[i][j] && TABLERO[i][j] == turno && nextPlay == 0 && verificarPerdio()) {
                        //JOptionPane.showMessageDialog(null, "¡INICIO!");
                        if (i == 0 && !valFront) {
                            valFront = true;
                            //duplicar
                            posAI = i;
                            posAJ = j;
                            if (TABLERO[i][j - 1] != 0 && TABLERO[i][j - 1] == 1) {
                                nextPlay = 1;
                                fich[i][j - 1].setNewImgFich(f[dup], fix[i][j - 1]);
                                TABLERO[i][j - 1] = dup;
                            }
                            if (TABLERO[i][j + 1] != 0 && TABLERO[i][j + 1] == 1) {
                                nextPlay = 1;
                                fich[i][j + 1].setNewImgFich(f[dup], fix[i][j + 1]);
                                TABLERO[i][j + 1] = dup;
                            }
                            if (TABLERO[i + 1][j] != 0 && TABLERO[i + 1][j] == 1) {
                                nextPlay = 1;
                                fich[i + 1][j].setNewImgFich(f[dup], fix[i + 1][j]);
                                TABLERO[i + 1][j] = dup;
                            }
                            if (TABLERO[i + 1][j - 1] != 0 && TABLERO[i + 1][j - 1] == 1) {
                                nextPlay = 1;
                                fich[i + 1][j - 1].setNewImgFich(f[dup], fix[i + 1][j - 1]);
                                TABLERO[i + 1][j - 1] = dup;
                            }
                            if (TABLERO[i + 1][j + 1] != 0 && TABLERO[i + 1][j + 1] == 1) {
                                nextPlay = 1;
                                fich[i + 1][j + 1].setNewImgFich(f[dup], fix[i + 1][j + 1]);
                                TABLERO[i + 1][j + 1] = dup;
                            }
                            //saltar
                            if (TABLERO[i][j - 2] != 0 && TABLERO[i][j - 2] == 1) {
                                nextPlay = 1;
                                fich[i][j - 2].setNewImgFich(f[salt], fix[i][j - 2]);
                                TABLERO[i][j - 2] = salt;
                            }
                            if (TABLERO[i + 1][j - 2] != 0 && TABLERO[i + 1][j - 2] == 1) {
                                nextPlay = 1;
                                fich[i + 1][j - 2].setNewImgFich(f[salt], fix[i + 1][j - 2]);
                                TABLERO[i + 1][j - 2] = salt;
                            }
                            if (TABLERO[i + 2][j - 2] != 0 && TABLERO[i + 2][j - 2] == 1) {
                                nextPlay = 1;
                                fich[i + 2][j - 2].setNewImgFich(f[salt], fix[i + 2][j - 2]);
                                TABLERO[i + 2][j - 2] = salt;
                            }
                            if (TABLERO[i + 2][j - 1] != 0 && TABLERO[i + 2][j - 1] == 1) {
                                nextPlay = 1;
                                fich[i + 2][j - 1].setNewImgFich(f[salt], fix[i + 2][j - 1]);
                                TABLERO[i + 2][j - 1] = salt;
                            }
                            if (TABLERO[i + 2][j] != 0 && TABLERO[i + 2][j] == 1) {
                                nextPlay = 1;
                                fich[i + 2][j].setNewImgFich(f[salt], fix[i + 2][j]);
                                TABLERO[i + 2][j] = salt;
                            }
                            if (TABLERO[i + 2][j + 1] != 0 && TABLERO[i + 2][j + 1] == 1) {
                                nextPlay = 1;
                                fich[i + 2][j + 1].setNewImgFich(f[salt], fix[i + 2][j + 1]);
                                TABLERO[i + 2][j + 1] = salt;
                            }
                            if (TABLERO[i + 2][j + 2] != 0 && TABLERO[i + 2][j + 2] == 1) {
                                nextPlay = 1;
                                fich[i + 2][j + 2].setNewImgFich(f[salt], fix[i + 2][j + 2]);
                                TABLERO[i + 2][j + 2] = salt;
                            }
                            if (TABLERO[i + 2][j + 2] != 0 && TABLERO[i + 2][j + 2] == 1) {
                                nextPlay = 1;
                                fich[i + 2][j + 2].setNewImgFich(f[salt], fix[i + 2][j + 2]);
                                TABLERO[i + 2][j + 2] = salt;
                            }
                            if (TABLERO[i][j + 2] != 0 && TABLERO[i][j + 2] == 1) {
                                nextPlay = 1;
                                fich[i][j + 2].setNewImgFich(f[salt], fix[i][j + 2]);
                                TABLERO[i][j + 2] = salt;
                            }
                        }
                        if (i == (FIL - 1) && !valFront) {
                            //duplicar
                            posAI = i;
                            posAJ = j;
                            valFront = true;
                            if (TABLERO[i][j - 1] != 0 && TABLERO[i][j - 1] == 1) {
                                nextPlay = 1;
                                fich[i][j - 1].setNewImgFich(f[dup], fix[i][j - 1]);
                                TABLERO[i][j - 1] = dup;
                            }
                            if (TABLERO[i][j + 1] != 0 && TABLERO[i][j + 1] == 1) {
                                nextPlay = 1;
                                fich[i][j + 1].setNewImgFich(f[dup], fix[i][j + 1]);
                                TABLERO[i][j + 1] = dup;
                            }
                            if (TABLERO[i - 1][j - 1] != 0 && TABLERO[i - 1][j - 1] == 1) {
                                nextPlay = 1;
                                fich[i - 1][j - 1].setNewImgFich(f[dup], fix[i - 1][j - 1]);
                                TABLERO[i - 1][j - 1] = dup;
                            }
                            if (TABLERO[i - 1][j] != 0 && TABLERO[i - 1][j] == 1) {
                                nextPlay = 1;
                                fich[i - 1][j].setNewImgFich(f[dup], fix[i - 1][j]);
                                TABLERO[i - 1][j] = dup;
                            }
                            if (TABLERO[i - 1][j + 1] != 0 && TABLERO[i - 1][j + 1] == 1) {
                                nextPlay = 1;
                                fich[i - 1][j + 1].setNewImgFich(f[dup], fix[i - 1][j + 1]);
                                TABLERO[i - 1][j + 1] = dup;
                            }
                            //saltar
                            if (TABLERO[i][j - 2] != 0 && TABLERO[i][j - 2] == 1) {
                                nextPlay = 1;
                                fich[i][j - 2].setNewImgFich(f[salt], fix[i][j - 2]);
                                TABLERO[i][j - 2] = salt;
                            }
                            if (TABLERO[i - 1][j - 2] != 0 && TABLERO[i - 1][j - 2] == 1) {
                                nextPlay = 1;
                                fich[i - 1][j - 2].setNewImgFich(f[salt], fix[i - 1][j - 2]);
                                TABLERO[i - 1][j - 2] = salt;
                            }
                            if (TABLERO[i - 2][j - 2] != 0 && TABLERO[i - 2][j - 2] == 1) {
                                nextPlay = 1;
                                fich[i - 2][j - 2].setNewImgFich(f[salt], fix[i - 2][j - 2]);
                                TABLERO[i - 2][j - 2] = salt;
                            }
                            if (TABLERO[i - 2][j - 1] != 0 && TABLERO[i - 2][j - 1] == 1) {
                                nextPlay = 1;
                                fich[i - 2][j - 1].setNewImgFich(f[salt], fix[i - 2][j - 1]);
                                TABLERO[i - 2][j - 1] = salt;
                            }
                            if (TABLERO[i - 2][j] != 0 && TABLERO[i - 2][j] == 1) {
                                nextPlay = 1;
                                fich[i - 2][j].setNewImgFich(f[salt], fix[i - 2][j]);
                                TABLERO[i - 2][j] = salt;
                            }
                            if (TABLERO[i - 2][j + 1] != 0 && TABLERO[i - 2][j + 1] == 1) {
                                nextPlay = 1;
                                fich[i - 2][j + 1].setNewImgFich(f[salt], fix[i - 2][j + 1]);
                                TABLERO[i - 2][j + 1] = salt;
                            }
                            if (TABLERO[i - 2][j + 2] != 0 && TABLERO[i - 2][j + 2] == 1) {
                                nextPlay = 1;
                                fich[i - 2][j + 2].setNewImgFich(f[salt], fix[i - 2][j + 2]);
                                TABLERO[i - 2][j + 2] = salt;
                            }
                            if (TABLERO[i - 1][j + 2] != 0 && TABLERO[i - 1][j + 2] == 1) {
                                nextPlay = 1;
                                fich[i - 1][j + 2].setNewImgFich(f[salt], fix[i - 1][j + 2]);
                                TABLERO[i - 1][j + 2] = salt;
                            }
                            if (TABLERO[i][j + 2] != 0 && TABLERO[i][j + 2] == 1) {
                                nextPlay = 1;
                                fich[i][j + 2].setNewImgFich(f[salt], fix[i][j + 2]);
                                TABLERO[i][j + 2] = salt;
                            }
                        }
                        if (j == 0 && !valFront) {
                            valFront = true;
                            //duplicar
                            posAI = i;
                            posAJ = j;
                            if (TABLERO[i - 1][j] != 0 && TABLERO[i - 1][j] == 1) {
                                nextPlay = 1;
                                fich[i - 1][j].setNewImgFich(f[dup], fix[i - 1][j]);
                                TABLERO[i - 1][j] = dup;
                            }
                            if (TABLERO[i - 1][j + 1] != 0 && TABLERO[i - 1][j + 1] == 1) {
                                nextPlay = 1;
                                fich[i - 1][j + 1].setNewImgFich(f[dup], fix[i - 1][j + 1]);
                                TABLERO[i - 1][j + 1] = dup;
                            }
                            if (TABLERO[i][j + 1] != 0 && TABLERO[i][j + 1] == 1) {
                                nextPlay = 1;
                                fich[i][j + 1].setNewImgFich(f[dup], fix[i][j + 1]);
                                TABLERO[i][j + 1] = dup;
                            }
                            if (TABLERO[i + 1][j + 1] != 0 && TABLERO[i + 1][j + 1] == 1) {
                                nextPlay = 1;
                                fich[i + 1][j + 1].setNewImgFich(f[dup], fix[i + 1][j + 1]);
                                TABLERO[i + 1][j + 1] = dup;
                            }
                            if (TABLERO[i + 1][j] != 0 && TABLERO[i + 1][j] == 1) {
                                nextPlay = 1;
                                fich[i + 1][j].setNewImgFich(f[dup], fix[i + 1][j]);
                                TABLERO[i + 1][j] = dup;
                            }
                            //saltar
                            if (TABLERO[i - 2][j] != 0 && TABLERO[i - 2][j] == 1) {
                                nextPlay = 1;
                                fich[i - 2][j].setNewImgFich(f[salt], fix[i - 2][j]);
                                TABLERO[i - 2][j] = salt;
                            }
                            if (TABLERO[i - 2][j + 1] != 0 && TABLERO[i - 2][j + 1] == 1) {
                                nextPlay = 1;
                                fich[i - 2][j + 1].setNewImgFich(f[salt], fix[i - 2][j + 1]);
                                TABLERO[i - 2][j + 1] = salt;
                            }
                            if (TABLERO[i - 2][j + 2] != 0 && TABLERO[i - 2][j + 2] == 1) {
                                nextPlay = 1;
                                fich[i - 2][j + 2].setNewImgFich(f[salt], fix[i - 2][j + 2]);
                                TABLERO[i - 2][j + 2] = salt;
                            }
                            if (TABLERO[i - 1][j + 2] != 0 && TABLERO[i - 1][j + 2] == 1) {
                                nextPlay = 1;
                                fich[i - 1][j + 2].setNewImgFich(f[salt], fix[i - 1][j + 2]);
                                TABLERO[i - 1][j + 2] = salt;
                            }
                            if (TABLERO[i + 2][j] != 0 && TABLERO[i + 2][j] == 1) {
                                nextPlay = 1;
                                fich[i + 2][j].setNewImgFich(f[salt], fix[i + 2][j]);
                                TABLERO[i + 2][j] = salt;
                            }
                            if (TABLERO[i + 2][j + 1] != 0 && TABLERO[i + 2][j + 1] == 1) {
                                nextPlay = 1;
                                fich[i + 2][j + 1].setNewImgFich(f[salt], fix[i + 2][j + 1]);
                                TABLERO[i + 2][j + 1] = salt;
                            }
                            if (TABLERO[i + 2][j + 2] != 0 && TABLERO[i + 2][j + 2] == 1) {
                                nextPlay = 1;
                                fich[i + 2][j + 2].setNewImgFich(f[salt], fix[i + 2][j + 2]);
                                TABLERO[i + 2][j + 2] = salt;
                            }
                            if (TABLERO[i + 1][j + 2] != 0 && TABLERO[i + 1][j + 2] == 1) {
                                nextPlay = 1;
                                fich[i + 1][j + 2].setNewImgFich(f[salt], fix[i + 1][j + 2]);
                                TABLERO[i + 1][j + 2] = salt;
                            }
                            if (TABLERO[i][j + 2] != 0 && TABLERO[i][j + 2] == 1) {
                                nextPlay = 1;
                                fich[i][j + 2].setNewImgFich(f[salt], fix[i][j + 2]);
                                TABLERO[i][j + 2] = salt;
                            }
                        }
                        if (j == (COL - 1) && !valFront) {
                            valFront = true;
                            //duplicar
                            posAI = i;
                            posAJ = j;
                            if (TABLERO[i - 1][j - 1] != 0 && TABLERO[i - 1][j - 1] == 1) {
                                nextPlay = 1;
                                fich[i - 1][j - 1].setNewImgFich(f[dup], fix[i - 1][j - 1]);
                                TABLERO[i - 1][j - 1] = dup;
                            }
                            if (TABLERO[i - 1][j] != 0 && TABLERO[i - 1][j] == 1) {
                                nextPlay = 1;
                                fich[i - 1][j].setNewImgFich(f[dup], fix[i - 1][j]);
                                TABLERO[i - 1][j] = dup;
                            }
                            if (TABLERO[i][j - 1] != 0 && TABLERO[i][j - 1] == 1) {
                                nextPlay = 1;
                                fich[i][j - 1].setNewImgFich(f[dup], fix[i][j - 1]);
                                TABLERO[i][j - 1] = dup;
                            }
                            if (TABLERO[i + 1][j - 1] != 0 && TABLERO[i + 1][j - 1] == 1) {
                                nextPlay = 1;
                                fich[i + 1][j - 1].setNewImgFich(f[dup], fix[i + 1][j - 1]);
                                TABLERO[i + 1][j - 1] = dup;
                            }
                            if (TABLERO[i + 1][j] != 0 && TABLERO[i + 1][j] == 1) {
                                nextPlay = 1;
                                fich[i + 1][j].setNewImgFich(f[dup], fix[i + 1][j]);
                                TABLERO[i + 1][j] = dup;
                            }
                            //saltar
                            if (TABLERO[i][j - 2] != 0 && TABLERO[i][j - 2] == 1) {
                                nextPlay = 1;
                                fich[i][j - 2].setNewImgFich(f[salt], fix[i][j - 2]);
                                TABLERO[i][j - 2] = salt;
                            }
                            if (TABLERO[i + 1][j - 2] != 0 && TABLERO[i + 1][j - 2] == 1) {
                                nextPlay = 1;
                                fich[i + 1][j - 2].setNewImgFich(f[salt], fix[i + 1][j - 2]);
                                TABLERO[i + 1][j - 2] = salt;
                            }
                            if (TABLERO[i + 2][j - 2] != 0 && TABLERO[i + 2][j - 2] == 1) {
                                nextPlay = 1;
                                fich[i + 2][j - 2].setNewImgFich(f[salt], fix[i + 2][j - 2]);
                                TABLERO[i + 2][j - 2] = salt;
                            }
                            if (TABLERO[i + 2][j - 1] != 0 && TABLERO[i + 2][j - 1] == 1) {
                                nextPlay = 1;
                                fich[i + 2][j - 1].setNewImgFich(f[salt], fix[i + 2][j - 1]);
                                TABLERO[i + 2][j - 1] = salt;
                            }
                            if (TABLERO[i + 2][j] != 0 && TABLERO[i + 2][j] == 1) {
                                nextPlay = 1;
                                fich[i + 2][j].setNewImgFich(f[salt], fix[i + 2][j]);
                                TABLERO[i + 2][j] = salt;
                            }
                            if (TABLERO[i - 1][j - 2] != 0 && TABLERO[i - 1][j - 2] == 1) {
                                nextPlay = 1;
                                fich[i - 1][j - 2].setNewImgFich(f[salt], fix[i - 1][j - 2]);
                                TABLERO[i - 1][j - 2] = salt;
                            }
                            if (TABLERO[i - 2][j - 2] != 0 && TABLERO[i - 2][j - 2] == 1) {
                                nextPlay = 1;
                                fich[i - 2][j - 2].setNewImgFich(f[salt], fix[i - 2][j - 2]);
                                TABLERO[i - 2][j - 2] = salt;
                            }
                            if (TABLERO[i - 2][j - 1] != 0 && TABLERO[i - 2][j - 1] == 1) {
                                nextPlay = 1;
                                fich[i - 2][j - 1].setNewImgFich(f[salt], fix[i - 2][j - 1]);
                                TABLERO[i - 2][j - 1] = salt;
                            }
                            if (TABLERO[i - 2][j] != 0 && TABLERO[i - 2][j] == 1) {
                                nextPlay = 1;
                                fich[i - 2][j].setNewImgFich(f[salt], fix[i - 2][j]);
                                TABLERO[i - 2][j] = salt;
                            }
                        }
                        //···························································
                        if (i == 1 && !valFront) {
                            nextPlay = 1;
                            valFront = true;
                            //duplicar
                            posAI = i;
                            posAJ = j;
                            if (TABLERO[i][j - 1] != 0 && TABLERO[i][j - 1] == 1) {
                                nextPlay = 1;
                                fich[i][j - 1].setNewImgFich(f[dup], fix[i][j - 1]);
                                TABLERO[i][j - 1] = dup;
                            }
                            if (TABLERO[i][j + 1] != 0 && TABLERO[i][j + 1] == 1) {
                                nextPlay = 1;
                                fich[i][j + 1].setNewImgFich(f[dup], fix[i][j + 1]);
                                TABLERO[i][j + 1] = dup;
                            }
                            if (TABLERO[i + 1][j] != 0 && TABLERO[i + 1][j] == 1) {
                                nextPlay = 1;
                                fich[i + 1][j].setNewImgFich(f[dup], fix[i + 1][j]);
                                TABLERO[i + 1][j] = dup;
                            }
                            if (TABLERO[i + 1][j - 1] != 0 && TABLERO[i + 1][j - 1] == 1) {
                                nextPlay = 1;
                                fich[i + 1][j - 1].setNewImgFich(f[dup], fix[i + 1][j - 1]);
                                TABLERO[i + 1][j - 1] = dup;
                            }
                            if (TABLERO[i + 1][j + 1] != 0 && TABLERO[i + 1][j + 1] == 1) {
                                nextPlay = 1;
                                fich[i + 1][j + 1].setNewImgFich(f[dup], fix[i + 1][j + 1]);
                                TABLERO[i + 1][j + 1] = dup;
                            }
                            if (TABLERO[i - 1][j - 1] != 0 && TABLERO[i - 1][j - 1] == 1) {
                                nextPlay = 1;
                                fich[i - 1][j - 1].setNewImgFich(f[dup], fix[i - 1][j - 1]);
                                TABLERO[i - 1][j - 1] = dup;
                            }
                            if (TABLERO[i - 1][j] != 0 && TABLERO[i - 1][j] == 1) {
                                nextPlay = 1;
                                fich[i - 1][j].setNewImgFich(f[dup], fix[i - 1][j]);
                                TABLERO[i - 1][j] = dup;
                            }
                            if (TABLERO[i - 1][j + 1] != 0 && TABLERO[i - 1][j + 1] == 1) {
                                nextPlay = 1;
                                fich[i - 1][j + 1].setNewImgFich(f[dup], fix[i - 1][j + 1]);
                                TABLERO[i - 1][j + 1] = dup;
                            }
                            //saltar
                            if (TABLERO[i][j - 2] != 0 && TABLERO[i][j - 2] == 1) {
                                nextPlay = 1;
                                fich[i][j - 2].setNewImgFich(f[salt], fix[i][j - 2]);
                                TABLERO[i][j - 2] = salt;
                            }
                            if (TABLERO[i + 1][j - 2] != 0 && TABLERO[i + 1][j - 2] == 1) {
                                nextPlay = 1;
                                fich[i + 1][j - 2].setNewImgFich(f[salt], fix[i + 1][j - 2]);
                                TABLERO[i + 1][j - 2] = salt;
                            }
                            if (TABLERO[i + 2][j - 2] != 0 && TABLERO[i + 2][j - 2] == 1) {
                                nextPlay = 1;
                                fich[i + 2][j - 2].setNewImgFich(f[salt], fix[i + 2][j - 2]);
                                TABLERO[i + 2][j - 2] = salt;
                            }
                            if (TABLERO[i + 2][j - 1] != 0 && TABLERO[i + 2][j - 1] == 1) {
                                nextPlay = 1;
                                fich[i + 2][j - 1].setNewImgFich(f[salt], fix[i + 2][j - 1]);
                                TABLERO[i + 2][j - 1] = salt;
                            }
                            if (TABLERO[i + 2][j] != 0 && TABLERO[i + 2][j] == 1) {
                                nextPlay = 1;
                                fich[i + 2][j].setNewImgFich(f[salt], fix[i + 2][j]);
                                TABLERO[i + 2][j] = salt;
                            }
                            if (TABLERO[i + 2][j + 1] != 0 && TABLERO[i + 2][j + 1] == 1) {
                                nextPlay = 1;
                                fich[i + 2][j + 1].setNewImgFich(f[salt], fix[i + 2][j + 1]);
                                TABLERO[i + 2][j + 1] = salt;
                            }
                            if (TABLERO[i + 2][j + 2] != 0 && TABLERO[i + 2][j + 2] == 1) {
                                nextPlay = 1;
                                fich[i + 2][j + 2].setNewImgFich(f[salt], fix[i + 2][j + 2]);
                                TABLERO[i + 2][j + 2] = salt;
                            }
                            if (TABLERO[i + 2][j + 2] != 0 && TABLERO[i + 2][j + 2] == 1) {
                                nextPlay = 1;
                                fich[i + 2][j + 2].setNewImgFich(f[salt], fix[i + 2][j + 2]);
                                TABLERO[i + 2][j + 2] = salt;
                            }
                            if (TABLERO[i][j + 2] != 0 && TABLERO[i][j + 2] == 1) {
                                nextPlay = 1;
                                fich[i][j + 2].setNewImgFich(f[salt], fix[i][j + 2]);
                                TABLERO[i][j + 2] = salt;
                            }
                            if (TABLERO[i - 1][j - 2] != 0 && TABLERO[i - 1][j - 2] == 1) {
                                nextPlay = 1;
                                fich[i - 1][j - 2].setNewImgFich(f[salt], fix[i - 1][j - 2]);
                                TABLERO[i - 1][j - 2] = salt;
                            }
                            if (TABLERO[i - 1][j + 2] != 0 && TABLERO[i - 1][j + 2] == 1) {
                                nextPlay = 1;
                                fich[i - 1][j + 2].setNewImgFich(f[salt], fix[i - 1][j + 2]);
                                TABLERO[i - 1][j + 2] = salt;
                            }
                        }
                        if (i == (FIL - 2) && !valFront) {
                            //duplicar
                            posAI = i;
                            posAJ = j;
                            valFront = true;
                            if (TABLERO[i][j - 1] != 0 && TABLERO[i][j - 1] == 1) {
                                nextPlay = 1;
                                fich[i][j - 1].setNewImgFich(f[dup], fix[i][j - 1]);
                                TABLERO[i][j - 1] = dup;
                            }
                            if (TABLERO[i][j + 1] != 0 && TABLERO[i][j + 1] == 1) {
                                nextPlay = 1;
                                fich[i][j + 1].setNewImgFich(f[dup], fix[i][j + 1]);
                                TABLERO[i][j + 1] = dup;
                            }
                            if (TABLERO[i - 1][j - 1] != 0 && TABLERO[i - 1][j - 1] == 1) {
                                nextPlay = 1;
                                fich[i - 1][j - 1].setNewImgFich(f[dup], fix[i - 1][j - 1]);
                                TABLERO[i - 1][j - 1] = dup;
                            }
                            if (TABLERO[i - 1][j] != 0 && TABLERO[i - 1][j] == 1) {
                                nextPlay = 1;
                                fich[i - 1][j].setNewImgFich(f[dup], fix[i - 1][j]);
                                TABLERO[i - 1][j] = dup;
                            }
                            if (TABLERO[i - 1][j + 1] != 0 && TABLERO[i - 1][j + 1] == 1) {
                                nextPlay = 1;
                                fich[i - 1][j + 1].setNewImgFich(f[dup], fix[i - 1][j + 1]);
                                TABLERO[i - 1][j + 1] = dup;
                            }
                            if (TABLERO[i + 1][j - 1] != 0 && TABLERO[i + 1][j - 1] == 1) {
                                nextPlay = 1;
                                fich[i + 1][j - 1].setNewImgFich(f[dup], fix[i + 1][j - 1]);
                                TABLERO[i + 1][j - 1] = dup;
                            }
                            if (TABLERO[i + 1][j] != 0 & TABLERO[i + 1][j] == 1) {
                                nextPlay = 1;
                                fich[i + 1][j].setNewImgFich(f[dup], fix[i + 1][j]);
                                TABLERO[i + 1][j] = dup;
                            }
                            if (TABLERO[i + 1][j + 1] != 0 && TABLERO[i + 1][j + 1] == 1) {
                                nextPlay = 1;
                                fich[i + 1][j + 1].setNewImgFich(f[dup], fix[i + 1][j + 1]);
                                TABLERO[i + 1][j + 1] = dup;
                            }
                            //saltar
                            if (TABLERO[i][j - 2] != 0 && TABLERO[i][j - 2] == 1) {
                                nextPlay = 1;
                                fich[i][j - 2].setNewImgFich(f[salt], fix[i][j - 2]);
                                TABLERO[i][j - 2] = salt;
                            }
                            if (TABLERO[i - 1][j - 2] != 0 && TABLERO[i - 1][j - 2] == 1) {
                                nextPlay = 1;
                                fich[i - 1][j - 2].setNewImgFich(f[salt], fix[i - 1][j - 2]);
                                TABLERO[i - 1][j - 2] = salt;
                            }
                            if (TABLERO[i - 2][j - 2] != 0 && TABLERO[i - 2][j - 2] == 1) {
                                nextPlay = 1;
                                fich[i - 2][j - 2].setNewImgFich(f[salt], fix[i - 2][j - 2]);
                                TABLERO[i - 2][j - 2] = salt;
                            }
                            if (TABLERO[i - 2][j - 1] != 0 && TABLERO[i - 2][j - 1] == 1) {
                                nextPlay = 1;
                                fich[i - 2][j - 1].setNewImgFich(f[salt], fix[i - 2][j - 1]);
                                TABLERO[i - 2][j - 1] = salt;
                            }
                            if (TABLERO[i - 2][j] != 0 && TABLERO[i - 2][j] == 1) {
                                nextPlay = 1;
                                fich[i - 2][j].setNewImgFich(f[salt], fix[i - 2][j]);
                                TABLERO[i - 2][j] = salt;
                            }
                            if (TABLERO[i - 2][j + 1] != 0 && TABLERO[i - 2][j + 1] == 1) {
                                nextPlay = 1;
                                fich[i - 2][j + 1].setNewImgFich(f[salt], fix[i - 2][j + 1]);
                                TABLERO[i - 2][j + 1] = salt;
                            }
                            if (TABLERO[i - 2][j + 2] != 0 && TABLERO[i - 2][j + 2] == 1) {
                                nextPlay = 1;
                                fich[i - 2][j + 2].setNewImgFich(f[salt], fix[i - 2][j + 2]);
                                TABLERO[i - 2][j + 2] = salt;
                            }
                            if (TABLERO[i - 1][j + 2] != 0 && TABLERO[i - 1][j + 2] == 1) {
                                nextPlay = 1;
                                fich[i - 1][j + 2].setNewImgFich(f[salt], fix[i - 1][j + 2]);
                                TABLERO[i - 1][j + 2] = salt;
                            }
                            if (TABLERO[i][j + 2] != 0 && TABLERO[i][j + 2] == 1) {
                                nextPlay = 1;
                                fich[i][j + 2].setNewImgFich(f[salt], fix[i][j + 2]);
                                TABLERO[i][j + 2] = salt;
                            }
                            if (TABLERO[i + 1][j + 2] != 0 && TABLERO[i + 1][j + 2] == 1) {
                                nextPlay = 1;
                                fich[i + 1][j + 2].setNewImgFich(f[salt], fix[i + 1][j + 2]);
                                TABLERO[i + 1][j + 2] = salt;
                            }
                            if (TABLERO[i + 1][j - 2] != 0 && TABLERO[i + 1][j - 2] == 1) {
                                nextPlay = 1;
                                fich[i + 1][j - 2].setNewImgFich(f[salt], fix[i + 1][j - 2]);
                                TABLERO[i + 1][j - 2] = salt;
                            }
                        }
                        if (j == 1 && !valFront) {
                            valFront = true;
                            //duplicar
                            posAI = i;
                            posAJ = j;
                            if (TABLERO[i - 1][j] != 0 && TABLERO[i - 1][j] == 1) {
                                nextPlay = 1;
                                fich[i - 1][j].setNewImgFich(f[dup], fix[i - 1][j]);
                                TABLERO[i - 1][j] = dup;
                            }
                            if (TABLERO[i - 1][j + 1] != 0 && TABLERO[i - 1][j + 1] == 1) {
                                nextPlay = 1;
                                fich[i - 1][j + 1].setNewImgFich(f[dup], fix[i - 1][j + 1]);
                                TABLERO[i - 1][j + 1] = dup;
                            }
                            if (TABLERO[i][j + 1] != 0 && TABLERO[i][j + 1] == 1) {
                                nextPlay = 1;
                                fich[i][j + 1].setNewImgFich(f[dup], fix[i][j + 1]);
                                TABLERO[i][j + 1] = dup;
                            }
                            if (TABLERO[i + 1][j + 1] != 0 && TABLERO[i + 1][j + 1] == 1) {
                                nextPlay = 1;
                                fich[i + 1][j + 1].setNewImgFich(f[dup], fix[i + 1][j + 1]);
                                TABLERO[i + 1][j + 1] = dup;
                            }
                            if (TABLERO[i + 1][j] != 0 && TABLERO[i + 1][j] == 1) {
                                nextPlay = 1;
                                fich[i + 1][j].setNewImgFich(f[dup], fix[i + 1][j]);
                                TABLERO[i + 1][j] = dup;
                            }
                            if (TABLERO[i][j - 1] != 0 && TABLERO[i][j - 1] == 1) {
                                nextPlay = 1;
                                fich[i][j - 1].setNewImgFich(f[dup], fix[i][j - 1]);
                                TABLERO[i][j - 1] = dup;
                            }
                            if (TABLERO[i + 1][j - 1] != 0 && TABLERO[i + 1][j - 1] == 1) {
                                nextPlay = 1;
                                fich[i + 1][j - 1].setNewImgFich(f[dup], fix[i + 1][j - 1]);
                                TABLERO[i + 1][j - 1] = dup;
                            }
                            if (TABLERO[i - 1][j - 1] != 0 && TABLERO[i - 1][j - 1] == 1) {
                                nextPlay = 1;
                                fich[i - 1][j - 1].setNewImgFich(f[dup], fix[i - 1][j - 1]);
                                TABLERO[i - 1][j - 1] = dup;
                            }
                            //saltar
                            if (TABLERO[i - 2][j] != 0 && TABLERO[i - 2][j] == 1) {
                                nextPlay = 1;
                                fich[i - 2][j].setNewImgFich(f[salt], fix[i - 2][j]);
                                TABLERO[i - 2][j] = salt;
                            }
                            if (TABLERO[i - 2][j + 1] != 0 && TABLERO[i - 2][j + 1] == 1) {
                                nextPlay = 1;
                                fich[i - 2][j + 1].setNewImgFich(f[salt], fix[i - 2][j + 1]);
                                TABLERO[i - 2][j + 1] = salt;
                            }
                            if (TABLERO[i - 2][j + 2] != 0 && TABLERO[i - 2][j + 2] == 1) {
                                nextPlay = 1;
                                fich[i - 2][j + 2].setNewImgFich(f[salt], fix[i - 2][j + 2]);
                                TABLERO[i - 2][j + 2] = salt;
                            }
                            if (TABLERO[i - 1][j + 2] != 0 && TABLERO[i - 1][j + 2] == 1) {
                                nextPlay = 1;
                                fich[i - 1][j + 2].setNewImgFich(f[salt], fix[i - 1][j + 2]);
                                TABLERO[i - 1][j + 2] = salt;
                            }
                            if (TABLERO[i + 2][j] != 0 && TABLERO[i + 2][j] == 1) {
                                nextPlay = 1;
                                fich[i + 2][j].setNewImgFich(f[salt], fix[i + 2][j]);
                                TABLERO[i + 2][j] = salt;
                            }
                            if (TABLERO[i + 2][j + 1] != 0 && TABLERO[i + 2][j + 1] == 1) {
                                nextPlay = 1;
                                fich[i + 2][j + 1].setNewImgFich(f[salt], fix[i + 2][j + 1]);
                                TABLERO[i + 2][j + 1] = salt;
                            }
                            if (TABLERO[i + 2][j + 2] != 0 && TABLERO[i + 2][j + 2] == 1) {
                                nextPlay = 1;
                                fich[i + 2][j + 2].setNewImgFich(f[salt], fix[i + 2][j + 2]);
                                TABLERO[i + 2][j + 2] = salt;
                            }
                            if (TABLERO[i + 1][j + 2] != 0 && TABLERO[i + 1][j + 2] == 1) {
                                nextPlay = 1;
                                fich[i + 1][j + 2].setNewImgFich(f[salt], fix[i + 1][j + 2]);
                                TABLERO[i + 1][j + 2] = salt;
                            }
                            if (TABLERO[i][j + 2] != 0 && TABLERO[i][j + 2] == 1) {
                                nextPlay = 1;
                                fich[i][j + 2].setNewImgFich(f[salt], fix[i][j + 2]);
                                TABLERO[i][j + 2] = salt;
                            }
                            if (TABLERO[i - 2][j - 1] != 0 && TABLERO[i - 2][j - 1] == 1) {
                                nextPlay = 1;
                                fich[i - 2][j - 1].setNewImgFich(f[salt], fix[i - 2][j - 1]);
                                TABLERO[i - 2][j - 1] = salt;
                            }
                            if (TABLERO[i + 2][j - 1] != 0 && TABLERO[i + 2][j - 1] == 1) {
                                nextPlay = 1;
                                fich[i + 2][j - 1].setNewImgFich(f[salt], fix[i + 2][j - 1]);
                                TABLERO[i + 2][j - 1] = salt;
                            }
                        }
                        if (j == (COL - 2) && !valFront) {
                            valFront = true;
                            //duplicar
                            posAI = i;
                            posAJ = j;
                            if (TABLERO[i - 1][j - 1] != 0 && TABLERO[i - 1][j - 1] == 1) {
                                nextPlay = 1;
                                fich[i - 1][j - 1].setNewImgFich(f[dup], fix[i - 1][j - 1]);
                                TABLERO[i - 1][j - 1] = dup;
                            }
                            if (TABLERO[i - 1][j] != 0 && TABLERO[i - 1][j] == 1) {
                                nextPlay = 1;
                                fich[i - 1][j].setNewImgFich(f[dup], fix[i - 1][j]);
                                TABLERO[i - 1][j] = dup;
                            }
                            if (TABLERO[i][j - 1] != 0 && TABLERO[i][j - 1] == 1) {
                                nextPlay = 1;
                                fich[i][j - 1].setNewImgFich(f[dup], fix[i][j - 1]);
                                TABLERO[i][j - 1] = dup;
                            }
                            if (TABLERO[i + 1][j - 1] != 0 && TABLERO[i + 1][j - 1] == 1) {
                                nextPlay = 1;
                                fich[i + 1][j - 1].setNewImgFich(f[dup], fix[i + 1][j - 1]);
                                TABLERO[i + 1][j - 1] = dup;
                            }
                            if (TABLERO[i + 1][j] != 0 && TABLERO[i + 1][j] == 1) {
                                nextPlay = 1;
                                fich[i + 1][j].setNewImgFich(f[dup], fix[i + 1][j]);
                                TABLERO[i + 1][j] = dup;
                            }
                            if (TABLERO[i - 1][j + 1] != 0 && TABLERO[i - 1][j + 1] == 1) {
                                nextPlay = 1;
                                fich[i - 1][j + 1].setNewImgFich(f[dup], fix[i - 1][j + 1]);
                                TABLERO[i - 1][j + 1] = dup;
                            }
                            if (TABLERO[i + 1][j + 1] != 0 && TABLERO[i + 1][j + 1] == 1) {
                                nextPlay = 1;
                                fich[i + 1][j + 1].setNewImgFich(f[dup], fix[i + 1][j + 1]);
                                TABLERO[i + 1][j + 1] = dup;
                            }
                            if (TABLERO[i][j + 1] != 0 && TABLERO[i][j + 1] == 1) {
                                nextPlay = 1;
                                fich[i][j + 1].setNewImgFich(f[dup], fix[i][j + 1]);
                                TABLERO[i][j + 1] = dup;
                            }
                            //saltar
                            if (TABLERO[i][j - 2] != 0 && TABLERO[i][j - 2] == 1) {
                                nextPlay = 1;
                                fich[i][j - 2].setNewImgFich(f[salt], fix[i][j - 2]);
                                TABLERO[i][j - 2] = salt;
                            }
                            if (TABLERO[i + 1][j - 2] != 0 && TABLERO[i + 1][j - 2] == 1) {
                                nextPlay = 1;
                                fich[i + 1][j - 2].setNewImgFich(f[salt], fix[i + 1][j - 2]);
                                TABLERO[i + 1][j - 2] = salt;
                            }
                            if (TABLERO[i + 2][j - 2] != 0 && TABLERO[i + 2][j - 2] == 1) {
                                nextPlay = 1;
                                fich[i + 2][j - 2].setNewImgFich(f[salt], fix[i + 2][j - 2]);
                                TABLERO[i + 2][j - 2] = salt;
                            }
                            if (TABLERO[i + 2][j - 1] != 0 && TABLERO[i + 2][j - 1] == 1) {
                                nextPlay = 1;
                                fich[i + 2][j - 1].setNewImgFich(f[salt], fix[i + 2][j - 1]);
                                TABLERO[i + 2][j - 1] = salt;
                            }
                            if (TABLERO[i + 2][j] != 0 && TABLERO[i + 2][j] == 1) {
                                nextPlay = 1;
                                fich[i + 2][j].setNewImgFich(f[salt], fix[i + 2][j]);
                                TABLERO[i + 2][j] = salt;
                            }
                            if (TABLERO[i - 1][j - 2] != 0 && TABLERO[i - 1][j - 2] == 1) {
                                nextPlay = 1;
                                fich[i - 1][j - 2].setNewImgFich(f[salt], fix[i - 1][j - 2]);
                                TABLERO[i - 1][j - 2] = salt;
                            }
                            if (TABLERO[i - 2][j - 2] != 0 && TABLERO[i - 2][j - 2] == 1) {
                                nextPlay = 1;
                                fich[i - 2][j - 2].setNewImgFich(f[salt], fix[i - 2][j - 2]);
                                TABLERO[i - 2][j - 2] = salt;
                            }
                            if (TABLERO[i - 2][j - 1] != 0 && TABLERO[i - 2][j - 1] == 1) {
                                nextPlay = 1;
                                fich[i - 2][j - 1].setNewImgFich(f[salt], fix[i - 2][j - 1]);
                                TABLERO[i - 2][j - 1] = salt;
                            }
                            if (TABLERO[i - 2][j] != 0 && TABLERO[i - 2][j] == 1) {
                                nextPlay = 1;
                                fich[i - 2][j].setNewImgFich(f[salt], fix[i - 2][j]);
                                TABLERO[i - 2][j] = salt;
                            }
                            if (TABLERO[i - 2][j + 1] != 0 && TABLERO[i - 2][j + 1] == 1) {
                                nextPlay = 1;
                                fich[i - 2][j + 1].setNewImgFich(f[salt], fix[i - 2][j + 1]);
                                TABLERO[i - 2][j + 1] = salt;
                            }
                            if (TABLERO[i + 2][j + 1] != 0 && TABLERO[i + 2][j + 1] == 1) {
                                nextPlay = 1;
                                fich[i + 2][j + 1].setNewImgFich(f[salt], fix[i + 2][j + 1]);
                                TABLERO[i + 2][j + 1] = salt;
                            }
                        }
                        //···························································
                        if (!valFront) {
                            //duplicar
                            posAI = i;
                            posAJ = j;
                            nextPlay = 1;
                            if (TABLERO[i][j - 1] != 0 && TABLERO[i][j - 1] == 1) {
                                nextPlay = 1;
                                fich[i][j - 1].setNewImgFich(f[dup], fix[i][j - 1]);
                                TABLERO[i][j - 1] = dup;
                            }
                            if (TABLERO[i][j + 1] != 0 && TABLERO[i][j + 1] == 1) {
                                nextPlay = 1;
                                fich[i][j + 1].setNewImgFich(f[dup], fix[i][j + 1]);
                                TABLERO[i][j + 1] = dup;
                            }
                            if (TABLERO[i + 1][j] != 0 && TABLERO[i + 1][j] == 1) {
                                nextPlay = 1;
                                fich[i + 1][j].setNewImgFich(f[dup], fix[i + 1][j]);
                                TABLERO[i + 1][j] = dup;
                            }
                            if (TABLERO[i + 1][j - 1] != 0 && TABLERO[i + 1][j - 1] == 1) {
                                nextPlay = 1;
                                fich[i + 1][j - 1].setNewImgFich(f[dup], fix[i + 1][j - 1]);
                                TABLERO[i + 1][j - 1] = dup;
                            }
                            if (TABLERO[i + 1][j + 1] != 0 && TABLERO[i + 1][j + 1] == 1) {
                                nextPlay = 1;
                                fich[i + 1][j + 1].setNewImgFich(f[dup], fix[i + 1][j + 1]);
                                TABLERO[i + 1][j + 1] = dup;
                            }
                            /**
                             *
                             */
                            if (TABLERO[i - 1][j - 1] != 0 && TABLERO[i - 1][j - 1] == 1) {
                                nextPlay = 1;
                                fich[i - 1][j - 1].setNewImgFich(f[dup], fix[i - 1][j - 1]);
                                TABLERO[i - 1][j - 1] = dup;
                            }
                            if (TABLERO[i - 1][j] != 0 && TABLERO[i - 1][j] == 1) {
                                nextPlay = 1;
                                fich[i - 1][j].setNewImgFich(f[dup], fix[i - 1][j]);
                                TABLERO[i - 1][j] = dup;
                            }
                            if (TABLERO[i - 1][j + 1] != 0 && TABLERO[i - 1][j + 1] == 1) {
                                nextPlay = 1;
                                fich[i - 1][j + 1].setNewImgFich(f[dup], fix[i - 1][j + 1]);
                                TABLERO[i - 1][j + 1] = dup;
                            }
                            //saltar
                            if (TABLERO[i][j - 2] != 0 && TABLERO[i][j - 2] == 1) {
                                nextPlay = 1;
                                fich[i][j - 2].setNewImgFich(f[salt], fix[i][j - 2]);
                                TABLERO[i][j - 2] = salt;
                            }
                            if (TABLERO[i + 1][j - 2] != 0 && TABLERO[i + 1][j - 2] == 1) {
                                nextPlay = 1;
                                fich[i + 1][j - 2].setNewImgFich(f[salt], fix[i + 1][j - 2]);
                                TABLERO[i + 1][j - 2] = salt;
                            }
                            if (TABLERO[i + 2][j - 2] != 0 && TABLERO[i + 2][j - 2] == 1) {
                                nextPlay = 1;
                                fich[i + 2][j - 2].setNewImgFich(f[salt], fix[i + 2][j - 2]);
                                TABLERO[i + 2][j - 2] = salt;
                            }
                            if (TABLERO[i + 2][j - 1] != 0 && TABLERO[i + 2][j - 1] == 1) {
                                nextPlay = 1;
                                fich[i + 2][j - 1].setNewImgFich(f[salt], fix[i + 2][j - 1]);
                                TABLERO[i + 2][j - 1] = salt;
                            }
                            if (TABLERO[i + 2][j] != 0 && TABLERO[i + 2][j] == 1) {
                                nextPlay = 1;
                                fich[i + 2][j].setNewImgFich(f[salt], fix[i + 2][j]);
                                TABLERO[i + 2][j] = salt;
                            }
                            if (TABLERO[i + 2][j + 1] != 0 && TABLERO[i + 2][j + 1] == 1) {
                                nextPlay = 1;
                                fich[i + 2][j + 1].setNewImgFich(f[salt], fix[i + 2][j + 1]);
                                TABLERO[i + 2][j + 1] = salt;
                            }
                            if (TABLERO[i + 2][j + 2] != 0 && TABLERO[i + 2][j + 2] == 1) {
                                nextPlay = 1;
                                fich[i + 2][j + 2].setNewImgFich(f[salt], fix[i + 2][j + 2]);
                                TABLERO[i + 2][j + 2] = salt;
                            }
                            if (TABLERO[i + 1][j + 2] != 0 && TABLERO[i + 1][j + 2] == 1) {
                                nextPlay = 1;
                                fich[i + 1][j + 2].setNewImgFich(f[salt], fix[i + 1][j + 2]);
                                TABLERO[i + 1][j + 2] = salt;
                            }
                            if (TABLERO[i][j + 2] != 0 && TABLERO[i][j + 2] == 1) {
                                nextPlay = 1;
                                fich[i][j + 2].setNewImgFich(f[salt], fix[i][j + 2]);
                                TABLERO[i][j + 2] = salt;
                            }
                            /**
                             *
                             */
                            if (TABLERO[i - 1][j - 2] != 0 && TABLERO[i - 1][j - 2] == 1) {
                                nextPlay = 1;
                                fich[i - 1][j - 2].setNewImgFich(f[salt], fix[i - 1][j - 2]);
                                TABLERO[i - 1][j - 2] = salt;
                            }
                            if (TABLERO[i - 2][j - 2] != 0 && TABLERO[i - 2][j - 2] == 1) {
                                nextPlay = 1;
                                fich[i - 2][j - 2].setNewImgFich(f[salt], fix[i - 2][j - 2]);
                                TABLERO[i - 2][j - 2] = salt;
                            }
                            if (TABLERO[i - 2][j - 1] != 0 && TABLERO[i - 2][j - 1] == 1) {
                                nextPlay = 1;
                                fich[i - 2][j - 1].setNewImgFich(f[salt], fix[i - 2][j - 1]);
                                TABLERO[i - 2][j - 1] = salt;
                            }
                            if (TABLERO[i - 2][j] != 0 && TABLERO[i - 2][j] == 1) {
                                nextPlay = 1;
                                fich[i - 2][j].setNewImgFich(f[salt], fix[i - 2][j]);
                                TABLERO[i - 2][j] = salt;
                            }
                            if (TABLERO[i - 2][j + 1] != 0 && TABLERO[i - 2][j + 1] == 1) {
                                nextPlay = 1;
                                fich[i - 2][j + 1].setNewImgFich(f[salt], fix[i - 2][j + 1]);
                                TABLERO[i - 2][j + 1] = salt;
                            }
                            if (TABLERO[i - 2][j + 2] != 0 && TABLERO[i - 2][j + 2] == 1) {
                                nextPlay = 1;
                                fich[i - 2][j + 2].setNewImgFich(f[salt], fix[i - 2][j + 2]);
                                TABLERO[i - 2][j + 2] = salt;
                            }
                            if (TABLERO[i - 1][j + 2] != 0 && TABLERO[i - 1][j + 2] == 1) {
                                nextPlay = 1;
                                fich[i - 1][j + 2].setNewImgFich(f[salt], fix[i - 1][j + 2]);
                                TABLERO[i - 1][j + 2] = salt;
                            }
                        }
                    }
                    if (e.getSource() == fix[i][j] && TABLERO[i][j] == dup && nextPlay == 1) {
                        nextPlay = 0;
                        fich[i][j].setNewImgFich(f[turno], fix[i][j]);
                        TABLERO[i][j] = turno;
                        CambioFicha(i, j);
                        recontruirTablero();
                        int aux = turno;
                        turno = rival;
                        rival = aux;
                    }
                    if (e.getSource() == fix[i][j] && TABLERO[i][j] == salt && nextPlay == 1) {
                        nextPlay = 0;
                        fich[i][j].setNewImgFich(f[turno], fix[i][j]);
                        TABLERO[i][j] = turno;
                        fich[posAI][posAJ].setNewImgFich(f[vacio], fix[posAI][posAJ]);
                        TABLERO[posAI][posAJ] = vacio;
                        CambioFicha(i, j);
                        recontruirTablero();
                        int aux = turno;
                        turno = rival;
                        rival = aux;
                    }
                }
            }
            if(!verificarPerdio()){
                JOptionPane.showMessageDialog(null, "¡GAME OVER!");
                System.exit(0);
            }
        }
    }
    
    public Boolean verificarPerdio(){
        for (int i = 0; i < FIL; i++) {
            for (int j = 0; j < COL; j++) {
                if (TABLERO[i][j] == turno) {
                    return true;
                }
            }
        }
        return false;
    }
    
    public void recontruirTablero() {
        for (int i = 0; i < FIL; i++) {
            for (int j = 0; j < COL; j++) {
                if (TABLERO[i][j] == salt || TABLERO[i][j] == dup) {
                    TABLERO[i][j] = vacio;
                    fich[i][j].setNewImgFich(f[vacio], fix[i][j]);
                }
            }
        }
    }

    public void CambioFicha(int i, int j) {

        Boolean valFront = false;
        if (i == 0 && !valFront) {
            valFront = true;
            if (TABLERO[i][j - 1] == rival) {
                fich[i][j - 1].setNewImgFich(f[turno], fix[i][j - 1]);
                TABLERO[i][j - 1] = turno;
            }
            if (TABLERO[i][j + 1] == rival) {
                fich[i][j + 1].setNewImgFich(f[turno], fix[i][j + 1]);
                TABLERO[i][j + 1] = turno;
            }
            if (TABLERO[i + 1][j] == rival) {
                fich[i + 1][j].setNewImgFich(f[turno], fix[i + 1][j]);
                TABLERO[i + 1][j] = turno;
            }
            if (TABLERO[i + 1][j - 1] == rival) {
                fich[i + 1][j - 1].setNewImgFich(f[turno], fix[i + 1][j - 1]);
                TABLERO[i + 1][j - 1] = turno;
            }
            if (TABLERO[i + 1][j + 1] == rival) {
                fich[i + 1][j + 1].setNewImgFich(f[turno], fix[i + 1][j + 1]);
                TABLERO[i + 1][j + 1] = turno;
            }
        }
        if (i == (FIL - 1) && !valFront) {
            valFront = true;
            if (TABLERO[i][j - 1] == rival) {
                fich[i][j - 1].setNewImgFich(f[turno], fix[i][j - 1]);
                TABLERO[i][j - 1] = turno;
            }
            if (TABLERO[i][j + 1] == rival) {
                fich[i][j + 1].setNewImgFich(f[turno], fix[i][j + 1]);
                TABLERO[i][j + 1] = turno;
            }
            if (TABLERO[i - 1][j - 1] == rival) {
                fich[i - 1][j - 1].setNewImgFich(f[turno], fix[i - 1][j - 1]);
                TABLERO[i - 1][j - 1] = turno;
            }
            if (TABLERO[i - 1][j] == rival) {
                fich[i - 1][j].setNewImgFich(f[turno], fix[i - 1][j]);
                TABLERO[i - 1][j] = turno;
            }
            if (TABLERO[i - 1][j + 1] == rival) {
                fich[i - 1][j + 1].setNewImgFich(f[turno], fix[i - 1][j + 1]);
                TABLERO[i - 1][j + 1] = turno;
            }
        }
        if (j == 0 && !valFront) {
            valFront = true;
            if (TABLERO[i - 1][j] == rival) {
                fich[i - 1][j].setNewImgFich(f[turno], fix[i - 1][j]);
                TABLERO[i - 1][j] = turno;
            }
            if (TABLERO[i - 1][j + 1] == rival) {
                fich[i - 1][j + 1].setNewImgFich(f[turno], fix[i - 1][j + 1]);
                TABLERO[i - 1][j + 1] = turno;
            }
            if (TABLERO[i][j + 1] == rival) {
                fich[i][j + 1].setNewImgFich(f[turno], fix[i][j + 1]);
                TABLERO[i][j + 1] = turno;
            }
            if (TABLERO[i + 1][j + 1] == rival) {
                fich[i + 1][j + 1].setNewImgFich(f[turno], fix[i + 1][j + 1]);
                TABLERO[i + 1][j + 1] = turno;
            }
            if (TABLERO[i + 1][j] == rival) {
                fich[i + 1][j].setNewImgFich(f[turno], fix[i + 1][j]);
                TABLERO[i + 1][j] = turno;
            }
        }
        if (j == (COL - 1) && !valFront) {
            valFront = true;
            if (TABLERO[i - 1][j - 1] == rival) {
                fich[i - 1][j - 1].setNewImgFich(f[turno], fix[i - 1][j - 1]);
                TABLERO[i - 1][j - 1] = turno;
            }
            if (TABLERO[i - 1][j] == rival) {
                fich[i - 1][j].setNewImgFich(f[turno], fix[i - 1][j]);
                TABLERO[i - 1][j] = turno;
            }
            if (TABLERO[i][j - 1] == rival) {
                fich[i][j - 1].setNewImgFich(f[turno], fix[i][j - 1]);
                TABLERO[i][j - 1] = turno;
            }
            if (TABLERO[i + 1][j - 1] == rival) {
                fich[i + 1][j - 1].setNewImgFich(f[turno], fix[i + 1][j - 1]);
                TABLERO[i + 1][j - 1] = turno;
            }
            if (TABLERO[i + 1][j] == rival) {
                fich[i + 1][j].setNewImgFich(f[turno], fix[i + 1][j]);
                TABLERO[i + 1][j] = turno;
            }
        }
        //···························································
        if (i == 1 && !valFront) {
            valFront = true;
            if (TABLERO[i][j - 1] == rival) {
                fich[i][j - 1].setNewImgFich(f[turno], fix[i][j - 1]);
                TABLERO[i][j - 1] = turno;
            }
            if (TABLERO[i][j + 1] == rival) {
                fich[i][j + 1].setNewImgFich(f[turno], fix[i][j + 1]);
                TABLERO[i][j + 1] = turno;
            }
            if (TABLERO[i + 1][j] == rival) {
                fich[i + 1][j].setNewImgFich(f[turno], fix[i + 1][j]);
                TABLERO[i + 1][j] = turno;
            }
            if (TABLERO[i + 1][j - 1] == rival) {
                fich[i + 1][j - 1].setNewImgFich(f[turno], fix[i + 1][j - 1]);
                TABLERO[i + 1][j - 1] = turno;
            }
            if (TABLERO[i + 1][j + 1] == rival) {
                fich[i + 1][j + 1].setNewImgFich(f[turno], fix[i + 1][j + 1]);
                TABLERO[i + 1][j + 1] = turno;
            }
            if (TABLERO[i - 1][j - 1] == rival) {
                fich[i - 1][j - 1].setNewImgFich(f[turno], fix[i - 1][j - 1]);
                TABLERO[i - 1][j - 1] = turno;
            }
            if (TABLERO[i - 1][j] == rival) {
                fich[i - 1][j].setNewImgFich(f[turno], fix[i - 1][j]);
                TABLERO[i - 1][j] = turno;
            }
            if (TABLERO[i - 1][j + 1] == rival) {
                fich[i - 1][j + 1].setNewImgFich(f[turno], fix[i - 1][j + 1]);
                TABLERO[i - 1][j + 1] = turno;
            }
        }
        if (i == (FIL - 2) && !valFront) {
            valFront = true;
            if (TABLERO[i][j - 1] == rival) {
                fich[i][j - 1].setNewImgFich(f[turno], fix[i][j - 1]);
                TABLERO[i][j - 1] = turno;
            }
            if (TABLERO[i][j + 1] == rival) {
                fich[i][j + 1].setNewImgFich(f[turno], fix[i][j + 1]);
                TABLERO[i][j + 1] = turno;
            }
            if (TABLERO[i - 1][j - 1] == rival) {
                fich[i - 1][j - 1].setNewImgFich(f[turno], fix[i - 1][j - 1]);
                TABLERO[i - 1][j - 1] = turno;
            }
            if (TABLERO[i - 1][j] == rival) {
                fich[i - 1][j].setNewImgFich(f[turno], fix[i - 1][j]);
                TABLERO[i - 1][j] = turno;
            }
            if (TABLERO[i - 1][j + 1] == rival) {
                fich[i - 1][j + 1].setNewImgFich(f[turno], fix[i - 1][j + 1]);
                TABLERO[i - 1][j + 1] = turno;
            }
            if (TABLERO[i + 1][j - 1] == rival) {
                fich[i + 1][j - 1].setNewImgFich(f[turno], fix[i + 1][j - 1]);
                TABLERO[i + 1][j - 1] = turno;
            }
            if (TABLERO[i + 1][j] == rival) {
                fich[i + 1][j].setNewImgFich(f[turno], fix[i + 1][j]);
                TABLERO[i + 1][j] = turno;
            }
            if (TABLERO[i + 1][j + 1] == rival) {
                fich[i + 1][j + 1].setNewImgFich(f[turno], fix[i + 1][j + 1]);
                TABLERO[i + 1][j + 1] = turno;
            }
        }
        if (j == 1 && !valFront) {
            valFront = true;
            if (TABLERO[i - 1][j] == rival) {
                fich[i - 1][j].setNewImgFich(f[turno], fix[i - 1][j]);
                TABLERO[i - 1][j] = turno;
            }
            if (TABLERO[i - 1][j + 1] == rival) {
                fich[i - 1][j + 1].setNewImgFich(f[turno], fix[i - 1][j + 1]);
                TABLERO[i - 1][j + 1] = turno;
            }
            if (TABLERO[i][j + 1] == rival) {
                fich[i][j + 1].setNewImgFich(f[turno], fix[i][j + 1]);
                TABLERO[i][j + 1] = turno;
            }
            if (TABLERO[i + 1][j + 1] == rival) {
                fich[i + 1][j + 1].setNewImgFich(f[turno], fix[i + 1][j + 1]);
                TABLERO[i + 1][j + 1] = turno;
            }
            if (TABLERO[i + 1][j] == rival) {
                fich[i + 1][j].setNewImgFich(f[turno], fix[i + 1][j]);
                TABLERO[i + 1][j] = turno;
            }
            if (TABLERO[i][j - 1] == rival) {
                fich[i][j - 1].setNewImgFich(f[turno], fix[i][j - 1]);
                TABLERO[i][j - 1] = turno;
            }
            if (TABLERO[i + 1][j - 1] == rival) {
                fich[i + 1][j - 1].setNewImgFich(f[turno], fix[i + 1][j - 1]);
                TABLERO[i + 1][j - 1] = turno;
            }
            if (TABLERO[i - 1][j - 1] == rival) {
                fich[i - 1][j - 1].setNewImgFich(f[turno], fix[i - 1][j - 1]);
                TABLERO[i - 1][j - 1] = turno;
            }
        }
        if (j == (COL - 2) && !valFront) {
            valFront = true;
            if (TABLERO[i - 1][j - 1] == rival) {
                fich[i - 1][j - 1].setNewImgFich(f[turno], fix[i - 1][j - 1]);
                TABLERO[i - 1][j - 1] = turno;
            }
            if (TABLERO[i - 1][j] == rival) {
                fich[i - 1][j].setNewImgFich(f[turno], fix[i - 1][j]);
                TABLERO[i - 1][j] = turno;
            }
            if (TABLERO[i][j - 1] == rival) {
                fich[i][j - 1].setNewImgFich(f[turno], fix[i][j - 1]);
                TABLERO[i][j - 1] = turno;
            }
            if (TABLERO[i + 1][j - 1] == rival) {
                fich[i + 1][j - 1].setNewImgFich(f[turno], fix[i + 1][j - 1]);
                TABLERO[i + 1][j - 1] = turno;
            }
            if (TABLERO[i + 1][j] == rival) {
                fich[i + 1][j].setNewImgFich(f[turno], fix[i + 1][j]);
                TABLERO[i + 1][j] = turno;
            }
            if (TABLERO[i - 1][j + 1] == rival) {
                fich[i - 1][j + 1].setNewImgFich(f[turno], fix[i - 1][j + 1]);
                TABLERO[i - 1][j + 1] = turno;
            }
            if (TABLERO[i + 1][j + 1] == rival) {
                fich[i + 1][j + 1].setNewImgFich(f[turno], fix[i + 1][j + 1]);
                TABLERO[i + 1][j + 1] = turno;
            }
            if (TABLERO[i][j + 1] == rival) {
                fich[i][j + 1].setNewImgFich(f[turno], fix[i][j + 1]);
                TABLERO[i][j + 1] = turno;
            }
        }
        //···························································
        if (!valFront) {
            if (TABLERO[i][j - 1] == rival) {
                fich[i][j - 1].setNewImgFich(f[turno], fix[i][j - 1]);
                TABLERO[i][j - 1] = turno;
            }
            if (TABLERO[i][j + 1] == rival) {
                fich[i][j + 1].setNewImgFich(f[turno], fix[i][j + 1]);
                TABLERO[i][j + 1] = turno;
            }
            if (TABLERO[i + 1][j] == rival) {
                fich[i + 1][j].setNewImgFich(f[turno], fix[i + 1][j]);
                TABLERO[i + 1][j] = turno;
            }
            if (TABLERO[i + 1][j - 1] == rival) {
                fich[i + 1][j - 1].setNewImgFich(f[turno], fix[i + 1][j - 1]);
                TABLERO[i + 1][j - 1] = turno;
            }
            if (TABLERO[i + 1][j + 1] == rival) {
                fich[i + 1][j + 1].setNewImgFich(f[turno], fix[i + 1][j + 1]);
                TABLERO[i + 1][j + 1] = turno;
            }
            if (TABLERO[i - 1][j - 1] == rival) {
                fich[i - 1][j - 1].setNewImgFich(f[turno], fix[i - 1][j - 1]);
                TABLERO[i - 1][j - 1] = turno;
            }
            if (TABLERO[i - 1][j] == rival) {
                fich[i - 1][j].setNewImgFich(f[turno], fix[i - 1][j]);
                TABLERO[i - 1][j] = turno;
            }
            if (TABLERO[i - 1][j + 1] == rival) {
                fich[i - 1][j + 1].setNewImgFich(f[turno], fix[i - 1][j + 1]);
                TABLERO[i - 1][j + 1] = turno;
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}
