package com.pascalrouw.sausageman;

import java.util.ArrayList;
import java.io.InputStream;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.BorderFactory;
import java.awt.Toolkit;
import java.awt.Cursor;
import java.io.File;
import java.awt.Image;
import java.awt.Point;
import javax.imageio.ImageIO;
import java.awt.Rectangle;

/**
* Main is a class for making the JFrame and the components
* that are to put in the JFrame
* 
* @author Melvin Versluijs
* @author Pascal Rouw
*
* @version 2013.11.21
*/
public class Frame {
    private JFrame frame;
    private MusicPlayer mPlayer;
    private Font sausageFont;
    private float fontSize;
    
    private JPanel loadPanel;
    private JLabel loadLabel;
    
    private Cursor cDefault;
    private Cursor cSelect;
    
    private JPanel menuPanel;
    private JLabel title;
    private ArrayList<JLabel> menuSelections;
    private ImageIcon arrowLeft;
    private ImageIcon arrowRight;
    private ImageIcon arrowLeftSel;
    private ImageIcon arrowRightSel;
    private JLabel madeBy;
    
    private JPanel gamePanel;
    
    private JPanel uiPanel;
    private JLabel score;
    private JLabel coin;
    private ImageIcon heartIcon;
    private ImageIcon heartIconEmpty;
    private JLabel heart1;
    private JLabel heart2;
    private JLabel heart3;
    
    
    /**
     * Create a JFrame, create Sausage Font and initiate MusicPlayer
     */
    public Frame() {
        frame = new JFrame( "Game" );
        frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        frame.setResizable( true );
        frame.setLayout(null);
        frame.setBounds(0,0,1280,770);
        
        mPlayer = new MusicPlayer();
        
        try {
            InputStream in = this.getClass().getResourceAsStream("Resources/Fonts/KGNexttoMeSolid.ttf");  
            sausageFont = Font.createFont(Font.TRUETYPE_FONT, in);
            fontSize = 20f;
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
        initLoadingScreen();
    }
    public JFrame getFrame() {
        return frame;
    }
    public Font getSausageFont() {
        return sausageFont;
    }
    public MusicPlayer getMPlayer() {
        return mPlayer;
    }
    
    
    
    
    /**
     * Create load JPanel together with the objects within it
     */
    public void initLoadingScreen() {
        loadPanel = new JPanel();
        loadPanel.setLayout( null );
        loadPanel.setBounds(0,0,1280,770);
        loadPanel.setBackground( Color.BLACK );
        loadPanel.setVisible(true);
        
        ImageIcon loadIcon = new ImageIcon(this.getClass().getResource("Resources/Images/handStand.gif"));
        loadLabel = new JLabel("");
        loadLabel.setIcon(loadIcon);
        loadLabel.setBounds(924,492,256,128);
        
        JLabel loadingLabel = new JLabel("Loading", JLabel.CENTER);
        loadingLabel.setBounds(924,620,256,30);
        loadingLabel.setFont(sausageFont.deriveFont(fontSize));
        loadingLabel.setForeground(Color.WHITE);
        
        loadPanel.add(loadingLabel);
        loadPanel.add(loadLabel);
        
        frame.add(loadPanel);
        frame.setVisible(true);
        
        
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - 1280) / 2);
        int y = (int) ((dimension.getHeight() - 770) / 2);
        
        Rectangle contentBounds = frame.getContentPane().getBounds();
        int xExtra = 1280 - contentBounds.width;
        int yExtra = 770 - contentBounds.height;
        frame.setBounds(x,y,1280 + xExtra,770 + yExtra);
        
