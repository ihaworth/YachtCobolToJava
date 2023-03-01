import jp.osscons.opensourcecobol.libcobj.call.CobolRunnable;
import jp.osscons.opensourcecobol.libcobj.common.CobolModule;
import jp.osscons.opensourcecobol.libcobj.common.CobolString;
import jp.osscons.opensourcecobol.libcobj.common.CobolUtil;
import jp.osscons.opensourcecobol.libcobj.data.*;
import jp.osscons.opensourcecobol.libcobj.exceptions.CobolGoBackException;
import jp.osscons.opensourcecobol.libcobj.exceptions.CobolStopRunException;
import jp.osscons.opensourcecobol.libcobj.ui.CobolResultSet;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toMap;

public class YACHT implements CobolRunnable {

  private boolean initialized = false;
  private static final boolean cobolInitialized = false;


  @Override
  public int run(CobolDataStorage... argStorages) {
    return YACHT_(0);
  }

  @Override
  public void cancel() {
    YACHT_(-1);
  }

  @Override
  public boolean isActive() {
    return false;
  }

  public CobolResultSet execute () {
    int returnCode = run_module(0);
    return new CobolResultSet(returnCode);
  }

  public int YACHT_ (int entry) {
    return this.run_module(entry);
  }

  int run_module (int entry) {
    CobolModule module = new CobolModule(null, null, null, null, 0, '.', '$', ',', 1, 1, 1, 0, null);

    /* Start of function code */

    /* CANCEL callback handling */
    if (entry < 0) {
    	if (!this.initialized) {
    		CobolDecimal.cobInitNumeric();
    		return 0;
    	}
    	d0.clear();
    	d0.setScale(0);
    	d1.clear();
    	d1.setScale(0);
    	this.initialized = false;
    	return 0;
    }

    /* Push module stack */
    CobolModule.push (module);

    /* Initialize program */
    if (!this.initialized) {
      /* Initialize decimal numbers */
      d0.decimalInit();
      d1.decimalInit();

      b_RETURN_CODE.set(0);
      b_I.set(1);
      b_J.set(1);
      b_WS_DICE.fillBytes('0', 5);
      b_WS_CATEGORY.fillBytes(' ', 15);
      b_WS_RESULT.fillBytes ('0', 2);
      b_WS_WORKING.fillBytes('0', 15);
      b_WS_NUMBER.setByte('0');
      b_WS_COUNT.setByte('0');
      b_WS_ABSENT.setByte('0');
      this.initialized = true;
    }
    /* PROCEDURE DIVISION */
    try{
      CobolStopRunException.dummy();
      CobolGoBackException.dummy();
      /* Entry dispatch */
      score();

    } catch(CobolGoBackException e) {
      return e.getReturnCode();
    } catch(CobolStopRunException e) {
      CobolStopRunException.stopRun();
      System.exit(e.getReturnCode());
    }
    /* Pop module stack */
    CobolModule.pop();

    /* Program return */
    return b_RETURN_CODE.intValue();
  }

  private void score() throws CobolStopRunException {
    /* YACHT.cobol:26: MOVE */
    b_WS_RESULT.fillBytes ('0', 2);

    /* YACHT.cobol:27: UNSTRING */
    CobolString.unstringInit (f_WS_DICE, 0, 0);
    CobolString.unstringInto (CobolFieldFactory.makeCobolField(1, b_WS_WORKING.getSubDataStorage(0), a_2), 0, 0);
    CobolString.unstringInto (CobolFieldFactory.makeCobolField(1, b_WS_WORKING.getSubDataStorage(1), a_2), 0, 0);
    CobolString.unstringInto (CobolFieldFactory.makeCobolField(1, b_WS_WORKING.getSubDataStorage(2), a_2), 0, 0);
    CobolString.unstringInto (CobolFieldFactory.makeCobolField(1, b_WS_WORKING.getSubDataStorage(3), a_2), 0, 0);
    CobolString.unstringInto (CobolFieldFactory.makeCobolField(1, b_WS_WORKING.getSubDataStorage(4), a_2), 0, 0);
    CobolString.unstringFinish ();

    final List<Integer> diceRolls = getDiceRolls();

    /* YACHT.cobol:29: EVALUATE */
    switch (YACHT.this.f_WS_CATEGORY.getString().trim()) {
      case "yacht"           -> setResult(scoreYacht(diceRolls));
      case "ones"            -> setResult(scoreNumbers(1, diceRolls));
      case "twos"            -> setResult(scoreNumbers(2, diceRolls));
      case "threes"          -> setResult(scoreNumbers(3, diceRolls));
      case "fours"           -> setResult(scoreNumbers(4, diceRolls));
      case "fives"           -> setResult(scoreNumbers(5, diceRolls));
      case "sixes"           -> setResult(scoreNumbers(6, diceRolls));
      case "full house"      -> setResult(scoreFullHouse(diceRolls));
      case "four of a kind"  -> scoreFourOfAKind(diceRolls);
      case "little straight" -> setResult(scoreStraight(6, diceRolls));
      case "big straight"    -> setResult(scoreStraight(1, diceRolls));
      case "choice"          -> setResult(scoreAllDice(diceRolls));
    }
  }

