/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package octo;

import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author anyel
 */
public class Ficha {
    public static URL FICHA;
    public int TAM_H = 80;
    public int TAM_V = 50;
    int pos_fV;
    int pos_fH;
    String dirFicha = "";
    ImageIcon imgFich;
    public Ficha ()
		{			
		
		}

    public JLabel setFicha(int pos_fH, int pos_fV, String dirFicha, JLabel containerFicha, JLabel fix){
        
            this.pos_fV = pos_fV;			
            this.pos_fH = pos_fH;
            this.dirFicha = dirFicha;
            String dirURL = "imagenesJuego/"+dirFicha;
            FICHA = this.getClass().getResource(dirURL);           
            imgFich = new ImageIcon(FICHA);
            fix = new JLabel(imgFich);   
            fix.setBounds(pos_fH, pos_fV,TAM_H,TAM_V);
            containerFicha.add(fix);
            return fix;
    }
    public void setNewImgFich(String dirFicha, JLabel fix){
            
            String dirURL = "imagenesJuego/"+dirFicha;
            FICHA = this.getClass().getResource(dirURL);           
            imgFich = new ImageIcon(FICHA);
            fix.setIcon(imgFich);          

    }
}
