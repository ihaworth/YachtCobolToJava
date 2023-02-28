import jp.osscons.opensourcecobol.libcobj.common.*;
import jp.osscons.opensourcecobol.libcobj.data.*;
import jp.osscons.opensourcecobol.libcobj.exceptions.*;
import jp.osscons.opensourcecobol.libcobj.call.*;
import jp.osscons.opensourcecobol.libcobj.ui.*;
import java.util.Optional;

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
      b_WS_RESULT.fillBytes (48, 2);
      b_WS_WORKING.fillBytes('0', 15);
      b_WS_NUM_DISTINCT_DICE.setByte('0');
      b_WS_DIE_PROCESSED.setByte(' ');
      b_WS_NUMBER.setByte('0');
      b_WS_COUNT.setByte('0');
      b_WS_ABSENT.setByte('0');
      b_WS_VALUE_ABSENT.setByte(' ');
      this.initialized = true;
    }
    /* PROCEDURE DIVISION */
    try{
      CobolStopRunException.dummy();
      CobolGoBackException.dummy();
      /* Entry dispatch */
      execEntry(1);

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
  public CobolControl[] contList = {
          new CobolControl(0, CobolControl.LabelType.label) {
            public Optional<CobolControl> run() throws CobolRuntimeException {

              return Optional.of(contList[1]);
            }
          },
          /* Entry YACHT */
          new CobolControl(1, CobolControl.LabelType.label) {
            public Optional<CobolControl> run() throws CobolRuntimeException {

              return Optional.of(contList[2]);
            }
          },
          /* MAIN SECTION */
          new CobolControl(2, CobolControl.LabelType.section) {
            public Optional<CobolControl> run() throws CobolRuntimeException {

              return Optional.of(contList[3]);
            }
          },
          /* YACHT */
          new CobolControl(3, CobolControl.LabelType.label) {
            public Optional<CobolControl> run() throws CobolRuntimeException, CobolGoBackException, CobolStopRunException {
              /* YACHT.cobol:26: MOVE */
              {
                b_WS_RESULT.fillBytes (48, 2);
              }
              /* YACHT.cobol:27: UNSTRING */
              {
                CobolString.unstringInit (f_WS_DICE, 0, 0);
                CobolString.unstringInto (CobolFieldFactory.makeCobolField(1, b_WS_WORKING.getSubDataStorage(0), a_2), 0, 0);
                CobolString.unstringInto (CobolFieldFactory.makeCobolField(1, b_WS_WORKING.getSubDataStorage(1), a_2), 0, 0);
                CobolString.unstringInto (CobolFieldFactory.makeCobolField(1, b_WS_WORKING.getSubDataStorage(2), a_2), 0, 0);
                CobolString.unstringInto (CobolFieldFactory.makeCobolField(1, b_WS_WORKING.getSubDataStorage(3), a_2), 0, 0);
                CobolString.unstringInto (CobolFieldFactory.makeCobolField(1, b_WS_WORKING.getSubDataStorage(4), a_2), 0, 0);
                CobolString.unstringFinish ();
              }
              /* YACHT.cobol:29: EVALUATE */
              {
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
                  case "little straight" -> {
                    b_WS_ABSENT.setByte(54);
                    scoreStraight();
                  }
                  case "big straight" -> {
                    b_WS_ABSENT.setByte(49);
                    scoreStraight();
                  }
                  case "choice" ->
                          scoreAllDice();
                }
              }
              /* YACHT.cobol:43: EXIT */
              {
                if (!CobolModule.isQueueEmpty()) {
                  return Optional.of(contList[contList.length - 1]);
                }
              }

              return Optional.of(contList[4]);
            }
          },
          /* SCORE-YACHT */
          new CobolControl(4, CobolControl.LabelType.label) {
            public Optional<CobolControl> run() throws CobolRuntimeException, CobolStopRunException {
              return scoreYacht();
            }
          },
          /* SCORE_NUMBERS */
          new CobolControl(5, CobolControl.LabelType.label) {
            public Optional<CobolControl> run() throws CobolRuntimeException, CobolStopRunException {
              return Optional.empty();
            }
          },
          /* SCORE-FULL-HOUSE */
          new CobolControl(6, CobolControl.LabelType.label) {
            public Optional<CobolControl> run() throws CobolRuntimeException, CobolGoBackException, CobolStopRunException {
              return scoreFullHouse();
            }
          },
          /* SCORE-FOUR-OF-A-KIND */
          new CobolControl(7, CobolControl.LabelType.label) {
            public Optional<CobolControl> run() throws CobolRuntimeException, CobolStopRunException {
              return scoreFourOfAKind();
            }
          },
          /* SCORE-STRIGHT */
          new CobolControl(8, CobolControl.LabelType.label) {
            public Optional<CobolControl> run() throws CobolRuntimeException, CobolGoBackException, CobolStopRunException {
              return scoreStraight();
            }
          },
          /* SCORE-ALL-DICE */
          new CobolControl(9, CobolControl.LabelType.label) {
            public Optional<CobolControl> run() throws CobolRuntimeException, CobolStopRunException {
              return scoreAllDice();
            }
          },
          /* CHECK-VALUE-ABSENT */
          new CobolControl(10, CobolControl.LabelType.label) {
            public Optional<CobolControl> run() throws CobolRuntimeException {
              /* YACHT.cobol:94: MOVE */
              {
                b_WS_VALUE_ABSENT.setByte(89);
              }
              /* YACHT.cobol:95: PERFORM */
              b_I.set(1);
              while ((long)(b_I.intValue() - 5) <= 0L && (long)(Byte.toUnsignedInt(b_WS_VALUE_ABSENT.getByte(0)) - 78) != 0L)
              {
                {
                  /* YACHT.cobol:97: IF */
                  {
                    if (((long)b_WS_WORKING.getSubDataStorage((b_I.intValue() - 1)).memcmp (b_WS_ABSENT, 1) == 0L))
                    {
                      /* YACHT.cobol:98: MOVE */
                      {
                        b_WS_VALUE_ABSENT.setByte(78);
                      }
                    }
                  }
                }
                b_I.set(b_I.intValue() + 1);
              }

              return Optional.of(contList[11]);
            }
          },
          /* COUNT-DISTINCT-DICE */
          new CobolControl(11, CobolControl.LabelType.label) {
            public Optional<CobolControl> run() throws CobolRuntimeException, CobolStopRunException {
              return countDistinctDice();
            }
          },
          CobolControl.pure()
  };

  private Optional<CobolControl> scoreAllDice() throws CobolStopRunException {
    /* YACHT.cobol:89: PERFORM */
    b_I.set(1);
    while ((long)(b_I.intValue() - 5) <= 0L)
    {
      {
        /* YACHT.cobol:90: ADD */
        {
          f_WS_RESULT.add (CobolFieldFactory.makeCobolField(1, b_WS_WORKING.getSubDataStorage((b_I.intValue() - 1)), a_2), 4);
        }
      }
      b_I.set(b_I.intValue() + 1);
    }

    return Optional.of(contList[10]);
  }

  private Optional<CobolControl> scoreStraight() throws CobolStopRunException, CobolGoBackException {
    /* YACHT.cobol:80: PERFORM */
    /* PERFORM COUNT-DISTINCT-DICE */
    countDistinctDice();
    /* YACHT.cobol:81: IF */
    {
      if (((long)b_WS_NUM_DISTINCT_DICE.cmpNumdisp (1, 5) == 0L))
      {
        /* YACHT.cobol:82: PERFORM */
        /* PERFORM CHECK-VALUE-ABSENT */
        CobolControl.perform(contList, 10).run();
        /* YACHT.cobol:83: IF */
        {
          if (((long)(Byte.toUnsignedInt(b_WS_VALUE_ABSENT.getByte(0)) - 89) == 0L))
          {
            /* YACHT.cobol:84: MOVE */
            {
              b_WS_RESULT.setBytes ("30", 2);
            }
          }
        }
      }
    }

    return Optional.of(contList[9]);
  }

  private Optional<CobolControl> scoreFourOfAKind() throws CobolStopRunException {
    /* YACHT.cobol:70: PERFORM */
    /* PERFORM COUNT-DISTINCT-DICE */
    countDistinctDice();
    /* YACHT.cobol:71: IF */
    {
      if (((long)b_WS_NUM_DISTINCT_DICE.cmpNumdisp (1, 2) <= 0L))
      {
        /* YACHT.cobol:72: PERFORM */
        b_J.set(1);
        while ((long)(b_J.intValue() - b_WS_NUM_DISTINCT_DICE.getNumdisp(1)) <= 0L)
        {
          {
            /* YACHT.cobol:73: IF */
            {
              if (((long)b_WS_WORKING.getSubDataStorage(10).getSubDataStorage((b_J.intValue() - 1)).cmpNumdisp (1, 4) >= 0L))
              {
                /* YACHT.cobol:74: COMPUTE */
                {
                  {
                    {
                      d0.set (4);
                      d1.set (b_WS_WORKING.getSubDataStorage(5).getSubDataStorage((b_J.intValue() - 1)).getNumdisp(1));
                      d0.mul (d1);
                      d0.getField (f_WS_RESULT, 4);
                    }
                  }
                }
              }
            }
          }
          b_J.set(b_J.intValue() + 1);
        }
      }
    }

    return Optional.of(contList[8]);
  }

  private Optional<CobolControl> scoreFullHouse() throws CobolStopRunException, CobolGoBackException {
    /* YACHT.cobol:62: PERFORM */
    /* PERFORM COUNT-DISTINCT-DICE */
    countDistinctDice();
    /* YACHT.cobol:63: IF */
    {
      if (((((long)b_WS_NUM_DISTINCT_DICE.cmpNumdisp (1, 2) == 0L) && ((long)b_WS_WORKING.getSubDataStorage(10).getSubDataStorage(0).cmpNumdisp (1, 2) == 0L)) || ((long)b_WS_WORKING.getSubDataStorage(10).getSubDataStorage(0).cmpNumdisp (1, 3) == 0L)))
      {
        /* YACHT.cobol:66: PERFORM */
        /* PERFORM SCORE-ALL-DICE */
        CobolControl.perform(contList, 9).run();
      }
    }

    return Optional.of(contList[7]);
  }

  private Optional<CobolControl> scoreNumbers(int x) throws CobolStopRunException {
    b_WS_NUMBER.setByte('0' + x);
    /* YACHT.cobol:53: MOVE */
    {
      b_WS_COUNT.setByte(48);
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

    return Optional.of(contList[6]);
  }

  private Optional<CobolControl> scoreYacht() throws CobolStopRunException {
    /* YACHT.cobol:46: PERFORM */
    /* PERFORM COUNT-DISTINCT-DICE */
    countDistinctDice();
    /* YACHT.cobol:47: IF */
    {
      if (((long)b_WS_NUM_DISTINCT_DICE.cmpNumdisp (1, 1) == 0L))
      {
        /* YACHT.cobol:48: MOVE */
        {
          b_WS_RESULT.setBytes ("50", 2);
        }
      }
    }

    return Optional.of(contList[5]);
  }

  private Optional<CobolControl> countDistinctDice() throws CobolStopRunException {
    /* YACHT.cobol:103: MOVE */
    {
      b_WS_NUM_DISTINCT_DICE.setByte(48);
    }
    /* YACHT.cobol:104: PERFORM */
    b_J.set(1);
    while ((long) (b_J.intValue() - 5) <= 0L)
    {
      {
        /* YACHT.cobol:105: MOVE */
        {
          b_WS_WORKING.getSubDataStorage(5).getSubDataStorage((b_J.intValue() - 1)).setByte(48);
        }
        /* YACHT.cobol:106: MOVE */
        {
          b_WS_WORKING.getSubDataStorage(10).getSubDataStorage((b_J.intValue() - 1)).setByte(48);
        }
      }
      b_J.set(b_J.intValue() + 1);
    }
    /* YACHT.cobol:109: PERFORM */
    b_I.set(1);
    while ((long)(b_I.intValue() - 5) <= 0L)
    {
      {
        /* YACHT.cobol:110: MOVE */
        {
          b_WS_DIE_PROCESSED.setByte(78);
        }
        /* YACHT.cobol:111: PERFORM */
        b_J.set(1);
        while ((long)(Byte.toUnsignedInt(b_WS_DIE_PROCESSED.getByte(0)) - 89) != 0L)
        {
          {
            /* YACHT.cobol:112: IF */
            {
              if (((long)(b_J.intValue() - b_WS_NUM_DISTINCT_DICE.getNumdisp(1)) >  0L))
              {
                /* YACHT.cobol:113: MOVE */
                {
                  b_WS_WORKING.getSubDataStorage(5).getSubDataStorage((b_J.intValue() - 1)).setByte(b_WS_WORKING.getSubDataStorage((b_I.intValue() - 1)).getByte(0));
                }
                /* YACHT.cobol:114: ADD */
                {
                  CobolFieldFactory.makeCobolField(1, b_WS_WORKING.getSubDataStorage(10).getSubDataStorage((b_J.intValue() - 1)), a_2).add (c_12, 4);
                }
                /* YACHT.cobol:115: ADD */
                {
                  f_WS_NUM_DISTINCT_DICE.add (c_12, 4);
                }
                /* YACHT.cobol:116: MOVE */
                {
                  b_WS_DIE_PROCESSED.setByte(89);
                }
              }
              else
              {
                /* YACHT.cobol:118: IF */
                {
                  if (((long)b_WS_WORKING.getSubDataStorage((b_I.intValue() - 1)).memcmp (b_WS_WORKING.getSubDataStorage(5).getSubDataStorage((b_J.intValue() - 1)), 1) == 0L))
                  {
                    /* YACHT.cobol:119: ADD */
                    {
                      CobolFieldFactory.makeCobolField(1, b_WS_WORKING.getSubDataStorage(10).getSubDataStorage((b_J.intValue() - 1)), a_2).add (c_12, 4);
                    }
                    /* YACHT.cobol:120: MOVE */
                    {
                      b_WS_DIE_PROCESSED.setByte(89);
                    }
                  }
                }
              }
            }
          }
          b_J.set(b_J.intValue() + 1);
        }
      }
      b_I.set(b_I.intValue() + 1);
    }
    return Optional.of(CobolControl.pure());
  }

  public void execEntry(int start) throws CobolRuntimeException, CobolGoBackException, CobolStopRunException {
    Optional<CobolControl> nextLabel = Optional.of(contList[start]);
    while(nextLabel.isPresent()) {
      CobolControl section = nextLabel.get();
      nextLabel = section.run();
    }
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
      b_WS_NUM_DISTINCT_DICE = new CobolDataStorage(1);	/* WS-NUM-DISTINCT-DICE */
      b_WS_DIE_PROCESSED = new CobolDataStorage(1);	/* WS-DIE-PROCESSED */
      b_WS_NUMBER = new CobolDataStorage(1);	/* WS-NUMBER */
      b_WS_COUNT = new CobolDataStorage(1);	/* WS-COUNT */
      b_WS_ABSENT = new CobolDataStorage(1);	/* WS-ABSENT */
      b_WS_VALUE_ABSENT = new CobolDataStorage(1);	/* WS-VALUE-ABSENT */

      /* End of data storage */


      initAttr();

      /* Fields */

      /* PROGRAM-ID : YACHT */
      f_WS_DICE	= CobolFieldFactory.makeCobolField(5, b_WS_DICE, a_1);	/* WS-DICE */
      f_WS_CATEGORY	= CobolFieldFactory.makeCobolField(15, b_WS_CATEGORY, a_3);	/* WS-CATEGORY */
      f_WS_RESULT	= CobolFieldFactory.makeCobolField(2, b_WS_RESULT, a_4);	/* WS-RESULT */
      f_WS_NUM_DISTINCT_DICE	= CobolFieldFactory.makeCobolField(1, b_WS_NUM_DISTINCT_DICE, a_2);	/* WS-NUM-DISTINCT-DICE */
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
  private CobolDataStorage b_WS_NUM_DISTINCT_DICE;	/* WS-NUM-DISTINCT-DICE */
  private CobolDataStorage b_WS_DIE_PROCESSED;	/* WS-DIE-PROCESSED */
  private CobolDataStorage b_WS_NUMBER;	/* WS-NUMBER */
  private CobolDataStorage b_WS_COUNT;	/* WS-COUNT */
  private CobolDataStorage b_WS_ABSENT;	/* WS-ABSENT */
  private CobolDataStorage b_WS_VALUE_ABSENT;	/* WS-VALUE-ABSENT */

  /* End of data storage */


  /* Fields */

  /* PROGRAM-ID : YACHT */
  private AbstractCobolField f_WS_DICE;	/* WS-DICE */
  private AbstractCobolField f_WS_CATEGORY;	/* WS-CATEGORY */
  private AbstractCobolField f_WS_RESULT;	/* WS-RESULT */
  private AbstractCobolField f_WS_NUM_DISTINCT_DICE;	/* WS-NUM-DISTINCT-DICE */
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
