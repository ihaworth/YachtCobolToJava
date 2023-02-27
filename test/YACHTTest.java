import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class YACHTTest {
    @Test
    void yacht() {
        YACHT yacht = new YACHT();
        int result = yacht.calculate("55555", "yacht");
        assertEquals(50, result);
    }

    @Test
    void Not_Yacht() {
        YACHT yacht = new YACHT();
        int result = yacht.calculate("13325", "yacht");
        assertEquals(0, result);
    }

    @Test
    void Ones() {
        YACHT yacht = new YACHT();
        int result = yacht.calculate("11135", "ones");
        assertEquals(3, result);
    }

    @Test
    void Ones_out_of_order() {
        YACHT yacht = new YACHT();
        int result = yacht.calculate("31151", "ones");
        assertEquals(3, result);
    }

    @Test
    void No_ones() {
        YACHT yacht = new YACHT();
        int result = yacht.calculate("43655", "ones");
        assertEquals(0, result);
    }

    @Test
    void Twos() {
        YACHT yacht = new YACHT();
        int result = yacht.calculate("23456", "twos");
        assertEquals(2, result);
    }

    @Test
    void Fours() {
        YACHT yacht = new YACHT();
        int result = yacht.calculate("14141", "fours");
        assertEquals(8, result);
    }

    @Test
    void Yacht_counted_as_threes() {
        YACHT yacht = new YACHT();
        int result = yacht.calculate("33333", "threes");
        assertEquals(15, result);
    }

    @Test
    void Yacht_of_3s_counted_as_fives() {
        YACHT yacht = new YACHT();
        int result = yacht.calculate("33333", "fives");
        assertEquals(0, result);
    }

    @Test
    void Fives() {
        YACHT yacht = new YACHT();
        int result = yacht.calculate("15353", "fives");
        assertEquals(10, result);
    }

    @Test
    void Sixes() {
        YACHT yacht = new YACHT();
        int result = yacht.calculate("23456", "sixes");
        assertEquals(6, result);
    }

    @Test
    void Full_house_two_small_three_big() {
        YACHT yacht = new YACHT();
        int result = yacht.calculate("22444", "full house");
        assertEquals(16, result);
    }

    @Test
    void Full_house_three_small_two_big() {
        YACHT yacht = new YACHT();
        int result = yacht.calculate("53353", "full house");
        assertEquals(19, result);
    }

    @Test
    void Two_pair_is_not_a_full_house() {
        YACHT yacht = new YACHT();
        int result = yacht.calculate("22445", "full house");
        assertEquals(0, result);
    }

    @Test
    void Four_of_a_kind_is_not_a_full_house() {
        YACHT yacht = new YACHT();
        int result = yacht.calculate("14444", "full house");
        assertEquals(0, result);
    }

    @Test
    void Yacht_is_not_a_full_house() {
        YACHT yacht = new YACHT();
        int result = yacht.calculate("22222", "full house");
        assertEquals(0, result);
    }

    @Test
    void Four_of_a_Kind() {
        YACHT yacht = new YACHT();
        int result = yacht.calculate("66466", "four of a kind");
        assertEquals(24, result);
    }

    @Test
    void Yacht_can_be_scored_as_Four_of_a_Kind() {
        YACHT yacht = new YACHT();
        int result = yacht.calculate("33333", "four of a kind");
        assertEquals(12, result);
    }

    @Test
    void Full_house_is_not_Four_of_a_Kind() {
        YACHT yacht = new YACHT();
        int result = yacht.calculate("33355", "four of a kind");
        assertEquals(0, result);
    }

    @Test
    void Little_Straight() {
        YACHT yacht = new YACHT();
        int result = yacht.calculate("35412", "little straight");
        assertEquals(30, result);
    }

    @Test
    void Little_Straight_as_Big_Straight() {
        YACHT yacht = new YACHT();
        int result = yacht.calculate("12345", "big straight");
        assertEquals(0, result);
    }

    @Test
    void Four_in_order_but_not_a_little_straight() {
        YACHT yacht = new YACHT();
        int result = yacht.calculate("11234", "little straight");
        assertEquals(0, result);
    }

    @Test
    void No_pairs_but_not_a_little_straight() {
        YACHT yacht = new YACHT();
        int result = yacht.calculate("12346", "little straight");
        assertEquals(0, result);
    }

    @Test
    void Minimum_is_1_maximum_is_5_but_not_a_little_straight() {
        YACHT yacht = new YACHT();
        int result = yacht.calculate("11345", "little straight");
        assertEquals(0, result);
    }

    @Test
    void Big_Straight() {
        YACHT yacht = new YACHT();
        int result = yacht.calculate("46253", "big straight");
        assertEquals(30, result);
    }

    @Test
    void Big_Straight_as_little_straight() {
        YACHT yacht = new YACHT();
        int result = yacht.calculate("65432", "little straight");
        assertEquals(0, result);
    }

    @Test
    void No_pairs_but_not_a_big_straight() {
        YACHT yacht = new YACHT();
        int result = yacht.calculate("65431", "big straight");
        assertEquals(0, result);
    }

    @Test
    void Choice() {
        YACHT yacht = new YACHT();
        int result = yacht.calculate("33566", "choice");
        assertEquals(23, result);
    }

    @Test
    void Yacht_as_choice() {
        YACHT yacht = new YACHT();
        int result = yacht.calculate("22222", "choice");
        assertEquals(10, result);
    }

}