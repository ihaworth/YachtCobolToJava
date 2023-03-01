import jp.osscons.opensourcecobol.libcobj.call.CobolRunnable;
import jp.osscons.opensourcecobol.libcobj.common.CobolString;
import jp.osscons.opensourcecobol.libcobj.data.AbstractCobolField;
import jp.osscons.opensourcecobol.libcobj.data.CobolDataStorage;
import jp.osscons.opensourcecobol.libcobj.data.CobolFieldAttribute;
import jp.osscons.opensourcecobol.libcobj.data.CobolFieldFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toMap;

public class YACHT implements CobolRunnable {

  @Override
  public int run(CobolDataStorage... argStorages) {
    return 0;
  }

  @Override
  public void cancel() {
  }

  @Override
  public boolean isActive() {
    return false;
  }

  private static int score(List<Integer> diceRolls, String category) {
    /* YACHT.cobol:29: EVALUATE */
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

  private static int scoreAllDice(List<Integer> diceRolls) {
    return diceRolls.stream().mapToInt(i -> i).sum();
  }

  private static int scoreStraight(int absentRoll, List<Integer> diceRolls) {
    if (numberOfDistinctDice(diceRolls) == 5 &&
            !diceRolls.contains(absentRoll))
      return 30;

    return 0;
  }

  private static int numberOfDistinctDice(List<Integer> diceRolls) {
    return countDistinctDice(diceRolls).size();
  }

  private static Map<Integer, Integer> countDistinctDice(List<Integer> rolledDice) {
    return rolledDice.stream().collect(groupingBy(r -> r))
            .entrySet().stream().collect(toMap(e -> e.getKey(),
                                               e -> e.getValue().size()));
  }

  public int score(String dice, String category) {
    // Initialise data
    CobolFieldAttribute a_1 = new CobolFieldAttribute(16, 5, 0, 0, null);
    CobolDataStorage b_WS_DICE = new CobolDataStorage(5);    /* WS-DICE */
    AbstractCobolField f_WS_DICE = CobolFieldFactory.makeCobolField(5, b_WS_DICE, a_1);    /* WS-DICE */

    CobolFieldAttribute a_3 = new CobolFieldAttribute(33, 0, 0, 0, null);
    CobolDataStorage b_WS_CATEGORY = new CobolDataStorage(15);    /* WS-CATEGORY */
    AbstractCobolField f_WS_CATEGORY = CobolFieldFactory.makeCobolField(15, b_WS_CATEGORY, a_3);    /* WS-CATEGORY */

    // Convert java test parameters to COBOL
    b_WS_DICE.memcpy(dice, dice.length());
    b_WS_CATEGORY.memcpy(category, category.length());

    AbstractCobolField f_WS_RESULT = scoreCOBOL(f_WS_DICE, f_WS_CATEGORY);

    // Turn the COBOL result back into java for the tests to check
    return f_WS_RESULT.getInt();
  }

  private AbstractCobolField scoreCOBOL(AbstractCobolField f_WS_DICE, AbstractCobolField f_WS_CATEGORY) {
    // Convert COBOL input parameters to java
    List<Integer> diceRolls = getDiceRolls(f_WS_DICE);
    String categoryFromCobol = f_WS_CATEGORY.getString().trim();

    // Invoke the scoring algorithm
    int score = score(diceRolls, categoryFromCobol);

    // Convert the java score to COBOL
    CobolFieldAttribute a_4 = new CobolFieldAttribute(16, 2, 0, 0, null);
    CobolDataStorage b_WS_RESULT = new CobolDataStorage(2);    /* WS-RESULT */
    AbstractCobolField f_WS_RESULT = CobolFieldFactory.makeCobolField(2, b_WS_RESULT, a_4);    /* WS-RESULT */
    f_WS_RESULT.setInt(score);

    return f_WS_RESULT;
  }

  private List<Integer> getDiceRolls(AbstractCobolField fWsDice) {
    /* YACHT.cobol:27: UNSTRING */
    CobolDataStorage b_WS_WORKING = new CobolDataStorage(15);    /* WS-WORKING */
    b_WS_WORKING.fillBytes('0', 15);
    CobolFieldAttribute a_2 = new CobolFieldAttribute(16, 1, 0, 0, null);
    CobolString.unstringInit (fWsDice, 0, 0);
    CobolString.unstringInto (CobolFieldFactory.makeCobolField(1, b_WS_WORKING.getSubDataStorage(0), a_2), 0, 0);
    CobolString.unstringInto (CobolFieldFactory.makeCobolField(1, b_WS_WORKING.getSubDataStorage(1), a_2), 0, 0);
    CobolString.unstringInto (CobolFieldFactory.makeCobolField(1, b_WS_WORKING.getSubDataStorage(2), a_2), 0, 0);
    CobolString.unstringInto (CobolFieldFactory.makeCobolField(1, b_WS_WORKING.getSubDataStorage(3), a_2), 0, 0);
    CobolString.unstringInto (CobolFieldFactory.makeCobolField(1, b_WS_WORKING.getSubDataStorage(4), a_2), 0, 0);
    CobolString.unstringFinish ();

    List<Integer> rolledDice = new ArrayList<>();
    for (int i = 0; i < 5; i++)
    {
      int dieRoll = b_WS_WORKING.getSubDataStorage(i).getByte(0) - '0';
      rolledDice.add(dieRoll);
    }
    return rolledDice;
  }
}
