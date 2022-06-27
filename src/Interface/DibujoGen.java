/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Interface;

import static com.sun.java.accessibility.util.AWTEventMonitor.addWindowListener;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Locale;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 *
 * @author davpa
 */
public class DibujoGen extends JPanel{
    GUIpry gui;
    BufferedImage img;//aqui se almacena todo lo que se hace con g2d
    Graphics2D g2d;
    JFrame frame;
    
    public DibujoGen(GUIpry gui){
        super();
        this.gui = gui;
        init();
    }
    
    public BufferedImage getImage(){
        return img;
    }
    public void init(){
        setBackground(Color.WHITE);
        
        img = new BufferedImage(400, 200, BufferedImage.TYPE_INT_BGR);
        g2d = img.createGraphics();//instancia de imagenes
        g2d.setBackground(Color.WHITE);
        g2d.clearRect(0, 0, 400, 200);
        g2d.setColor(Color.BLACK);
        
        //ggd.drawLine(100, 5, 5, 200);
        //ggd.fillRect(50, 50, 100, 100);
        g2d.setStroke(new BasicStroke(2));//lineas algo mas gruesas
        
        //para usar el raton
        
        MouseAdapter mauseHandler;
        mauseHandler = new MouseAdapter() {
            
            
            private Point curPoint = new Point();
            @Override
            public void mousePressed(MouseEvent e) {
                //iguala la posicion inicial
                curPoint.setLocation(e.getPoint());//get adquiere la posicion del raton y lo inicializa en el point
            }

            @Override
            public void mouseDragged(MouseEvent e) {//cuando  movemos el raton y segimos presionando el raton
                g2d.drawLine(curPoint.x, curPoint.y, e.getX(), e.getY());//acualiza en donde se encuntra el raton
                curPoint.setLocation(e.getPoint());//iguala la posicion final
                repaint();//requerimos el metodo paintComponer
                gui.setImage(img);
                //ImageIO.write(img, "jpg", new File("foto1.jpg"));
            }
            
            
            
        };
        addMouseListener(mauseHandler);//invoca al metodo mousePress
        addMouseMotionListener(mauseHandler);//invoca a mouseDragged
        frame = new JFrame("DibujoGen");
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);//se dibuje el color de fondo
        g.drawImage(img, 0, 0, null);
    }
    public void iniciar(GUIpry gui){
        
        SwingUtilities.invokeLater(() -> {
            //JFrame frame = new JFrame("DibujoGen");
            frame.setMinimumSize(new Dimension(400, 200));
            frame.setResizable(false);
            //frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.addWindowListener(new WindowAdapter() {
                public void windowClosing(WindowEvent e){
                    //JOptionPane.showMessageDialog(frame, "HEY", "HEY", HEIGHT);
                    gui.cargarPanel();
                }
            });
            frame.setContentPane(new DibujoGen(gui));
            frame.setLocationRelativeTo(null);//centrar la ventana
            frame.setVisible(true);//mostrar la interface            
        });
    }
    /*
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
    }*/
}
