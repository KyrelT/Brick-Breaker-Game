package main.java;


import main.Controller.BrickControl;
import main.Controller.PlayerControl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.io.*;
import java.util.Random;


/**
 * Pause Menu
 */
public class GameBoard extends JComponent implements KeyListener,MouseListener,MouseMotionListener {

    private static final String CONTINUE = "Continue";
    private static final String INSTRUCTIONS = "Instructions";
    private static final String RESTART = "Restart";
    private static final String OPTIONS = "Options";
    private static final String EXIT = "Exit";
    private static final String PAUSE = "Pause Menu";
    private static final String BACK = "Press Esc back to Menu";
    private static final int TEXT_SIZE = 30;
    private static final Color MENU_COLOR = new Color(100,205,150);
    private static final Color TEXT_COLOR = new Color(116, 52, 166);

    private static final int DEF_WIDTH = 600;
    private static final int DEF_HEIGHT = 450;

    private static final Color BG_COLOR = Color.WHITE;
    private static String name;
    public Point2D down;

    public Timer gameTimer;

    Wall wall;

    private Random rnd;

    private String message;
    private String message2;
    private String highScoreMessage;

    private boolean showPauseMenu;
    private boolean showInstructions;
    private boolean showleaderboard;

    public boolean collected;
    public boolean ispowerSpawn;

    private Font menuFont;
    private Font TitleFont;
    private Font AFont;
    private Font DFont;
    private Font SpaceFont;
    private Font BackFont;

    private Rectangle continueButtonRect;
    private Rectangle exitButtonRect;
    private Rectangle restartButtonRect;
    private Rectangle instructionsButtonRect;
    private Rectangle optionsButtonRect;
    private Rectangle backButton;
    private int strLen;

    private DebugConsole debugConsole;
    private int option;

    private Point2D center;
    private Powerup p;
    private Font ScoreboardFont;
    private File reader;


    /**
     * @param owner owner
     */
    public GameBoard(JFrame owner){
        super();

        strLen = 0;
        showPauseMenu = false;


        menuFont = new Font("Monospaced",Font.PLAIN,TEXT_SIZE);

        rnd = new Random();

        this.initialize();
        message = "";
        wall = new Wall(new Rectangle(0,0,DEF_WIDTH,DEF_HEIGHT),30,3,6/2,new Point(300,430));
        debugConsole = new DebugConsole(owner,wall,this);
        //initialize the first level
        wall.nextLevel();
        gameTimer = new Timer(10,e ->{ // for every 10 secs, it will check for any updates in the game
            wall.move();                     // for example if you change the value 10 into 1000 the updates on the game will be very slow
            wall.findImpacts();
            message = String.format("Bricks: %d Balls %d Score: %d",wall.getBrickCount(),wall.getBallCount(),wall.getCurrentHighScore());
//            highScoreMessage = String.format("Score: %d",wall.getCurrentHighScore());
            if(wall.isBallLost()){

                if(wall.ballEnd()){
                    wall.wallReset();
                    message = String.format("Game Over! Your Highscore: %d",wall.getFinalHighScore());
                    input();
                    viewLeaderBoard();
                }
                wall.ballReset();
                gameTimer.stop();
            }
            else if(wall.isDone()){
                if(wall.hasLevel()){
                    message = "Go to Next Level";
                    message2 = "Perfect Level!!";
                    gameTimer.stop();
                    wall.ballReset();
                    wall.wallReset();
                    wall.nextLevel();
                }
                else{
                    message = "ALL WALLS DESTROYED";
                    gameTimer.stop();
                }
            }
                if (wall.p != null) {
                    wall.p.drop();
                    this.p = wall.p;
                    if (PlayerControl.getInstance().getPlayerFace().contains(p.getPowerup().getLocation())) { // wall.player
                        System.out.print("add bonus score\n");
                        wall.setCollected(true);
                    }
                }
            repaint();
        });
        TitleFont = new Font("Noto Mono",Font.BOLD,30);
        AFont = new Font("Noto Mono",Font.BOLD,25);
        DFont = new Font("Noto Mono",Font.BOLD,25);
        SpaceFont = new Font("Noto Mono",Font.BOLD,25);
        BackFont = new Font("Noto Mono",Font.BOLD,25);

    }

