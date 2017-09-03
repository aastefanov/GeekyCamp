import java.util.Scanner;

public class TicTacToe {
    Element  player;

    public static void main(String[] args) {

        TicTacToe game = new TicTacToe();

        try (Scanner scanner = new Scanner(System.in)) {
            for (; ; ) {
                System.out.println();
                game.printBoard();
                if (game.boardFull()) {
                    System.out.println("The game is draw");
                    return;
                }
                else if (game.getWinner() != Element.Empty)  {
                    System.out.printf("%s wins\n", game.getWinner().name());
                    return;
                }
                System.out.printf("Player %s, enter your turn (1-9): ", game.player.name());
                if(!game.playTurn(scanner.nextInt())) System.out.println("Invalid turn.");
            }
        }
    }

    private Element getWinner() {
        //check by rows
        for(int i=0; i<elements.length; i+=3) {
            if(elements[i] == elements[i+1] && elements[i+1] == elements[i+2]) return elements[i];
        }
        //check by cols
        for (int i = 0; i < 3; i++) {
            if(elements[i] == elements[i+3] && elements[i+3] == elements[i+6]) return elements[i];
        }

        //check diagonals
        if(elements[0] == elements[4] && elements[4] == elements[8]) return elements[0];
        if(elements[2] == elements[4] && elements[4] == elements[6]) return elements[0];

        return Element.Empty;
    }

    TicTacToe() {
        // 2d array as 1d
        elements = new Element[9];
        for (int i = 0; i < elements.length; i++) {
            elements[i] = Element.Empty;
        }

        player = Element.X;
    }

    private Element[] elements;

    void printBoard() {
        int i, j;
        for (i = 1; i <= 3; i++) {
            for (j = 1; j <= 2; j++) {
                System.out.printf("%s | ", printElement((i - 1) * 3 + j));
            }

            // ugly fix
            System.out.printf("%s\n", printElement((i - 1) * 3 + 3));
            if (i != 3) System.out.println("---|---|---");
        }
    }

    String printElement(int position) {
        if (elements[position - 1] == Element.Empty) return Integer.toString(position);
        return elements[position - 1].name();
    }

    // Returns true if the turn is valid
    boolean playTurn(int turn) {
        // check winner - not possible to play finished game; not expected to be reached
        if(this.getWinner() != Element.Empty) throw new Error();
        if (turn < 1 || turn > 9 || elements[turn - 1] != Element.Empty) return false;
        elements[turn - 1] = player;
        player = (player == Element.X)?Element.O:Element.X;
        return true;
    }

    boolean boardFull() {
        for (Element i : elements) if (i == Element.Empty) return false;
        return true;
    }
}

enum Element {
    Empty,
    X,
    O
}