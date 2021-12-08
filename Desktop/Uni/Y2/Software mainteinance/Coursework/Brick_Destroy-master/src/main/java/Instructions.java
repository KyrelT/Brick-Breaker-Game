package main.java;

import org.w3c.dom.css.Rect;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import static com.sun.java.swing.plaf.motif.MotifBorders.FrameBorder.BORDER_SIZE;

public class Instructions extends JComponent implements KeyListener, MouseListener, MouseMotionListener {

    private static final String INSTRUCTIONS = "Instructions";
    private static final String START = "Start";
    private static final Color MENU_COLOR = new Color(0,0,0); // word color

    private boolean startClicked;
    private boolean showInstructions;


    private static final Color BG_COLOR = new Color(10, 200, 200);
    private static final Color TEXT_COLOR = new Color(100, 52, 116);

    private static final int DEF_WIDTH = 600;
    private static final int DEF_HEIGHT = 450;
    private final GameFrame owner;

    private Rectangle InstructionFace;
    private Rectangle startButton;

    private Timer Timer;

    private Font TitleFont;
    private Font startFont;
    private Font AFont;
    private Font DFont;
    private Font SpaceFont;
    private Font TryFont;

    public int xcoor=150;

    private Wall wall;
    private Rectangle player;

    private int strLen;

    public Instructions(GameFrame owner){

//        this.setFocusable(true);
//        this.requestFocusInWindow();
//
//        this.addMouseListener(this);
//        this.addMouseMotionListener(this);

        this.owner = owner;

        wall = new Wall(new Rectangle(0,0,DEF_WIDTH,DEF_HEIGHT),30,3,6/2,new Point(300,430));
        this.initialize();
        Timer = new Timer(10,e-> {
            // for every 10 secs, it will check for any updates in the game
            // for example if you change the value 10 into 1000 the updates on the game will be very slow
            if(player!=null) {
                player.setLocation(xcoor, 250);
            }

            repaint();

        });
        Timer.start();


        this.setPreferredSize(new Dimension(DEF_WIDTH,DEF_HEIGHT)); // WINDOW SIZE


        TitleFont = new Font("Noto Mono",Font.BOLD,35);
        AFont = new Font("Noto Mono",Font.BOLD,30);
        DFont = new Font("Noto Mono",Font.BOLD,30);
        SpaceFont = new Font("Noto Mono",Font.BOLD,30);
        startFont = new Font("Monospaced",Font.BOLD,40);
        TryFont = new Font("Monnospaced",Font.BOLD,25);

    }

    private void drawPlayer(Graphics2D g2d){
        Rectangle player = makeRectangle(xcoor,400,150,25);
        this.player = player;
        g2d.setColor(new Color(0,0,0));
        g2d.fill(player);
    }
    private Rectangle makeRectangle(int x,int y,int width,int height){
        Point p = new Point((int)(x),y);
        return  new Rectangle(p,new Dimension(width,height));
    }

    public void paint(Graphics g){
        drawInstructions((Graphics2D)g);
    }


    public void drawInstructions(Graphics2D g2d){
        drawContainer(g2d);
        drawText(g2d);
        drawPlayer(g2d);
        drawButton(g2d);
    }


    private void drawContainer(Graphics2D g2d){
        g2d.setColor(BG_COLOR); // background color
        Rectangle InstructionFace = new Rectangle(new Point(0, 0), new Dimension(DEF_WIDTH,DEF_HEIGHT));
        g2d.fill(InstructionFace);
    }

    private void drawText(Graphics2D g2d){

        g2d.setColor(TEXT_COLOR);

        FontRenderContext frc = g2d.getFontRenderContext();

        Rectangle2D TitleRect = TitleFont.getStringBounds(INSTRUCTIONS,frc);
        Rectangle2D ARect = AFont.getStringBounds("A To Move Left",frc);
        Rectangle2D DRect = DFont.getStringBounds("D To Move Right",frc);
        Rectangle2D SpaceRect = SpaceFont.getStringBounds("Spacebar To Pause the Game",frc);



        int sX = 200;
        int sY = 60;

        g2d.setFont(TitleFont);
        g2d.drawString(INSTRUCTIONS,sX,sY);

        sX = 80;
        sY = 130;

        g2d.setFont(AFont);
        g2d.drawString("A To Move Left",sX,sY);

        sY *= 1.5;

        g2d.setFont(DFont);
        g2d.drawString("D To Move Right",sX,sY);

        sY *= 1.4;

        g2d.setFont(SpaceFont);
        g2d.drawString("Spacebar To Pause the Game",sX,sY);

        g2d.setColor(new Color(0,0,0));

        sY *= 1.3;

        g2d.setFont(TryFont);
        g2d.drawString("Try it!",200,sY);

    }

    private void drawButton(Graphics2D g2d){
        Font tmpFont = g2d.getFont();
        Color tmpColor = g2d.getColor();

        g2d.setFont(startFont);
        g2d.setColor(MENU_COLOR);

        int x = 450;
        int y = 30;

        if(strLen == 0){
            FontRenderContext frc = g2d.getFontRenderContext();
            strLen = startFont.getStringBounds(START,frc).getBounds().width;
        }

        if(startButton == null){
            FontRenderContext frc = g2d.getFontRenderContext();
            startButton = startFont.getStringBounds(START,frc).getBounds();
            startButton.setLocation(x,y-startButton.height);
        }

        g2d.drawString(START,x,y);

        g2d.setFont(tmpFont);
        g2d.setColor(tmpColor);

    }


    private void initialize(){
        this.setPreferredSize(new Dimension(DEF_WIDTH,DEF_HEIGHT));
        this.setFocusable(true);
        this.requestFocusInWindow();
        this.addKeyListener(this);
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
    }



    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        switch(keyEvent.getKeyCode()){
            case KeyEvent.VK_A:
                if (xcoor!=0) {
                    xcoor -= 30;
                }
                break;
            case KeyEvent.VK_D:
                if (xcoor!=450) {
                    xcoor += 30;
                }
                break;
            default:
                xcoor+=0;
        }
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {

    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        Point p = mouseEvent.getPoint();
        if(startButton.contains(p)){
            owner.enableGameBoard();
        }
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        Point p = mouseEvent.getPoint();
        if(startButton.contains(p)) {
            startClicked = true;
            repaint(startButton.x, startButton.y, startButton.width + 1, startButton.height + 1);
        }
    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
        if(startClicked ){
            startClicked = false;
            repaint(startButton.x,startButton.y,startButton.width+1,startButton.height+1);
        }
    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseDragged(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseMoved(MouseEvent mouseEvent) {
        Point p = mouseEvent.getPoint();
        if(startButton != null && showInstructions) {
            if (startButton.contains(p))
                this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            else
                this.setCursor(Cursor.getDefaultCursor());
        }
        else{
            this.setCursor(Cursor.getDefaultCursor());
        }
    }

}
