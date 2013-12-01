/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebas;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Fran
 */
public class Pruebas extends Thread{
    
    JFrame pantalla = new JFrame();
    JPanel liquido = new JPanel(){
        
        ImageIcon agua = new ImageIcon("img/agua.jpg");
        
        @Override
        public void paint(Graphics g){
            g.drawImage(agua.getImage(), 0, 0, this);
                
                setOpaque(false);
                super.paint(g);
        }
    };
    JLabel cubicaje = new JLabel("HOLA");
    int liq_x = 40;
    int liq_y;
    int liquid_width = 298;
    int liquid_height = 0;
    
    public void init(){
        pantalla.setPreferredSize(new Dimension(400,600));
        Container cont = pantalla.getContentPane();
        cont.setLayout(null);
        
        JPanel panelFondo = new JPanel(){
            
            ImageIcon tanque = new ImageIcon("img/tanque.jpg");
            
            @Override
            public void paint(Graphics g){
                g.drawImage(tanque.getImage(), 25, 25, this);
                
                setOpaque(false);
                super.paint(g);
            }
        };
        panelFondo.setBounds(0, 0, pantalla.getPreferredSize().width, pantalla.getPreferredSize().height);
        
        liquido.setBackground(Color.YELLOW);
        liq_y = pantalla.getPreferredSize().height - 65;
        liquido.setBounds(liq_x, liq_y, liquid_width, liquid_height);
        
        cubicaje.setText(Integer.toString(liq_y));
        cubicaje.setBounds(pantalla.getPreferredSize().width / 2, liq_y + 10, 100, 20);
        
        
        cont.add(cubicaje);
        cont.add(liquido);
        cont.add(panelFondo);
        
               
        pantalla.pack();
        pantalla.setVisible(true);
        pantalla.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    @Override
    public void run(){
        while (liquid_height < 464){
            try {
                Thread.sleep(10);
                liq_y--;
                liquid_height++;
                System.out.println(liq_y + " - " + liquid_height);
                liquido.setBounds(liq_x, liq_y, liquid_width, liquid_height);
                cubicaje.setText(Integer.toString(liq_y));
                cubicaje.setBounds(pantalla.getPreferredSize().width / 2, liq_y + 10, 100, 20);
//                cubicaje.validate();
//                liquido.repaint();
//                pantalla.repaint();
            } catch (InterruptedException ex) {
                Logger.getLogger(Pruebas.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Pruebas p = new Pruebas();
        p.init();
        p.start();
    }
}
