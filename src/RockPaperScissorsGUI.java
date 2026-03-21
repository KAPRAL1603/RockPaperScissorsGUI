import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class RockPaperScissorsGUI implements ActionListener {
    JFrame frame;
    JLabel scoreLabel;
    JLabel resultLabel;
    JLabel playerImageLabel;
    JLabel computerImageLabel;
    JButton rockButton;
    JButton scissorsButton;
    JButton paperButton;
    JButton resetButton;
    GameLogic gameLogic;
    ImageIcon rockIcon;
    ImageIcon scissorsIcon;
    ImageIcon paperIcon;

    public static void main(String[] args) {
        RockPaperScissorsGUI gui = new RockPaperScissorsGUI();
        gui.go();
    }

    public void go() {
        frame = new JFrame("КАМЕНЬ НОЖНИЦЫ БУМАГА");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Загрузка картинок
        try {
            rockIcon = new ImageIcon("images/rock.png");
            scissorsIcon = new ImageIcon("images/scissors.png");
            paperIcon = new ImageIcon("images/paper.png");
        } catch (Exception e) {
            System.out.println("Ошибка загрузки картинок");
        }

        rockButton = new JButton( rockIcon);
        scissorsButton = new JButton(scissorsIcon);
        paperButton = new JButton( paperIcon);
        resetButton = new JButton("СБРОС");

        rockButton.addActionListener(this);
        scissorsButton.addActionListener(this);
        paperButton.addActionListener(this);
        resetButton.addActionListener(this);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(rockButton);
        buttonPanel.add(scissorsButton);
        buttonPanel.add(paperButton);
        frame.getContentPane().add(buttonPanel, BorderLayout.NORTH);

        gameLogic = new GameLogic();

        JPanel choicePanel = new JPanel(new GridLayout(2, 2));
        choicePanel.add(new JLabel("ТЫ ВЫБРАЛ:", SwingConstants.CENTER));
        choicePanel.add(new JLabel("КОМПЬЮТЕР ВЫБРАЛ:", SwingConstants.CENTER));

        playerImageLabel = new JLabel("?", SwingConstants.CENTER);
        computerImageLabel = new JLabel("?", SwingConstants.CENTER);
        choicePanel.add(playerImageLabel);
        choicePanel.add(computerImageLabel);

        resultLabel = new JLabel("", SwingConstants.CENTER);
        resultLabel.setFont(new Font("Arial", Font.BOLD, 16));

        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.add(choicePanel, BorderLayout.CENTER);
        centerPanel.add(resultLabel, BorderLayout.SOUTH);
        frame.add(centerPanel, BorderLayout.CENTER);

        scoreLabel = new JLabel("Счёт: 0 : 0");
        JPanel bottomPanel = new JPanel();
        bottomPanel.add(scoreLabel);
        bottomPanel.add(resetButton);
        frame.add(bottomPanel, BorderLayout.SOUTH);

        frame.setSize(500, 500);
        frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent event) {

        if (event.getSource() == resetButton) {
            gameLogic.resetScores();
            scoreLabel.setText("Счёт: 0 : 0");
            playerImageLabel.setIcon(null);
            computerImageLabel.setIcon(null);
            playerImageLabel.setText("?");
            computerImageLabel.setText("?");
            resultLabel.setText("");
            return;
        }

        int playerChoice = 0;

        String playerText = "";
        if (event.getSource() == rockButton) {
            playerChoice = 1;
            playerText = "Ты выбрал: КАМЕНЬ ";
        } else if (event.getSource() == scissorsButton) {
            playerChoice = 2;
            playerText = "Ты выбрал: НОЖНИЦЫ ";
        } else if (event.getSource() == paperButton) {
            playerChoice = 3;
            playerText = "Ты выбрал: БУМАГУ ";
        }

        System.out.println(playerText);

        if (playerChoice == 1) {
            playerImageLabel.setIcon(rockIcon);
            playerImageLabel.setText("");
        } else if (playerChoice == 2) {
            playerImageLabel.setIcon(scissorsIcon);
            playerImageLabel.setText("");
        } else {
            playerImageLabel.setIcon(paperIcon);
            playerImageLabel.setText("");
        }

        int result = gameLogic.playRound(playerChoice);

        int computerChoice = gameLogic.getLastComputerChoice();
        String computerText;
        if (computerChoice == 1) {
            computerText = "Компьютер выбрал: КАМЕНЬ";
        } else if (computerChoice == 2) {
            computerText = "Компьютер выбрал: НОЖНИЦЫ";
        } else {
            computerText = "Компьютер выбрал: БУМАГУ";
        }

        if (computerChoice == 1) {
            computerImageLabel.setIcon(rockIcon);
            computerImageLabel.setText("");
        } else if (computerChoice == 2) {
            computerImageLabel.setIcon(scissorsIcon);
            computerImageLabel.setText("");
        } else {
            computerImageLabel.setIcon(paperIcon);
            computerImageLabel.setText("");
        }

        if (result == 1) {
            resultLabel.setText("ТЫ ВЫИГРАЛ!");
            resultLabel.setForeground(Color.GREEN);
        } else if (result == -1) {
            resultLabel.setText("ТЫ ПРОИГРАЛ!");
            resultLabel.setForeground(Color.RED);
        } else {
            resultLabel.setText("НИЧЬЯ!");
            resultLabel.setForeground(Color.GRAY);
        }

        scoreLabel.setText("Счёт: " + gameLogic.getPlayerScore() +
                " : " + gameLogic.getComputerScore());
        System.out.println(computerText);
        System.out.println("Результат раунда: " + result);
        System.out.println("Счёт: " + gameLogic.getPlayerScore() +
                " : " + gameLogic.getComputerScore());
    }
}