import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toMap;

public class YachtJava {
    public static int score(List<Integer> diceRolls, String category) {
        return switch (category) {
            case "yacht"           -> scoreYacht(diceRolls);
            case "ones"            -> scoreNumbers(1, diceRolls);
            case "twos"            -> scoreNumbers(2, diceRolls);
            case "threes"          -> scoreNumbers(3, diceRolls);
            case "fours"           -> scoreNumbers(4, diceRolls);
            case "fives"           -> scoreNumbers(5, diceRolls);
            case "sixes"           -> scoreNumbers(6, diceRolls);
            case "full house"      -> scoreFullHouse(diceRolls);
            case "four of a kind"  -> scoreFourOfAKind(diceRolls);
            case "little straight" -> scoreStraight(6, diceRolls);
            case "big straight"    -> scoreStraight(1, diceRolls);
            case "choice"          -> scoreAllDice(diceRolls);
            default                -> 0;
        };
    }

    private static int scoreYacht(List<Integer> diceRolls) {
        if (numberOfDistinctDice(diceRolls) == 1)
            return 50;

        return 0;
    }

    private static int scoreNumbers(int value, List<Integer> diceRolls) {
        int numberOfRollsOfValue = (int)diceRolls.stream().filter(r -> r == value).count();
        return numberOfRollsOfValue * value;
    }

    private static int scoreFullHouse(List<Integer> diceRolls) {
        Map<Integer, Integer> diceCounts = countDistinctDice(diceRolls);
        if (diceCounts.size() == 2) {
            int numOfFirstDie = diceCounts.values().stream().findFirst().get();
            if (numOfFirstDie == 2 || numOfFirstDie == 3) {
                return scoreAllDice(diceRolls);
            }
        }
        return 0;
    }

    private static int scoreFourOfAKind(List<Integer> diceRolls) {
        Map<Integer, Integer> diceCounts = countDistinctDice(diceRolls);
        if (diceCounts.size() <= 2)
        {
            for (int dieValue: diceCounts.keySet())
            {
                int count = diceCounts.get(dieValue);
                if (count >= 4)
                    return 4 * dieValue;
            }
        }
        return 0;
    }

    private static int scoreStraight(int absentRoll, List<Integer> diceRolls) {
        if (numberOfDistinctDice(diceRolls) == 5 &&
                !diceRolls.contains(absentRoll))
            return 30;

        return 0;
    }

    private static int scoreAllDice(List<Integer> diceRolls) {
        return diceRolls.stream().mapToInt(i -> i).sum();
    }

    private static int numberOfDistinctDice(List<Integer> diceRolls) {
        return countDistinctDice(diceRolls).size();
    }

    private static Map<Integer, Integer> countDistinctDice(List<Integer> rolledDice) {
        return rolledDice.stream().collect(groupingBy(r -> r))
                .entrySet().stream().collect(toMap(e -> e.getKey(),
                        e -> e.getValue().size()));
    }
}
