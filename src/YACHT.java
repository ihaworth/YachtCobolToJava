import jp.osscons.opensourcecobol.libcobj.common.*;
import jp.osscons.opensourcecobol.libcobj.data.*;
import jp.osscons.opensourcecobol.libcobj.exceptions.*;
import jp.osscons.opensourcecobol.libcobj.call.*;
import jp.osscons.opensourcecobol.libcobj.ui.*;
import java.util.Optional;

public class YACHT implements CobolRunnable {

  private boolean initialized = false;
  private CobolModule cobolCurrentModule;
  private int frameIndex;
  private CobolModule module;
  private CobolFrame frame;
  private static boolean cobolInitialized = false;
  private CobolCallParams cobolSaveCallParams = null;
  private CobolCallParams cobolCallParams = null;
  private boolean cobolErrorOnExitFlag;
  private int entry;

  private CobolRunnable cob_unifunc;


  @Override
  public int run(CobolDataStorage... argStorages) {
    return YACHT_(0, argStorages);
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

  public int YACHT_ (int entry, CobolDataStorage ...argStorages) {
    this.entry = entry;
    return this.run_module(entry);
  }

  int run_module (int entry) {
    this.module = new CobolModule(null, null, null, null, 0, '.', '$', ',', 1, 1, 1, 0, null );

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

      b_RETURN_CODE.set((int)0);
      b_I.set((int)1);
      b_J.set((int)1);
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
      public Optional<CobolControl> run() throws CobolRuntimeException, CobolGoBackException, CobolStopRunException {

        return Optional.of(contList[1]);
      }
    },
    /* Entry YACHT */
    new CobolControl(1, CobolControl.LabelType.label) {
      public Optional<CobolControl> run() throws CobolRuntimeException, CobolGoBackException, CobolStopRunException {

        return Optional.of(contList[2]);
      }
    },
    /* MAIN SECTION */
    new CobolControl(2, CobolControl.LabelType.section) {
      public Optional<CobolControl> run() throws CobolRuntimeException, CobolGoBackException, CobolStopRunException {

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
          if (((long)f_WS_CATEGORY.compareTo (c_1) == 0L))
            {
              /* YACHT.cobol:30: PERFORM */
              /* PERFORM SCORE-YACHT */
              CobolControl.perform(contList, 4).run();
            }
          else
            if (((long)f_WS_CATEGORY.compareTo (c_2) == 0L))
              {
                /* YACHT.cobol:31: MOVE */
                {
                  b_WS_NUMBER.setByte(49);
                }
                /* YACHT.cobol:31: PERFORM */
                /* PERFORM SCORE_NUMBERS */
                CobolControl.perform(contList, 5).run();
              }
            else
              if (((long)f_WS_CATEGORY.compareTo (c_3) == 0L))
                {
                  /* YACHT.cobol:32: MOVE */
                  {
                    b_WS_NUMBER.setByte(50);
                  }
                  /* YACHT.cobol:32: PERFORM */
                  /* PERFORM SCORE_NUMBERS */
                  CobolControl.perform(contList, 5).run();
                }
              else
                if (((long)f_WS_CATEGORY.compareTo (c_4) == 0L))
                  {
                    /* YACHT.cobol:33: MOVE */
                    {
                      b_WS_NUMBER.setByte(51);
                    }
                    /* YACHT.cobol:33: PERFORM */
                    /* PERFORM SCORE_NUMBERS */
                    CobolControl.perform(contList, 5).run();
                  }
                else
                  if (((long)f_WS_CATEGORY.compareTo (c_5) == 0L))
                    {
                      /* YACHT.cobol:34: MOVE */
                      {
                        b_WS_NUMBER.setByte(52);
                      }
                      /* YACHT.cobol:34: PERFORM */
                      /* PERFORM SCORE_NUMBERS */
                      CobolControl.perform(contList, 5).run();
                    }
                  else
                    if (((long)f_WS_CATEGORY.compareTo (c_6) == 0L))
                      {
                        /* YACHT.cobol:35: MOVE */
                        {
                          b_WS_NUMBER.setByte(53);
                        }
                        /* YACHT.cobol:35: PERFORM */
                        /* PERFORM SCORE_NUMBERS */
                        CobolControl.perform(contList, 5).run();
                      }
                    else
                      if (((long)f_WS_CATEGORY.compareTo (c_7) == 0L))
                        {
                          /* YACHT.cobol:36: MOVE */
                          {
                            b_WS_NUMBER.setByte(54);
                          }
                          /* YACHT.cobol:36: PERFORM */
                          /* PERFORM SCORE_NUMBERS */
                          CobolControl.perform(contList, 5).run();
                        }
                      else
                        if (((long)f_WS_CATEGORY.compareTo (c_8) == 0L))
                          {
                            /* YACHT.cobol:37: PERFORM */
                            /* PERFORM SCORE-FULL-HOUSE */
                            CobolControl.perform(contList, 6).run();
                          }
                        else
                          if (((long)f_WS_CATEGORY.compareTo (c_9) == 0L))
                            {
                              /* YACHT.cobol:38: PERFORM */
                              /* PERFORM SCORE-FOUR-OF-A-KIND */
                              CobolControl.perform(contList, 7).run();
                            }
                          else
                            if (((long)b_WS_CATEGORY.memcmp ("little straight", 15) == 0L))
                              {
                                /* YACHT.cobol:39: MOVE */
                                {
                                  b_WS_ABSENT.setByte(54);
                                }
                                /* YACHT.cobol:39: PERFORM */
                                /* PERFORM SCORE-STRIGHT */
                                CobolControl.perform(contList, 8).run();
                              }
                            else
                              if (((long)f_WS_CATEGORY.compareTo (c_10) == 0L))
                                {
                                  /* YACHT.cobol:40: MOVE */
                                  {
                                    b_WS_ABSENT.setByte(49);
                                  }
                                  /* YACHT.cobol:40: PERFORM */
                                  /* PERFORM SCORE-STRIGHT */
                                  CobolControl.perform(contList, 8).run();
                                }
                              else
                                if (((long)f_WS_CATEGORY.compareTo (c_11) == 0L))
                                  {
                                    /* YACHT.cobol:41: PERFORM */
                                    /* PERFORM SCORE-ALL-DICE */
                                    CobolControl.perform(contList, 9).run();
                                  }
        }

        return Optional.empty();
      }
    },
    /* SCORE-YACHT */
    new CobolControl(4, CobolControl.LabelType.label) {
      public Optional<CobolControl> run() throws CobolRuntimeException, CobolGoBackException, CobolStopRunException {
        /* YACHT.cobol:45: PERFORM */
        /* PERFORM COUNT-DISTINCT-DICE */
        CobolControl.perform(contList, 11).run();
        /* YACHT.cobol:46: IF */
        {
          if (((long)b_WS_NUM_DISTINCT_DICE.cmpNumdisp (1, 1) == 0L))
            {
              /* YACHT.cobol:47: MOVE */
              {
                b_WS_RESULT.setBytes ("50", 2);
              }
            }
        }

        return Optional.of(contList[5]);
      }
    },
    /* SCORE_NUMBERS */
    new CobolControl(5, CobolControl.LabelType.label) {
      public Optional<CobolControl> run() throws CobolRuntimeException, CobolGoBackException, CobolStopRunException {
        /* YACHT.cobol:52: MOVE */
        {
          b_WS_COUNT.setByte(48);
        }
        /* YACHT.cobol:53: PERFORM */
        b_I.set((int)1);
        for (;;)
          {
            if (((long)(b_I.intValue() - 5) >  0L))
              break;
            {
              /* YACHT.cobol:54: IF */
              {
                if (((long)b_WS_WORKING.getSubDataStorage((b_I.intValue() - 1)).memcmp (b_WS_NUMBER, 1) == 0L))
                  {
                    /* YACHT.cobol:55: ADD */
                    {
                      f_WS_COUNT.add (c_12, 4);
                    }
                  }
              }
            }
            b_I.set((int)(b_I.intValue() + 1));
          }
        /* YACHT.cobol:58: COMPUTE */
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
    },
    /* SCORE-FULL-HOUSE */
    new CobolControl(6, CobolControl.LabelType.label) {
      public Optional<CobolControl> run() throws CobolRuntimeException, CobolGoBackException, CobolStopRunException {
        /* YACHT.cobol:61: PERFORM */
        /* PERFORM COUNT-DISTINCT-DICE */
        CobolControl.perform(contList, 11).run();
        /* YACHT.cobol:62: IF */
        {
          if (((((long)b_WS_NUM_DISTINCT_DICE.cmpNumdisp (1, 2) == 0L) && ((long)b_WS_WORKING.getSubDataStorage(10).getSubDataStorage(0).cmpNumdisp (1, 2) == 0L)) || ((long)b_WS_WORKING.getSubDataStorage(10).getSubDataStorage(0).cmpNumdisp (1, 3) == 0L)))
            {
              /* YACHT.cobol:65: PERFORM */
              /* PERFORM SCORE-ALL-DICE */
              CobolControl.perform(contList, 9).run();
            }
        }

        return Optional.of(contList[7]);
      }
    },
    /* SCORE-FOUR-OF-A-KIND */
    new CobolControl(7, CobolControl.LabelType.label) {
      public Optional<CobolControl> run() throws CobolRuntimeException, CobolGoBackException, CobolStopRunException {
        /* YACHT.cobol:69: PERFORM */
        /* PERFORM COUNT-DISTINCT-DICE */
        CobolControl.perform(contList, 11).run();
        /* YACHT.cobol:70: IF */
        {
          if (((long)b_WS_NUM_DISTINCT_DICE.cmpNumdisp (1, 2) <= 0L))
            {
              /* YACHT.cobol:71: PERFORM */
              b_J.set((int)1);
              for (;;)
                {
                  if (((long)(b_J.intValue() - b_WS_NUM_DISTINCT_DICE.getNumdisp(1)) >  0L))
                    break;
                  {
                    /* YACHT.cobol:72: IF */
                    {
                      if (((long)b_WS_WORKING.getSubDataStorage(10).getSubDataStorage((b_J.intValue() - 1)).cmpNumdisp (1, 4) >= 0L))
                        {
                          /* YACHT.cobol:73: COMPUTE */
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
                  b_J.set((int)(b_J.intValue() + 1));
                }
            }
        }

        return Optional.of(contList[8]);
      }
    },
    /* SCORE-STRIGHT */
    new CobolControl(8, CobolControl.LabelType.label) {
      public Optional<CobolControl> run() throws CobolRuntimeException, CobolGoBackException, CobolStopRunException {
        /* YACHT.cobol:79: PERFORM */
        /* PERFORM COUNT-DISTINCT-DICE */
        CobolControl.perform(contList, 11).run();
        /* YACHT.cobol:80: IF */
        {
          if (((long)b_WS_NUM_DISTINCT_DICE.cmpNumdisp (1, 5) == 0L))
            {
              /* YACHT.cobol:81: PERFORM */
              /* PERFORM CHECK-VALUE-ABSENT */
              CobolControl.perform(contList, 10).run();
              /* YACHT.cobol:82: IF */
              {
                if (((long)(Byte.toUnsignedInt(b_WS_VALUE_ABSENT.getByte(0)) - (int)89) == 0L))
                  {
                    /* YACHT.cobol:83: MOVE */
                    {
                      b_WS_RESULT.setBytes ("30", 2);
                    }
                  }
              }
            }
        }

        return Optional.of(contList[9]);
      }
    },
    /* SCORE-ALL-DICE */
    new CobolControl(9, CobolControl.LabelType.label) {
      public Optional<CobolControl> run() throws CobolRuntimeException, CobolGoBackException, CobolStopRunException {
        /* YACHT.cobol:88: PERFORM */
        b_I.set((int)1);
        for (;;)
          {
            if (((long)(b_I.intValue() - 5) >  0L))
              break;
            {
              /* YACHT.cobol:89: ADD */
              {
                f_WS_RESULT.add (CobolFieldFactory.makeCobolField(1, b_WS_WORKING.getSubDataStorage((b_I.intValue() - 1)), a_2), 4);
              }
            }
            b_I.set((int)(b_I.intValue() + 1));
          }

        return Optional.of(contList[10]);
      }
    },
    /* CHECK-VALUE-ABSENT */
    new CobolControl(10, CobolControl.LabelType.label) {
      public Optional<CobolControl> run() throws CobolRuntimeException, CobolGoBackException, CobolStopRunException {
        /* YACHT.cobol:93: MOVE */
        {
          b_WS_VALUE_ABSENT.setByte(89);
        }
        /* YACHT.cobol:94: PERFORM */
        b_I.set((int)1);
        for (;;)
          {
            if ((((long)(b_I.intValue() - 5) >  0L) || ((long)(Byte.toUnsignedInt(b_WS_VALUE_ABSENT.getByte(0)) - (int)78) == 0L)))
              break;
            {
              /* YACHT.cobol:96: IF */
              {
                if (((long)b_WS_WORKING.getSubDataStorage((b_I.intValue() - 1)).memcmp (b_WS_ABSENT, 1) == 0L))
                  {
                    /* YACHT.cobol:97: MOVE */
                    {
                      b_WS_VALUE_ABSENT.setByte(78);
                    }
                  }
              }
            }
            b_I.set((int)(b_I.intValue() + 1));
          }

        return Optional.of(contList[11]);
      }
    },
    /* COUNT-DISTINCT-DICE */
    new CobolControl(11, CobolControl.LabelType.label) {
      public Optional<CobolControl> run() throws CobolRuntimeException, CobolGoBackException, CobolStopRunException {
        /* YACHT.cobol:102: MOVE */
        {
          b_WS_NUM_DISTINCT_DICE.setByte(48);
        }
        /* YACHT.cobol:103: PERFORM */
        b_J.set((int)1);
        for (;;)
          {
            if (((long)(b_J.intValue() - 5) >  0L))
              break;
            {
              /* YACHT.cobol:104: MOVE */
              {
                b_WS_WORKING.getSubDataStorage(5).getSubDataStorage((b_J.intValue() - 1)).setByte(48);
              }
              /* YACHT.cobol:105: MOVE */
              {
                b_WS_WORKING.getSubDataStorage(10).getSubDataStorage((b_J.intValue() - 1)).setByte(48);
              }
            }
            b_J.set((int)(b_J.intValue() + 1));
          }
        /* YACHT.cobol:108: PERFORM */
        b_I.set((int)1);
        for (;;)
          {
            if (((long)(b_I.intValue() - 5) >  0L))
              break;
            {
              /* YACHT.cobol:109: MOVE */
              {
                b_WS_DIE_PROCESSED.setByte(78);
              }
              /* YACHT.cobol:110: PERFORM */
              b_J.set((int)1);
              for (;;)
                {
                  if (((long)(Byte.toUnsignedInt(b_WS_DIE_PROCESSED.getByte(0)) - (int)89) == 0L))
                    break;
                  {
                    /* YACHT.cobol:111: IF */
                    {
                      if (((long)(b_J.intValue() - b_WS_NUM_DISTINCT_DICE.getNumdisp(1)) >  0L))
                        {
                          /* YACHT.cobol:112: MOVE */
                          {
                            b_WS_WORKING.getSubDataStorage(5).getSubDataStorage((b_J.intValue() - 1)).setByte(b_WS_WORKING.getSubDataStorage((b_I.intValue() - 1)).getByte(0));
                          }
                          /* YACHT.cobol:113: ADD */
                          {
                            CobolFieldFactory.makeCobolField(1, b_WS_WORKING.getSubDataStorage(10).getSubDataStorage((b_J.intValue() - 1)), a_2).add (c_12, 4);
                          }
                          /* YACHT.cobol:114: ADD */
                          {
                            f_WS_NUM_DISTINCT_DICE.add (c_12, 4);
                          }
                          /* YACHT.cobol:115: MOVE */
                          {
                            b_WS_DIE_PROCESSED.setByte(89);
                          }
                        }
                      else
                        {
                          /* YACHT.cobol:117: IF */
                          {
                            if (((long)b_WS_WORKING.getSubDataStorage((b_I.intValue() - 1)).memcmp (b_WS_WORKING.getSubDataStorage(5).getSubDataStorage((b_J.intValue() - 1)), 1) == 0L))
                              {
                                /* YACHT.cobol:118: ADD */
                                {
                                  CobolFieldFactory.makeCobolField(1, b_WS_WORKING.getSubDataStorage(10).getSubDataStorage((b_J.intValue() - 1)), a_2).add (c_12, 4);
                                }
                                /* YACHT.cobol:119: MOVE */
                                {
                                  b_WS_DIE_PROCESSED.setByte(89);
                                }
                              }
                          }
                        }
                    }
                  }
                  b_J.set((int)(b_J.intValue() + 1));
                }
            }
            b_I.set((int)(b_I.intValue() + 1));
          }
        return Optional.of(CobolControl.pure());
      }
    },
    CobolControl.pure()
  };
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

      cob_unifunc = null;

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

      c_1	= CobolFieldFactory.makeCobolField(5, "yacht", a_3);
      c_2	= CobolFieldFactory.makeCobolField(4, "ones", a_3);
      c_3	= CobolFieldFactory.makeCobolField(4, "twos", a_3);
      c_4	= CobolFieldFactory.makeCobolField(6, "threes", a_3);
      c_5	= CobolFieldFactory.makeCobolField(5, "fours", a_3);
      c_6	= CobolFieldFactory.makeCobolField(5, "fives", a_3);
      c_7	= CobolFieldFactory.makeCobolField(5, "sixes", a_3);
      c_8	= CobolFieldFactory.makeCobolField(10, "full house", a_3);
      c_9	= CobolFieldFactory.makeCobolField(14, "four of a kind", a_3);
      c_10	= CobolFieldFactory.makeCobolField(12, "big straight", a_3);
      c_11	= CobolFieldFactory.makeCobolField(6, "choice", a_3);
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


  private static AbstractCobolField f_native;

  /* Constants */

  private AbstractCobolField c_12;
  private AbstractCobolField c_11;
  private AbstractCobolField c_10;
  private AbstractCobolField c_9;
  private AbstractCobolField c_8;
  private AbstractCobolField c_7;
  private AbstractCobolField c_6;
  private AbstractCobolField c_5;
  private AbstractCobolField c_4;
  private AbstractCobolField c_3;
  private AbstractCobolField c_2;
  private AbstractCobolField c_1;

  /* Attributes */

  private CobolFieldAttribute a_4;
  private CobolFieldAttribute a_3;
  private CobolFieldAttribute a_2;
  private CobolFieldAttribute a_1;




  private void cobolPushCallStackList(String programId)
  {
  }

  private void cobolFatalError(int errorCode)
  {
  }

  private void cobolCheckVersion(String sourceFile, int packageVersion, int patchVersion)
  {
  }

  private void cobolSetCancel(String programId, Object a, Object b)
  {
  }

  private void cobolPopCallStackList()
  {
  }

  private static CobolDataStorage makeCobolDataStorage(byte ...bytes) {
    return new CobolDataStorage(bytes);
  }

  public int score(String dice, String category) {
    run_module(1);
    b_WS_DICE.memcpy(dice, dice.length());
    b_WS_CATEGORY.memcpy(category, category.length());

    run_module(1);

    return f_WS_RESULT.getInt();
  }
}
