       IDENTIFICATION DIVISION.
       PROGRAM-ID. YACHT.
       DATA DIVISION.
       WORKING-STORAGE SECTION.
      *Input
       01 WS-DICE     PIC 9(5).
       01 WS-CATEGORY PIC X(15).
      *Output
       01 WS-RESULT   PIC 99 VALUE 0.
      *Working
       01 WS-DIE                 PIC 9(1) OCCURS 5 TIMES INDEXED BY I.
      *Working COUNT-DISTINCT-DICE
       01 WS-DISTINCT-DICE       PIC 9(1) OCCURS 5 TIMES INDEXED BY J.
       01 WS-DISTINCT-DICE-COUNT PIC 9(1) OCCURS 5 TIMES INDEXED BY WS-NUM-DISTINCT-DICE.
       01 WS-DIE-PROCESSED       PIC X(1).
      *Working SCORE_NUMBERS
       01 WS-NUMBER PIC 9(1).
       01 WS-COUNT  PIC 9(1).
      *Working CHECK-VALUE-ABSENT 
       01 WS-ABSENT       PIC 9(1).
       01 WS-VALUE-ABSENT PIC X(1).
       PROCEDURE DIVISION.
       YACHT.
       MOVE 0 TO WS-RESULT
       UNSTRING WS-DICE
          INTO WS-DIE(1), WS-DIE(2), WS-DIE(3), WS-DIE(4), WS-DIE(5)
       EVALUATE WS-CATEGORY
       WHEN "yacht"           PERFORM SCORE-YACHT
       WHEN "ones"            MOVE 1 TO WS-NUMBER PERFORM SCORE_NUMBERS
       WHEN "twos"            MOVE 2 TO WS-NUMBER PERFORM SCORE_NUMBERS
       WHEN "threes"          MOVE 3 TO WS-NUMBER PERFORM SCORE_NUMBERS
       WHEN "fours"           MOVE 4 TO WS-NUMBER PERFORM SCORE_NUMBERS
       WHEN "fives"           MOVE 5 TO WS-NUMBER PERFORM SCORE_NUMBERS
       WHEN "sixes"           MOVE 6 TO WS-NUMBER PERFORM SCORE_NUMBERS
       WHEN "full house"      PERFORM SCORE-FULL-HOUSE
       WHEN "four of a kind"  PERFORM SCORE-FOUR-OF-A-KIND
       WHEN "little straight" MOVE 6 TO WS-ABSENT PERFORM SCORE-STRIGHT
       WHEN "big straight"    MOVE 1 TO WS-ABSENT PERFORM SCORE-STRIGHT
       WHEN "choice"          PERFORM SCORE-ALL-DICE
       END-EVALUATE
       .
       SCORE-YACHT.
       PERFORM COUNT-DISTINCT-DICE
       IF WS-NUM-DISTINCT-DICE = 1
          MOVE 50 TO WS-RESULT
       END-IF
       .
    
       SCORE_NUMBERS.
       MOVE 0 TO WS-COUNT
       PERFORM VARYING I FROM 1 BY 1 UNTIL I > 5
          IF WS-DIE(I) = WS-NUMBER
             ADD 1 TO WS-COUNT
          END-IF
       END-PERFORM
       COMPUTE WS-RESULT = WS-NUMBER * WS-COUNT
       .
       SCORE-FULL-HOUSE.
       PERFORM COUNT-DISTINCT-DICE
       IF WS-NUM-DISTINCT-DICE = 2 AND
          WS-DISTINCT-DICE-COUNT(1) = 2 OR
          WS-DISTINCT-DICE-COUNT(1) = 3
       PERFORM SCORE-ALL-DICE
       END-IF
       .
       SCORE-FOUR-OF-A-KIND.
       PERFORM COUNT-DISTINCT-DICE
       IF WS-NUM-DISTINCT-DICE <= 2
          PERFORM VARYING J FROM 1 BY 1 UNTIL J > WS-NUM-DISTINCT-DICE
             IF WS-DISTINCT-DICE-COUNT(J) >= 4
                COMPUTE WS-RESULT = 4 * WS-DISTINCT-DICE(J)
             END-IF
          END-PERFORM
       END-IF
       .
       SCORE-STRIGHT.
       PERFORM COUNT-DISTINCT-DICE
       IF WS-NUM-DISTINCT-DICE = 5
          PERFORM CHECK-VALUE-ABSENT
          IF WS-VALUE-ABSENT = 'Y'
             MOVE 30 TO WS-RESULT
          END-IF
       END-IF
       .
       SCORE-ALL-DICE.
       PERFORM VARYING I FROM 1 BY 1 UNTIL I > 5
          ADD WS-DIE(I) TO WS-RESULT
       END-PERFORM
       .
       CHECK-VALUE-ABSENT.
       MOVE 'Y' TO WS-VALUE-ABSENT
       PERFORM VARYING I FROM 1 BY 1 UNTIL I > 5 OR WS-VALUE-ABSENT = 'N'
          IF WS-DIE(I) = WS-ABSENT
             MOVE 'N' TO WS-VALUE-ABSENT
          END-IF
       END-PERFORM
       .
       COUNT-DISTINCT-DICE.
       MOVE 0 TO WS-NUM-DISTINCT-DICE
       PERFORM VARYING J FROM 1 BY 1 UNTIL J > 5
          MOVE 0 TO WS-DISTINCT-DICE(J)
          MOVE 0 TO WS-DISTINCT-DICE-COUNT(J)
       END-PERFORM
    
       PERFORM VARYING I FROM 1 BY 1 UNTIL I > 5
          MOVE 'N' TO WS-DIE-PROCESSED
          PERFORM VARYING J FROM 1 BY 1 UNTIL WS-DIE-PROCESSED = 'Y'
             IF J > WS-NUM-DISTINCT-DICE
                MOVE WS-DIE(I) TO WS-DISTINCT-DICE(J)
                ADD 1 TO WS-DISTINCT-DICE-COUNT(J)
                ADD 1 TO WS-NUM-DISTINCT-DICE
                MOVE 'Y' TO WS-DIE-PROCESSED
             ELSE
                IF WS-DIE(I) = WS-DISTINCT-DICE(J)
                   ADD 1 TO WS-DISTINCT-DICE-COUNT(J)
                   MOVE 'Y' TO WS-DIE-PROCESSED
                END-IF
             END-IF
          END-PERFORM
       END-PERFORM
       .
