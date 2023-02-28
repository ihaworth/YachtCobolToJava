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

    /* YACHT.cobol:29: EVALUATE */
    switch (YACHT.this.f_WS_CATEGORY.getString().trim()) {
      case "yacht"           -> scoreYacht();
      case "ones"            -> scoreNumbers(1);
      case "twos"            -> scoreNumbers(2);
      case "threes"          -> scoreNumbers(3);
      case "fours"           -> scoreNumbers(4);
      case "fives"           -> scoreNumbers(5);
      case "sixes"           -> scoreNumbers(6);
      case "full house"      -> scoreFullHouse();
      case "four of a kind"  -> scoreFourOfAKind();
      case "little straight" -> scoreStraight(6);
      case "big straight"    -> scoreStraight(1);
      case "choice"          -> scoreAllDice();
    }
  }

  private void scoreAllDice() throws CobolStopRunException {
    for (int i = 0; i < 5; i++) {
      f_WS_RESULT.add(CobolFieldFactory.makeCobolField(1, b_WS_WORKING.getSubDataStorage(i), a_2), 4);
    }
  }

  private void scoreStraight(int absentRoll) {
    /* YACHT.cobol:80: PERFORM */
    /* PERFORM COUNT-DISTINCT-DICE */
    Map<Integer, Integer> diceCounts = countDistinctDice();
    /* YACHT.cobol:81: IF */
    {
      if (diceCounts.size() == 5)
      {
        /* YACHT.cobol:82: PERFORM */
        /* PERFORM CHECK-VALUE-ABSENT */
        boolean valueIsAbsent = checkValueAbsent(absentRoll);
        /* YACHT.cobol:83: IF */
        {
          if (valueIsAbsent)
          {
            /* YACHT.cobol:84: MOVE */
            {
              b_WS_RESULT.setBytes ("30", 2);
            }
          }
        }
      }
    }
  }

  private boolean checkValueAbsent(int absentRoll) {
    b_WS_ABSENT.setByte('0' + absentRoll);
    for (int i = 0; i < 5; i++)
    {
      if (((long)b_WS_WORKING.getSubDataStorage(i).memcmp (b_WS_ABSENT, 1) == 0L)) {
        return false;
      }
    }
    return true;
  }

  private void scoreFourOfAKind() throws CobolStopRunException {
    Map<Integer, Integer> diceCounts = countDistinctDice();
    if (diceCounts.size() <= 2)
    {
      for (int dieValue: diceCounts.keySet())
      {
        int count = diceCounts.get(dieValue);
        if (count >= 4)
        {
          d0.set (4);
          d1.set (dieValue);
          d0.mul (d1);
          d0.getField (f_WS_RESULT, 4);
        }
      }
    }
  }

  private void scoreFullHouse() throws CobolStopRunException {
    /* YACHT.cobol:62: PERFORM */
    /* PERFORM COUNT-DISTINCT-DICE */
    Map<Integer, Integer> diceCounts = countDistinctDice();
    /* YACHT.cobol:63: IF */
    {
      Integer numOfFirstDie = diceCounts.values().stream().findFirst().get();
      if (diceCounts.size() == 2 &&
              numOfFirstDie == 2 ||
              numOfFirstDie == 3)
      {
        /* YACHT.cobol:66: PERFORM */
        /* PERFORM SCORE-ALL-DICE */
        scoreAllDice();
      }
    }
  }

  private void scoreNumbers(int number) throws CobolStopRunException {
    b_WS_NUMBER.setByte('0' + number);
    /* YACHT.cobol:53: MOVE */
    {
      b_WS_COUNT.setByte('0');
    }
    /* YACHT.cobol:54: PERFORM */
    b_I.set(1);
    while ((long)(b_I.intValue() - 5) <= 0L)
    {
      {
        /* YACHT.cobol:55: IF */
        {
          if (((long)b_WS_WORKING.getSubDataStorage((b_I.intValue() - 1)).memcmp (b_WS_NUMBER, 1) == 0L))
          {
            /* YACHT.cobol:56: ADD */
            {
              f_WS_COUNT.add (c_12, 4);
            }
          }
        }
      }
      b_I.set(b_I.intValue() + 1);
    }
    /* YACHT.cobol:59: COMPUTE */
    {
      {
        {
          d0.set (b_WS_NUMBER.getNumdisp(1));
          d1.set (b_WS_COUNT.getNumdisp(1));
          d0.mul (d1);
          d0.getField (f_WS_RESULT, 4);
        }
      }
    }

  }

  private void scoreYacht() {
    /* YACHT.cobol:46: PERFORM */
    /* PERFORM COUNT-DISTINCT-DICE */
    Map<Integer, Integer> diceCounts = countDistinctDice();
    /* YACHT.cobol:47: IF */
    {
      if (diceCounts.size() == 1)
      {
        /* YACHT.cobol:48: MOVE */
        {
          b_WS_RESULT.setBytes ("50", 2);
        }
      }
    }
  }

  private Map<Integer, Integer> countDistinctDice() {
    List<Integer> rolledDice = new ArrayList<>();
    for (int i = 1; i <= 5; i++)
    {
      CobolDataStorage subDataStorage = b_WS_WORKING.getSubDataStorage(i - 1);
      byte aByte = subDataStorage.getByte(0);
      int dieRoll = aByte - '0';
      rolledDice.add(dieRoll);
    }

    return rolledDice.stream().collect(groupingBy(r -> r))
            .entrySet().stream().collect(toMap(e -> e.getKey(),
                                               e -> e.getValue().size()));
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
      f_WS_COUNT	= CobolFieldFactory.makeCobolField(1, b_WS_COUNT, a_2);	/* WS-COUNT */

      /* End of fields */


      /* Constants */

      c_12	= CobolFieldFactory.makeCobolField(1, "1", a_2);

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
  private AbstractCobolField f_WS_COUNT;	/* WS-COUNT */

  /* End of fields */


  /* Constants */

  private AbstractCobolField c_12;

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
