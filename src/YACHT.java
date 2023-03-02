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

  public int score(String dice, String category) {
    // Initialise data
    CobolFieldAttribute a_1 = new CobolFieldAttribute(16, 5, 0, 0, null);
    CobolDataStorage b_WS_DICE = new CobolDataStorage(5);
    AbstractCobolField f_WS_DICE = CobolFieldFactory.makeCobolField(5, b_WS_DICE, a_1);

    CobolFieldAttribute a_3 = new CobolFieldAttribute(33, 0, 0, 0, null);
    CobolDataStorage b_WS_CATEGORY = new CobolDataStorage(15);
    AbstractCobolField f_WS_CATEGORY = CobolFieldFactory.makeCobolField(15, b_WS_CATEGORY, a_3);

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
    int score = YachtJava.score(diceRolls, categoryFromCobol);

    // Convert the java score to COBOL
    CobolFieldAttribute a_4 = new CobolFieldAttribute(16, 2, 0, 0, null);
    CobolDataStorage b_WS_RESULT = new CobolDataStorage(2);
    AbstractCobolField f_WS_RESULT = CobolFieldFactory.makeCobolField(2, b_WS_RESULT, a_4);
    f_WS_RESULT.setInt(score);

    return f_WS_RESULT;
  }

  private List<Integer> getDiceRolls(AbstractCobolField fWsDice) {
    /* YACHT.cobol:27: UNSTRING */
    CobolDataStorage b_WS_WORKING = new CobolDataStorage(15);
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
