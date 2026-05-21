package arrays;

import java.util.ArrayList;
import java.util.List;

public class MusicalChairs {

    public static int findWinner(int[] songLengths, int[] eliminationOrder, int[] playerOrder) {
        int n = playerOrder.length;
        List<Integer> players = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            players.add(playerOrder[i - 1]);
        }
        int currentPos = 0;
        int currentIndex = 0; // Index to keep track of the current song length

        for (int elimPos : eliminationOrder) {
            if (currentPos == elimPos) {
                return -1; // Essa's initial position is eliminated
            }
            players.remove((Integer) elimPos);
            if (currentPos > elimPos) {
                currentPos--;
            }
            if (currentPos > players.size()) {
                currentPos = 1;
            }

            // Update current position based on song length
            int songLength = songLengths[currentIndex];
            if (songLength % 2 == 0) {
                // Move clockwise
                currentPos = (currentPos + songLength) % players.size();
                if (currentPos == 0) {
                    currentPos = players.size();
                }
            } else {
                // Move counterclockwise
                currentPos = (currentPos - songLength + players.size()) % players.size();
                if (currentPos == 0) {
                    currentPos = players.size();
                }
            }
            currentIndex++;
        }

        return players.get(players.size() - 1); // Return the winner's position
    }

    public static void main(String[] args) {
        int[] songLengths = {4, 7, 100};
        int[] eliminationOrder = {2, 0, 1};
        int[] playerOrder = {1, 2, 3, 4};

        int winner = findWinner(songLengths, eliminationOrder, playerOrder);
        if (winner == -1) {
            System.out.println("No winner");
        } else {
            System.out.println("Winner is at position " + winner);
        }
    }
}