        initCursors();
    }
    public JPanel getLoadPanel() {
        return loadPanel;
    }
    public JLabel getLoadLabel() {
        return loadLabel;
    }
    
    
    
    
    /**
     * Create cursors and set default cursor
     */
    public void initCursors() {
        try {
            File sourceimage;
            Image image;
            Point hotSpot;
            Toolkit toolkit = Toolkit.getDefaultToolkit();
            
            sourceimage = new File(this.getClass().getResource("Resources/Cursors/default.png").getPath());
            image = ImageIO.read(sourceimage); 
            hotSpot = new Point(0,7);
            cDefault = toolkit.createCustomCursor(image, hotSpot, "Default");
            
            sourceimage = new File(this.getClass().getResource("Resources/Cursors/select.png").getPath());
            image = ImageIO.read(sourceimage); 
            hotSpot = new Point(0,7);
            cSelect = toolkit.createCustomCursor(image, hotSpot, "Select");
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
        frame.setCursor(cDefault);
        initMenuPanel();
    }
    public Cursor getCDefault() {
        return cDefault;
    }
    public Cursor getCSelect() {
        return cSelect;
    }
    
    
    
    
    /**
     * Create menu JPanel and objects within it
     */
    public void initMenuPanel() {
        menuPanel = new JPanel();
        menuPanel.setLayout( null );
        menuPanel.setBounds(0,0,1280,770);
        menuPanel.setBackground( Color.GREEN );
        menuPanel.setVisible(true);
        
        ImageIcon titleIcon = new ImageIcon(this.getClass().getResource("Resources/Images/title.png"));
        title = new JLabel("", JLabel.CENTER);
        title.setBounds(0,0,1280,400);
        title.setIcon(titleIcon);
        
        madeBy = new JLabel("By Melvin Versluijs & Pascal Rouw", JLabel.RIGHT);
        madeBy.setFont(sausageFont.deriveFont(15f));
        madeBy.setBounds(878,745,400,25);
        
        JLabel menuSelection1 = new JLabel("", JLabel.CENTER);
        menuSelection1.setBounds(100,545,200,50);
        menuSelection1.setForeground(Color.WHITE);
        menuSelection1.setBackground(new Color(184,58,29));
        menuSelection1.setFont(sausageFont.deriveFont(fontSize));
        menuSelection1.setBorder(BorderFactory.createLineBorder(Color.black, 3));
        menuSelection1.setOpaque(true);
        
        JLabel menuSelection2 = new JLabel("", JLabel.CENTER);
        menuSelection2.setBounds(320,545,200,50);
        menuSelection2.setForeground(Color.WHITE);
        menuSelection2.setBackground(new Color(184,58,29));
        menuSelection2.setFont(sausageFont.deriveFont(fontSize));
        menuSelection2.setBorder(BorderFactory.createLineBorder(Color.black, 3));
        menuSelection2.setOpaque(true);
        
        JLabel menuSelection3 = new JLabel("", JLabel.CENTER);
        menuSelection3.setBounds(540,545,200,50);
        menuSelection3.setForeground(Color.WHITE);
        menuSelection3.setBackground(new Color(184,58,29));
        menuSelection3.setFont(sausageFont.deriveFont(fontSize));
        menuSelection3.setBorder(BorderFactory.createLineBorder(Color.black, 3));
        menuSelection3.setOpaque(true);
        
        JLabel menuSelection4 = new JLabel("", JLabel.CENTER);
        menuSelection4.setBounds(760,545,200,50);
        menuSelection4.setForeground(Color.WHITE);
        menuSelection4.setBackground(new Color(184,58,29));
        menuSelection4.setFont(sausageFont.deriveFont(fontSize));
        menuSelection4.setBorder(BorderFactory.createLineBorder(Color.black, 3));
        menuSelection4.setOpaque(true);
        
        JLabel menuSelection5 = new JLabel("", JLabel.CENTER);
        menuSelection5.setBounds(980,545,200,50);
        menuSelection5.setForeground(Color.WHITE);
        menuSelection5.setBackground(new Color(184,58,29));
        menuSelection5.setFont(sausageFont.deriveFont(fontSize));
        menuSelection5.setBorder(BorderFactory.createLineBorder(Color.black, 3));
        menuSelection5.setOpaque(true);
        
        JLabel menuBack = new JLabel("Back", JLabel.CENTER);
        menuBack.setBounds(540,645,200,50);
        menuBack.setForeground(Color.WHITE);
        menuBack.setBackground(new Color(184,58,29));
        menuBack.setFont(sausageFont.deriveFont(fontSize));
        menuBack.setBorder(BorderFactory.createLineBorder(Color.black, 3));
        menuBack.setOpaque(true);
        menuBack.setVisible(false);
        
        menuSelections = new ArrayList<JLabel>();
        menuSelections.add(menuSelection1);
        menuSelections.add(menuSelection2);
        menuSelections.add(menuSelection3);
        menuSelections.add(menuSelection4);
        menuSelections.add(menuSelection5);
        menuSelections.add(menuBack);
        
        JLabel menuBackground = new JLabel("");
        menuBackground.setBounds(0,0,1280,770);
        menuBackground.setBackground(Color.RED);
        menuBackground.setOpaque(true);
        ImageIcon tempImg = new ImageIcon(this.getClass().getResource("Resources/Images/menuBackground.png"));
        menuBackground.setIcon(tempImg);
        
        menuPanel.add(title);
        menuPanel.add(madeBy);
        menuPanel.add(menuSelection1);
        menuPanel.add(menuSelection2);
        menuPanel.add(menuSelection3);
        menuPanel.add(menuSelection4);
        menuPanel.add(menuSelection5);
        menuPanel.add(menuBack);
        menuPanel.add(menuBackground);
        
        initOptionPanel();
    }
    public JPanel getMenuPanel() {
        return menuPanel;
    }
    public JLabel getTitle() {
        return title;
    }
    public ArrayList<JLabel> getMenuSelections() {
        return menuSelections;
    }
    
    
    
    
    public void initOptionPanel(){
        arrowLeft = new ImageIcon(this.getClass().getResource("Resources/Images/arrow_left.png"));
        arrowRight = new ImageIcon(this.getClass().getResource("Resources/Images/arrow_right.png"));
        arrowLeftSel = new ImageIcon(this.getClass().getResource("Resources/Images/arrow_left_selected.png"));
        arrowRightSel = new ImageIcon(this.getClass().getResource("Resources/Images/arrow_right_selected.png"));
        
        JLabel arrow1Left = new JLabel();
        arrow1Left.setBounds(510,100,30,50);
        arrow1Left.setIcon(arrowLeft);
        arrow1Left.setVisible(false);
        
        JLabel arrow2Left = new JLabel();
        arrow2Left.setBounds(510,200,30,50);
        arrow2Left.setIcon(arrowLeft);
        arrow2Left.setVisible(false);
        
        JLabel arrow1Right = new JLabel();
        arrow1Right.setBounds(740,100,30,50);
        arrow1Right.setIcon(arrowRight);
        arrow1Right.setVisible(false);
        
        JLabel arrow2Right = new JLabel();
        arrow2Right.setBounds(740,200,30,50);
        arrow2Right.setIcon(arrowRight);
        arrow2Right.setVisible(false);
        
        menuPanel.add(arrow1Left);
        menuPanel.add(arrow2Left);
        menuPanel.add(arrow1Right);
        menuPanel.add(arrow2Right);
        
        initGamePanel();
    }
    public ImageIcon getArrowLeft(){
        return arrowLeft;
    }
    public ImageIcon getArrowRight(){
        return arrowRight;
    }
    public ImageIcon getArrowLeftSel(){
        return arrowLeftSel;
    }
    public ImageIcon getArrowRightSel(){
        return arrowRightSel;
    }
    
    
    
    
    /**
     * Create game JPanel
     */
    public void initGamePanel() {
        gamePanel = new JPanel();
        gamePanel.setLayout( null );
        gamePanel.setBounds(0,50,12960,720);
        gamePanel.setBackground( Color.BLUE );
        gamePanel.setVisible(true);
        
        initGameUI();
    }
    public JPanel getGamePanel() {
        return gamePanel;
    }
    
    public void initGameUI(){
        uiPanel = new JPanel();
        uiPanel.setLayout( null );
        uiPanel.setBounds(-3,-3,1286,53);
        uiPanel.setVisible(true);
        uiPanel.setBackground(new Color(230,79,37));
        uiPanel.setBorder(BorderFactory.createLineBorder(Color.black, 3));
        
        
        score = new JLabel("x 0", JLabel.CENTER);
        score.setBounds(530,0,50,50);
        score.setFont(sausageFont.deriveFont(15f));
        
        ImageIcon coinIcon = new ImageIcon(this.getClass().getResource("Resources/Images/coin.png"));
        coin = new JLabel();
        coin.setBounds(500,0,50,50);
        coin.setIcon(coinIcon);
        
        heartIcon = new ImageIcon(this.getClass().getResource("Resources/Images/heart_full.png"));
        heartIconEmpty = new ImageIcon(this.getClass().getResource("Resources/Images/heart_empty.png"));
        
        heart1 = new JLabel();
        heart1.setBounds(20,0,50,50);
        heart1.setIcon(heartIcon);
                
        heart2 = new JLabel();
        heart2.setBounds(60,0,50,50);
        heart2.setIcon(heartIcon);
                
        heart3 = new JLabel();
        heart3.setBounds(100,0,50,50);
        heart3.setIcon(heartIcon);
        
        uiPanel.add(score);
        uiPanel.add(coin);
        uiPanel.add(heart1);
        uiPanel.add(heart2);
        uiPanel.add(heart3);
        
        finish();
    }
    public JPanel getUiPanel() {
        return uiPanel;
    }
    public JLabel getScore(){
        return score;
    }
    public ImageIcon getHeartIcon(){
        return heartIcon;
    }
    public ImageIcon getHeartIconEmpty(){
        return heartIconEmpty;
    }
    public JLabel getHeart1() {
        return heart1;
    }
    public JLabel getHeart2() {
        return heart2;
    }
    public JLabel getHeart3() {
        return heart3;
    }
    
    
    
    /**
     * Finish loadin and 
     */
    public void finish() {
        new MenuMain(this);
    }
}
