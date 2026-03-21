public class GameLogic {
    private int playerScore;
    private int computerScore;
    private int lastComputerChoice; // ← ДОБАВИЛИ ПОЛЕ

    public int playRound(int playerChoice) {
        // Генерируем случайный выбор компьютера (1=камень, 2=ножницы, 3=бумага)
        lastComputerChoice = (int) (Math.random() * 3) + 1; // ← СОХРАНЯЕМ В ПОЛЕ

        if (playerChoice == lastComputerChoice) {
            return 0; // ничья
        } else if ((playerChoice == 1 && lastComputerChoice == 2) ||
                (playerChoice == 2 && lastComputerChoice == 3) ||
                (playerChoice == 3 && lastComputerChoice == 1)) {
            playerScore++;
            return 1; // победа игрока
        } else {
            computerScore++;
            return -1; // поражение игрока
        }
    }

    // ← ДОБАВИЛИ ЭТОТ МЕТОД
    public int getLastComputerChoice() {
        return lastComputerChoice;
    }

    public int getPlayerScore() {
        return playerScore;
    }

    public int getComputerScore() {
        return computerScore;
    }

    public void resetScores() {
        playerScore = 0;
        computerScore = 0;
        lastComputerChoice = 0; // ← можно сбросить и это
    }
}