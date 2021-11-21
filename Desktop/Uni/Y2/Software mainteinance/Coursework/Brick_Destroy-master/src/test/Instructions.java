package test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;

import static com.sun.java.swing.plaf.motif.MotifBorders.FrameBorder.BORDER_SIZE;

public class Instructions extends JComponent implements KeyListener, MouseListener, MouseMotionListener {

    private static final String INSTRUCTIONS = "Instructions";
    private static final String BACK = "Back";
    private static final int TEXT_SIZE = 30;
    private static final Color MENU_COLOR = new Color(100,205,150);

    private boolean menuClicked;
    private boolean showInstructions;


    private static final Color BG_COLOR = Color.GREEN.darker();
    private static final Color TEXT_COLOR = new Color(116, 52, 166);
    private static final Color CLICKED_BUTTON_COLOR = BG_COLOR.brighter();
    private static final Color CLICKED_TEXT = Color.WHITE;

    private static final int DEF_WIDTH = 600;
    private static final int DEF_HEIGHT = 450;
    private final GameFrame owner;

    private Rectangle InstructionFace;
    private Rectangle menuButton;


    private Font menuFont;
    private Font TitleFont;
    private Font buttonFont;
    private Font AFont;
    private Font DFont;
    private Font SpaceFont;


    private int strLen;

    public Instructions(GameFrame owner,Dimension area){

        this.setFocusable(true);
        this.requestFocusInWindow();

        this.addMouseListener(this);
        this.addMouseMotionListener(this);

        this.owner = owner;


        Rectangle menuFace = new Rectangle(new Point(0, 0), area);
        this.setPreferredSize(area);

        Dimension btnDim = new Dimension(area.width / 3, area.height / 12);
        menuButton = new Rectangle(btnDim);


        TitleFont = new Font("Noto Mono",Font.BOLD,40);
        buttonFont = new Font("Monospaced",Font.PLAIN,30);



    }

    public void paint(Graphics g){
        drawInstructions((Graphics2D)g); // *********************************************************************************
    }

    public void drawInstructions(Graphics2D g2d){

        drawContainer(g2d); // *********************************************************************************


        //all the following method calls need a relative
        //painting directly into the HomeMenu rectangle,
        //so the translation is made here so the other methods do not do that.

        Color prevColor = g2d.getColor();
        Font prevFont = g2d.getFont();

        double x = InstructionFace.getX();
        double y = InstructionFace.getY();

        g2d.translate(x,y);

        //methods calls
        drawText(g2d);
        drawButton(g2d);
        //end of methods calls

        g2d.translate(-x,-y);
        g2d.setFont(prevFont);
        g2d.setColor(prevColor);
    }

    private void drawContainer(Graphics2D g2d){
        Color prev = g2d.getColor();

        g2d.setColor(BG_COLOR);
        g2d.fill(InstructionFace); // *********************************************************************************

        Stroke tmp = g2d.getStroke();

        g2d.draw(InstructionFace);

        g2d.draw(InstructionFace);

        g2d.setStroke(tmp);

        g2d.setColor(prev);
    }

    private void drawText(Graphics2D g2d){

        g2d.setColor(TEXT_COLOR);

        FontRenderContext frc = g2d.getFontRenderContext();

        Rectangle2D TitleRect = TitleFont.getStringBounds(INSTRUCTIONS,frc);

        int sX;
        int sY = 0;

        sX = (int)(InstructionFace.getWidth() - TitleRect.getWidth()) / 2;
        sY += (int) TitleRect.getHeight() * 1.1;//add 10% of String height between the two strings

        g2d.setFont(TitleFont);
        g2d.drawString(INSTRUCTIONS,sX,sY);


    }

    private void drawButton(Graphics2D g2d){

        FontRenderContext frc = g2d.getFontRenderContext();

        Rectangle2D mTxtRect = buttonFont.getStringBounds(BACK,frc);

        g2d.setFont(buttonFont);
        int x,y;
        x = 50;
        y = 100;
        menuButton.setLocation(x,y);

        x = (int)(menuButton.getWidth() - mTxtRect.getWidth()) / 2;
        y = (int)(menuButton.getHeight() - mTxtRect.getHeight()) / 2;

        x += menuButton.x;
        y += menuButton.y;

        if(menuClicked){
            Color tmp = g2d.getColor();

            g2d.setColor(CLICKED_BUTTON_COLOR);
            g2d.draw(menuButton);
            g2d.setColor(CLICKED_TEXT);
            g2d.drawString(BACK,x,y);
            g2d.setColor(tmp);
        }
        else{
            g2d.draw(menuButton);
            g2d.drawString(BACK,x,y);
        }

    }


    private void initialize(){
        this.setPreferredSize(new Dimension(DEF_WIDTH,DEF_HEIGHT));
        this.setFocusable(true);
        this.requestFocusInWindow();
        this.addKeyListener(this);
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
    }

    private void drawInstructionMenu(Graphics2D g2d){
        Font tmpFont = g2d.getFont();
        Color tmpColor = g2d.getColor();


        g2d.setFont(menuFont);
        g2d.setColor(MENU_COLOR);

        if(strLen == 0){
            FontRenderContext frc = g2d.getFontRenderContext();
            strLen = menuFont.getStringBounds(INSTRUCTIONS,frc).getBounds().width;
        }


    }
    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {

    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        Point p = mouseEvent.getPoint();
        if(menuButton.contains(p)){
            owner.enableInstructions();
        }
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        Point p = mouseEvent.getPoint();
        if(menuButton.contains(p)) {
            menuClicked = true;
            repaint(menuButton.x, menuButton.y, menuButton.width + 1, menuButton.height + 1);
        }
    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
        if(menuClicked){
            menuClicked = false;
            repaint(menuButton.x,menuButton.y,menuButton.width+1,menuButton.height+1);
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
        if(menuButton.contains(p))
            this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        else
            this.setCursor(Cursor.getDefaultCursor());

    }

}