  private static int scoreAllDice(List<Integer> diceRolls) {
    int score = 0;
    for (int i = 0; i < 5; i++) {
      score += diceRolls.get(i);
    }
    return score;
  }

  private void setResult(int score) {
    b_WS_RESULT.setBytes("%02d".formatted(score), 2);
  }

  private static int scoreStraight(int absentRoll, List<Integer> diceRolls) {
    int score = 0;
    Map<Integer, Integer> diceCounts = countDistinctDice(diceRolls);
    if (diceCounts.size() == 5)
    {
      boolean valueIsAbsent = checkValueAbsent(absentRoll, diceRolls);
      if (valueIsAbsent)
      {
        score = 30;
      }
    }
    return score;
  }

  private static boolean checkValueAbsent(int absentRoll, List<Integer> diceRolls) {
    for (int i = 0; i < 5; i++)
    {
      if (diceRolls.get(i) == absentRoll) {
        return false;
      }
    }
    return true;
  }

  private void scoreFourOfAKind(List<Integer> diceRolls) {
    Map<Integer, Integer> diceCounts = countDistinctDice(diceRolls);
    if (diceCounts.size() <= 2)
    {
      for (int dieValue: diceCounts.keySet())
      {
        int count = diceCounts.get(dieValue);
        if (count >= 4)
        {
          setResult(4 * dieValue);
        }
      }
    }
  }

  private static int scoreFullHouse(List<Integer> diceRolls) {
    int score = 0;
    Map<Integer, Integer> diceCounts = countDistinctDice(diceRolls);
    if (diceCounts.size() == 2) {
      int numOfFirstDie = diceCounts.values().stream().findFirst().get();
      if (numOfFirstDie == 2 || numOfFirstDie == 3) {
        score = scoreAllDice(diceRolls);
      }
    }
    return score;
  }

  private static int scoreNumbers(int number, List<Integer> diceRolls) {
    int count = 0;
    for (int i = 0; i < 5; i++)
    {
      if (diceRolls.get(i) == number) {
        count++;
      }
    }
    return count * number;
  }

  private static int scoreYacht(List<Integer> diceRolls) {
    int score = 0;
    Map<Integer, Integer> diceCounts = countDistinctDice(diceRolls);
    if (diceCounts.size() == 1)
    {
      score = 50;
    }
    return score;
  }

  private static Map<Integer, Integer> countDistinctDice(List<Integer> rolledDice) {
    return rolledDice.stream().collect(groupingBy(r -> r))
            .entrySet().stream().collect(toMap(e -> e.getKey(),
                                               e -> e.getValue().size()));
  }

  private List<Integer> getDiceRolls() {
    List<Integer> rolledDice = new ArrayList<>();
    for (int i = 0; i < 5; i++)
    {
      int dieRoll = b_WS_WORKING.getSubDataStorage(i).getByte(0) - '0';
      rolledDice.add(dieRoll);
    }
    return rolledDice;
  }

