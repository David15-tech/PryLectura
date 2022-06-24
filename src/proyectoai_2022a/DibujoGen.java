/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectoai_2022a;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.Locale;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 *
 * @author davpa
 */
public class DibujoGen extends JPanel{
    
    BufferedImage img;//aqui se almacena todo lo que se hace con g2d
    Graphics2D g2d;
    
    public DibujoGen(){
        super();
        init();
    }
    
    public void init(){
        setBackground(Color.BLACK);
        
        img = new BufferedImage(640, 480, BufferedImage.TYPE_INT_RGB);
        g2d = img.createGraphics();//instancia de imagenes
        g2d.setColor(Color.GREEN);
        g2d.setStroke(new BasicStroke(2));//lineas algo mas gruesas
        
        //para usar el raton
        
        MouseAdapter mauseHandler = new MouseAdapter() {
            
            
            private Point curPoint = new Point();
            @Override
            public void mousePressed(MouseEvent e) {
                //iguala la posicion inicial
                curPoint.setLocation(e.getPoint());//get adquiere la posicion del raton y lo inicializa en el point
            }

            @Override
            public void mouseDragged(MouseEvent e) {//cuando  movemos el raton y segimos presionando el raton
                g2d.drawLine(curPoint.x, curPoint.y, e.getX(), e.getY());//actializa en donde se encuntra el raton
                curPoint.setLocation(e.getPoint());//iguala la posicion final
                repaint();//requerimos el metodo paintComponer
            }
            
            
            
        };
        addMouseListener(mauseHandler);//invoca al metodo mousePress
        addMouseMotionListener(mauseHandler);//invoca a mouseDragged
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);//se dibuje el color de fondo
        g.drawImage(img, 0, 0, null);
    }
    
    
    
    public static void main(String[] args){
        

        
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("DibujoGen");
            frame.setMinimumSize(new Dimension(640, 480));
            frame.setResizable(false);
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setContentPane(new DibujoGen());
            frame.setLocationRelativeTo(null);//centrar la ventana
            frame.setVisible(true);//mostrar la interface
        });
    }
}