    /**
     * @return name
     */
    public static String getname() {
        return name;
    }


    private void initialize(){
        this.setPreferredSize(new Dimension(DEF_WIDTH,DEF_HEIGHT));
        this.setFocusable(true);
        this.requestFocusInWindow();
        this.addKeyListener(this);
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
    }


    /**
     * @param g graphics
     */
    public void paint(Graphics g){

        Graphics2D g2d = (Graphics2D) g;

        clear(g2d);

        g2d.setColor(Color.BLUE);
        g2d.drawString(message,210,225);

        wall.ball.ballView.drawBall(wall.getBall(),g2d);

        if (wall.isPowerup) {
            drawPower(g2d);
        }

        for(BrickControl b : wall.getBricks()) // brick b : wall.bricks
            if(!b.isBroken()) // !b.isBroken()
                b.brickView.drawBrick(b,g2d);

        wall.player.playerView.drawPlayer(PlayerControl.getInstance(),g2d);

        if(showPauseMenu)
            drawMenu(g2d);

        if(showInstructions)
            drawInstructions(g2d);

        if(showleaderboard) {
            try {
                drawLeaderboard(g2d);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        Toolkit.getDefaultToolkit().sync();
    }

    /**
     * @param g2d graphics
     */
    private void clear(Graphics2D g2d){
        Color tmp = g2d.getColor();
        g2d.setColor(BG_COLOR);
        g2d.fillRect(0,0,getWidth(),getHeight());
        g2d.setColor(tmp);
    }

 /*   public void drawBrick(BrickControl brickController,Graphics2D g2d){


        Color tmp = g2d.getColor();
        g2d.setColor(brickController.getInnerColor());
        g2d.fill(brickController.getBrick());

        g2d.setColor(brickController.getBorderColor());
        g2d.draw(brickController.getBrick());


        g2d.setColor(tmp);
    }*/

    /**
     * @param g2d graphics for drawPower
     */
    private void drawPower(Graphics2D g2d){
        g2d.setColor(new Color(0,0,0));
        g2d.fill(p.getPowerup());
    }

    private Rectangle makeRectangle(int x,int y,int width,int height){
        Point p = new Point((int)(x),y);
        return  new Rectangle(p,new Dimension(width,height));
    }

    /**
     * @param ball ball object
     * @param g2d graphics for ball
     */
/*    private void drawBall(BallControl ball, Graphics2D g2d){
        Color tmp = g2d.getColor();

        Shape s = ball.getBallFace();

        g2d.setColor(ball.getInnerColor());
        g2d.fill(s);

        g2d.setColor(ball.getBorderColor());
        g2d.draw(s);

        g2d.setColor(tmp);
    }*/

    /**
     * @param p player object
     * @param g2d graphics for player
     */
/*    private void drawPlayer(PlayerControl p, Graphics2D g2d){
        Color tmp = g2d.getColor();

        Shape s = p.getPlayerFace();
        g2d.setColor(PlayerModel.INNER_COLOR);
        g2d.fill(s);

        g2d.setColor(PlayerModel.BORDER_COLOR);
        g2d.draw(s);

        g2d.setColor(tmp);
    }*/

    /**
     * @param g2d graphics for menu
     */
    private void drawMenu(Graphics2D g2d){
        obscureGameBoard(g2d);
        drawPauseMenu(g2d);
    }

    /**
     * @param g2d graphics for instructions
     */
    private void drawInstructions(Graphics2D g2d){
        obscureGameBoard(g2d);
        g2d.setColor(new Color(180,240,180)); // background color
        Rectangle InstructionFace = new Rectangle(new Point(0, 0), new Dimension(600,450));
        g2d.fill(InstructionFace);

        g2d.setColor(TEXT_COLOR);

        FontRenderContext frc = g2d.getFontRenderContext();

        Rectangle2D TitleRect = TitleFont.getStringBounds(INSTRUCTIONS,frc);
        Rectangle2D ARect = AFont.getStringBounds("A To Move Left",frc);
        Rectangle2D DRect = DFont.getStringBounds("D To Move Right",frc);
        Rectangle2D SpaceRect = SpaceFont.getStringBounds("Spacebar To Pause the Game",frc);
        Rectangle2D Back = BackFont.getStringBounds("Press Esc back to Menu",frc);

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

        Font tmpFont = g2d.getFont();
        Color tmpColor = g2d.getColor();

        g2d.setFont(BackFont);
        g2d.setColor(Color.BLACK);

        int x = 300;
        int y = 400;

        if(strLen == 0){
            strLen = BackFont.getStringBounds(BACK,frc).getBounds().width;
        }

        if(backButton == null){
            backButton = BackFont.getStringBounds(BACK,frc).getBounds();
            backButton.setLocation(x,y-backButton.height);
        }

        g2d.drawString(BACK,x,y);

        g2d.setFont(tmpFont);
        g2d.setColor(tmpColor);


    }

    /**
     * @param g2d graphics for game board
     */
    private void obscureGameBoard(Graphics2D g2d){

        Composite tmp = g2d.getComposite();
        Color tmpColor = g2d.getColor();

        AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER,0.55f);
        g2d.setComposite(ac);

        g2d.setColor(Color.BLACK);
        g2d.fillRect(0,0,DEF_WIDTH,DEF_HEIGHT);

        g2d.setComposite(tmp);
        g2d.setColor(tmpColor);
    }

    /**
     * @param g2d graphics for pause menu
     */
    private void drawPauseMenu(Graphics2D g2d){
        Font tmpFont = g2d.getFont();
        Color tmpColor = g2d.getColor();


        g2d.setFont(menuFont);
        g2d.setColor(MENU_COLOR);

        if(strLen == 0){
            FontRenderContext frc = g2d.getFontRenderContext();
            strLen = menuFont.getStringBounds(PAUSE,frc).getBounds().width;
        }

        int x = (this.getWidth() - strLen) / 2;
        int y = this.getHeight() / 10;

        g2d.drawString(PAUSE,x,y);

        x = this.getWidth() / 8;
        y = this.getHeight() / 4;


        if(continueButtonRect == null){
            FontRenderContext frc = g2d.getFontRenderContext();
            continueButtonRect = menuFont.getStringBounds(CONTINUE,frc).getBounds();
            continueButtonRect.setLocation(x,y-continueButtonRect.height);
        }

        g2d.drawString(CONTINUE,x,y);

        y *= 1.5;

        if(restartButtonRect == null){
            restartButtonRect = (Rectangle) continueButtonRect.clone();
            restartButtonRect.setLocation(x,y-restartButtonRect.height);
        }
        g2d.drawString(RESTART,x,y);

        y *= 2.8/2;

        if(instructionsButtonRect == null){
            instructionsButtonRect = (Rectangle) continueButtonRect.clone();
            instructionsButtonRect.setLocation(x,y-instructionsButtonRect.height);
        }

        g2d.drawString(INSTRUCTIONS,x,y);

        y *= 2.5/2;

        g2d.drawString(OPTIONS,x,y);

        if(optionsButtonRect == null){
            optionsButtonRect = (Rectangle) continueButtonRect.clone();
            optionsButtonRect.setLocation(x,y-optionsButtonRect.height);
        }

        y *= 2.5/2;

        if(exitButtonRect == null){
            exitButtonRect = (Rectangle) continueButtonRect.clone();
            exitButtonRect.setLocation(x,y-exitButtonRect.height);
        }

        g2d.drawString(EXIT,x,y);



        g2d.setFont(tmpFont);
        g2d.setColor(tmpColor);
    }

    /**
     * @param g2d graphics for leaderboard
     * @throws IOException
     */
    public void drawLeaderboard(Graphics2D g2d) throws IOException {
        obscureGameBoard(g2d);
        g2d.setColor(new Color(180,240,180)); // background color
        Rectangle InstructionFace = new Rectangle(new Point(0, 0), new Dimension(600,450));
        g2d.fill(InstructionFace);



        String row = " ";
        String name = " ";
        int ranking = 0 ;
        int highscore ;
        this.name = name;



        ScoreboardFont = new Font("Monospaced", Font.PLAIN, TEXT_SIZE);


        obscureGameBoard(g2d);
        FontRenderContext frc = g2d.getFontRenderContext();
        g2d.setFont(ScoreboardFont);

        Rectangle2D ScoreRect= ScoreboardFont.getStringBounds("hi",frc);
        Rectangle ScoreboardFace = new Rectangle(new Point(0, 0), new Dimension(600, 450));
        g2d.fill(ScoreboardFace);
        int y;
        g2d.setColor(Color.black);





        y=  70;

        g2d.drawString("rank",100,y);

        g2d.drawString("name",200,y);

        g2d.drawString("highscore",350,y);


        File reader = new File("src/main/resources/Highscore.txt");

        BufferedReader textread;

        textread = new BufferedReader(new FileReader(reader));


        for(int i = 0; i < 10  ; i ++) {

            y +=50 ;
            row = textread.readLine();
            if (row != null) {
                String[] data = row.split(",");
                ranking = Integer.parseInt(data[2]);
                name = data[0];
                highscore = Integer.parseInt(data[1]);


                g2d.drawString(String.valueOf(ranking), 100, y);
                g2d.drawString(name, 200 , y );
                g2d.drawString(String.valueOf(highscore),350 , y );



            }
        }
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {
    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        switch(keyEvent.getKeyCode()){
            case KeyEvent.VK_A:
                PlayerControl.getInstance().moveLeft();
                break;
            case KeyEvent.VK_D:
                PlayerControl.getInstance().moveRight();
                break;
            case KeyEvent.VK_ESCAPE:
                showPauseMenu = !showPauseMenu;
                if (showInstructions==true){
                    showInstructions =!showInstructions;
                }
                repaint();
                gameTimer.stop();
                break;
            case KeyEvent.VK_SPACE:
                if(!showPauseMenu)
                    if(gameTimer.isRunning())
                        gameTimer.stop();
                    else
                        gameTimer.start();
                break;
            case KeyEvent.VK_F1:
                if(keyEvent.isAltDown() && keyEvent.isShiftDown())
                    debugConsole.setVisible(true);
            case KeyEvent.VK_I:
                showInstructions =!showInstructions;
                repaint();
                gameTimer.stop();
                break;
            default:
                PlayerControl.getInstance().stop();
        }
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
        PlayerControl.getInstance().stop();
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        Point p = mouseEvent.getPoint();
        if(!showPauseMenu)
            return;
        if(continueButtonRect.contains(p)){
            showPauseMenu = false;
            repaint();
        }
        else if(restartButtonRect.contains(p)){
            message = "Restarting Game...";
            wall.ballReset();
            wall.wallReset();
            showPauseMenu = false;
            repaint();
        }
        else if(exitButtonRect.contains(p)){
            System.exit(0);
        }
        else if (instructionsButtonRect.contains(p)){
            showPauseMenu = false; // to close away the pause menu after clicking the buttons
            showInstructions = true;
            repaint();
        }
        else if (optionsButtonRect.contains(p)){
            debugConsole.setVisible(true);
            showPauseMenu = false;
            repaint();
        }
        else if (backButton.contains(p)){
            showInstructions = false;
            showPauseMenu = true;
            repaint();
        }

    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {

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
    public void mouseMoved(MouseEvent mouseEvent) { // just to show the mouse cursor
        Point p = mouseEvent.getPoint();
        if(exitButtonRect != null && showPauseMenu) {
            if (exitButtonRect.contains(p) || continueButtonRect.contains(p) || restartButtonRect.contains(p) || instructionsButtonRect.contains(p) || optionsButtonRect.contains(p))
                this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            else
                this.setCursor(Cursor.getDefaultCursor());
        }
        else{
            this.setCursor(Cursor.getDefaultCursor());
        }
    }

    public void onLostFocus(){
        gameTimer.stop();
        message = "Focus Lost";
        repaint();
    }

    public void input(){
        name = JOptionPane.showInputDialog(this, "Game over! please input your name");
        this.name = name;
        //Highscore.write();
    }
    public void viewLeaderBoard(){
        option = JOptionPane.showConfirmDialog(this,"check out the leaderboard?");
        switch (option){
            case JOptionPane.YES_OPTION:
                showleaderboard = true;
                //Highscore.read();
                repaint();
                break;
            case JOptionPane.NO_OPTION:
                showleaderboard = false;
                repaint();
                break;
            default:
                showleaderboard = false;
                repaint();
        }
    }

    public Point2D getPosition() {
        return center;
    }

    public boolean ispowerSpawn() {
        return ispowerSpawn;
    }
}