  public static void main(String[] args)
  {
    CobolUtil.cob_init(args, cobolInitialized);
    CobolDecimal.cobInitNumeric();
    new YACHT().YACHT_(0);
    CobolStopRunException.stopRun();
  }

  public YACHT()
  {
    init();
  }

  public void init()
  {
    try {
      /* Decimal structures */

      d0 = new CobolDecimal();
      d1 = new CobolDecimal();

      /* Data storage */

      /* PROGRAM-ID : YACHT */
      b_RETURN_CODE = new CobolDataStorage(4);	/* RETURN-CODE */
      b_WS_DICE = new CobolDataStorage(5);	/* WS-DICE */
      b_WS_CATEGORY = new CobolDataStorage(15);	/* WS-CATEGORY */
      b_WS_RESULT = new CobolDataStorage(2);	/* WS-RESULT */
      b_WS_WORKING = new CobolDataStorage(15);	/* WS-WORKING */
      b_I = new CobolDataStorage(4);	/* I */
      b_J = new CobolDataStorage(4);	/* J */
      b_WS_NUMBER = new CobolDataStorage(1);	/* WS-NUMBER */
      b_WS_COUNT = new CobolDataStorage(1);	/* WS-COUNT */
      b_WS_ABSENT = new CobolDataStorage(1);	/* WS-ABSENT */

      /* End of data storage */


      initAttr();

      /* Fields */

      /* PROGRAM-ID : YACHT */
      f_WS_DICE	= CobolFieldFactory.makeCobolField(5, b_WS_DICE, a_1);	/* WS-DICE */
      f_WS_CATEGORY	= CobolFieldFactory.makeCobolField(15, b_WS_CATEGORY, a_3);	/* WS-CATEGORY */
      f_WS_RESULT	= CobolFieldFactory.makeCobolField(2, b_WS_RESULT, a_4);	/* WS-RESULT */

      /* End of fields */

    } catch(Exception e) {
      e.printStackTrace();
    }
  }

  private void initAttr() {
    /* Attributes */

    a_1 = new CobolFieldAttribute (16, 5, 0, 0, null);
    a_2 = new CobolFieldAttribute (16, 1, 0, 0, null);
    a_3 = new CobolFieldAttribute (33, 0, 0, 0, null);
    a_4 = new CobolFieldAttribute (16, 2, 0, 0, null);

  }

  /* Decimal structures */

  private CobolDecimal d0;
  private CobolDecimal d1;

  /* Data storage */

  /* PROGRAM-ID : YACHT */
  private CobolDataStorage b_RETURN_CODE;	/* RETURN-CODE */
  private CobolDataStorage b_WS_DICE;	/* WS-DICE */
  private CobolDataStorage b_WS_CATEGORY;	/* WS-CATEGORY */
  private CobolDataStorage b_WS_RESULT;	/* WS-RESULT */
  private CobolDataStorage b_WS_WORKING;	/* WS-WORKING */
  private CobolDataStorage b_I;	/* I */
  private CobolDataStorage b_J;	/* J */
  private CobolDataStorage b_WS_NUMBER;	/* WS-NUMBER */
  private CobolDataStorage b_WS_COUNT;	/* WS-COUNT */
  private CobolDataStorage b_WS_ABSENT;	/* WS-ABSENT */

  /* End of data storage */


  /* Fields */

  /* PROGRAM-ID : YACHT */
  private AbstractCobolField f_WS_DICE;	/* WS-DICE */
  private AbstractCobolField f_WS_CATEGORY;	/* WS-CATEGORY */
  private AbstractCobolField f_WS_RESULT;	/* WS-RESULT */

  /* End of fields */


  /* Constants */

  /* Attributes */

  private CobolFieldAttribute a_4;
  private CobolFieldAttribute a_3;
  private CobolFieldAttribute a_2;
  private CobolFieldAttribute a_1;


  public int score(String dice, String category) {
    // Initialise data
    run_module(1);

    // Pass parameters
    b_WS_DICE.memcpy(dice, dice.length());
    b_WS_CATEGORY.memcpy(category, category.length());

    // Invoke
    run_module(1);

    // Get result
    return f_WS_RESULT.getInt();
  }
}
