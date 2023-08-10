import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

public class frame implements ActionListener, MouseListener{
    JButton menuButton, restartButton, startButton, easyButton, normalButton, hardButton;
    int i = 0;
    JFrame frame;
    ImageIcon diana = new ImageIcon("C:\\Users\\José\\IdeaProjects\\prubea981273912\\src\\diana.png");
    JLabel Circle1, Circle2, Circle3, difficultyPicker, title, highscore;
    Random random;
    int totalAmount = 10;
    int circleDiameter = 6;
    int spawnSpeedMS = 1000;


    static final int GAME_WIDTH = 500;
    static final int GAME_HEIGHT = 500;

    frame(){
        frame = new JFrame("Aim trainer");

        frame.setLayout(null);
        frame.setSize(GAME_WIDTH, GAME_HEIGHT);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Botones, dificultades y start;

        easyButton = new JButton("Easy");
        easyButton.setBounds(GAME_WIDTH-450, (150), 100, 30);
        easyButton.addActionListener(this);

        normalButton = new JButton("Normal");
        normalButton.setBounds(GAME_WIDTH-300, (150), 100, 30);
        normalButton.addActionListener(this);

        hardButton = new JButton("Hard");
        hardButton.setBounds(GAME_WIDTH-150, (150), 100, 30);
        hardButton.addActionListener(this);


        startButton = new JButton("Start ");
        startButton.setBounds((GAME_WIDTH/2)-50, (GAME_HEIGHT/2)-20, 100, 40);
        startButton.addActionListener(this);

        menuButton = new JButton("Menu!");
        menuButton.setBounds((GAME_WIDTH/2)-50, (GAME_HEIGHT/2)-20, 100, 40);
        menuButton.setVisible(false);
    /// LABELS

        title = new JLabel("AIM TRAINER");
        title.setFont(new Font("Calisto MT", Font.PLAIN, 30));
        title.setBounds(GAME_WIDTH-358, 20, 250, 100);


        difficultyPicker = new JLabel("CHOOSE DIFFICULTY");
        difficultyPicker.setBounds(GAME_WIDTH-308, (125), 150, 15);

        frame.add(title);
        frame.add(difficultyPicker);
        frame.add(easyButton);
        frame.add(normalButton);
        frame.add(hardButton);
        frame.add(startButton);
        frame.setVisible(true);
    }

    public void startCircles(){

        random = new Random();
        Circle1 = new JLabel();
        Circle2 = new JLabel();
        Circle3 = new JLabel();

        Circle1.setIcon(diana);
        Circle2.setIcon(diana);
        Circle3.setIcon(diana);

        Circle1.setBounds(random.nextInt(50, 430), random.nextInt(50, 422), 50, 50);
        Circle2.setBounds(random.nextInt(50, 430), random.nextInt(50, 422), 50, 50);
        Circle3.setBounds(random.nextInt(50, 430), random.nextInt(50, 422), 50, 50);

        Circle1.addMouseListener(this);
        Circle2.addMouseListener(this);
        Circle3.addMouseListener(this);

        frame.add(Circle1);
        frame.add(Circle2);
        frame.add(Circle3);
        frame.addMouseListener(this);
        frame.setVisible(false);
        frame.setVisible(true);
    }

    public void ending(){
        Circle1.setBounds(1000,1000,0,0);
        Circle2.setBounds(1000,1000,0,0);
        Circle3.setBounds(1000,1000,0,0);

        highscore = new JLabel(i+" out of "+totalAmount+"!");
        highscore.setFont(new Font(null, Font.PLAIN, 40));
        highscore.setBounds((GAME_WIDTH/2)-90, (GAME_HEIGHT/2)-140, 250, 40);
        frame.add(highscore);
        menuButton.setVisible(true);
        menuButton.addActionListener(this);
        frame.add(menuButton);
        restartButton = new JButton("Restart!");
        restartButton.setBounds((GAME_WIDTH/2)-50, (GAME_HEIGHT/2)-80, 100, 40);
        restartButton.addActionListener(this);
        frame.add(restartButton);
        i=0;
        frame.setVisible(false);
        frame.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource()== restartButton){
            menuButton.setVisible(false);
            restartButton.setVisible(false);
            highscore.setVisible(false);
            startCircles();
        }

        if (e.getSource()==startButton ){
            easyButton.setVisible(false);
            normalButton.setVisible(false);
            hardButton.setVisible(false);
            startButton.setVisible(false);
            title.setVisible(false);
            difficultyPicker.setVisible(false);

            startCircles();
        }

        if (e.getSource() == easyButton) {
            totalAmount = 5;
            circleDiameter = 8;
            spawnSpeedMS = 1500;
        }

        if (e.getSource() == normalButton) {
            totalAmount = 10;
            circleDiameter = 6;
            spawnSpeedMS = 1000;
        }

        if (e.getSource() == hardButton) {
            totalAmount = 30;
            circleDiameter = 5;
            spawnSpeedMS = 500;
        }

        if (e.getSource()== menuButton){
            easyButton.setVisible(true);
            normalButton.setVisible(true);
            hardButton.setVisible(true);
            startButton.setVisible(true);
            title.setVisible(true);
            difficultyPicker.setVisible(true);
            menuButton.setVisible(false);
            restartButton.setVisible(false);
            highscore.setVisible(false);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(e.getSource() == frame && !menuButton.isVisible() && !startButton.isVisible()){
            ending();
        }
        if (e.getSource()== Circle1 ) {
            if (i+1 != totalAmount){
                int newX = random.nextInt(50,430);
                int difference1 = Math.abs(newX-Circle2.getX());
                int difference2 = Math.abs(newX-Circle3.getX());
                while (difference2 < 50 || difference1 < 50 ) {
                    newX = random.nextInt(50,430);
                    difference1 = Math.abs(newX-Circle2.getX());
                    difference2 = Math.abs(newX-Circle3.getX());
                }
                Circle1.setBounds(newX, random.nextInt(50, 422), 50, 50);
                i++;
            }
            else {i++; ending();}
        }
        if (e.getSource()== Circle2 ) {
            if (i+1 != totalAmount){
                int newX = random.nextInt(50,430);
                int difference1 = Math.abs(newX-Circle1.getX());
                int difference2 = Math.abs(newX-Circle3.getX());
                while (difference2 < 50 || difference1 < 50 ) {
                    newX = random.nextInt(50,430);
                    difference1 = Math.abs(newX-Circle1.getX());
                    difference2 = Math.abs(newX-Circle3.getX());
                }
                Circle2.setBounds(newX, random.nextInt(50, 422), 50, 50);
                i++;}
            else {i++; ending();}
        }
        if (e.getSource()== Circle3 ) {
            if (i+1 != totalAmount){
                int newX = random.nextInt(50,430);
                int difference1 = Math.abs(newX-Circle1.getX());
                int difference2 = Math.abs(newX-Circle2.getX());
                while (difference2 < 50 || difference1 <50 ){
                    newX = random.nextInt(50,430);
                    difference1 = Math.abs(newX-Circle2.getX());
                    difference2 = Math.abs(newX-Circle1.getX());
                }
                Circle3.setBounds(newX, random.nextInt(50, 422), 50, 50);
                i++;}
            else {i++; ending();}
        }
    }


    // no lo uso jeje
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
