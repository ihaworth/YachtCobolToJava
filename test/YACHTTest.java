import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class YACHTTest {

    private final YACHT yacht = new YACHT();

    @Test
    void yacht() {
        assertEquals(50, yacht.score("55555", "yacht"));
    }

    @Test
    void Not_Yacht() {
        assertEquals(0, yacht.score("13325", "yacht"));
    }

    @Test
    void Ones() {
        assertEquals(3, yacht.score("11135", "ones"));
    }

    @Test
    void Ones_out_of_order() {
        assertEquals(3, yacht.score("31151", "ones"));
    }

    @Test
    void No_ones() {
        assertEquals(0, yacht.score("43655", "ones"));
    }

    @Test
    void Twos() {
        assertEquals(2, yacht.score("23456", "twos"));
    }

    @Test
    void Fours() {
        assertEquals(8, yacht.score("14141", "fours"));
    }

    @Test
    void Yacht_counted_as_threes() {
        assertEquals(15, yacht.score("33333", "threes"));
    }

    @Test
    void Yacht_of_3s_counted_as_fives() {
        assertEquals(0, yacht.score("33333", "fives"));
    }

    @Test
    void Fives() {
        assertEquals(10, yacht.score("15353", "fives"));
    }

    @Test
    void Sixes() {
        assertEquals(6, yacht.score("23456", "sixes"));
    }

    @Test
    void Full_house_two_small_three_big() {
        assertEquals(16, yacht.score("22444", "full house"));
    }

    @Test
    void Full_house_three_small_two_big() {
        assertEquals(19, yacht.score("53353", "full house"));
    }

    @Test
    void Two_pair_is_not_a_full_house() {
        assertEquals(0, yacht.score("22445", "full house"));
    }

    @Test
    void Four_of_a_kind_is_not_a_full_house() {
        assertEquals(0, yacht.score("14444", "full house"));
    }

    @Test
    void Yacht_is_not_a_full_house() {
        assertEquals(0, yacht.score("22222", "full house"));
    }

    @Test
    void Four_of_a_Kind() {
        assertEquals(24, yacht.score("66466", "four of a kind"));
    }

    @Test
    void Yacht_can_be_scored_as_Four_of_a_Kind() {
        assertEquals(12, yacht.score("33333", "four of a kind"));
    }

    @Test
    void Full_house_is_not_Four_of_a_Kind() {
        assertEquals(0, yacht.score("33355", "four of a kind"));
    }

    @Test
    void Little_Straight() {
        assertEquals(30, yacht.score("35412", "little straight"));
    }

    @Test
    void Little_Straight_as_Big_Straight() {
        assertEquals(0, yacht.score("12345", "big straight"));
    }

    @Test
    void Four_in_order_but_not_a_little_straight() {
        assertEquals(0, yacht.score("11234", "little straight"));
    }

    @Test
    void No_pairs_but_not_a_little_straight() {
        assertEquals(0, yacht.score("12346", "little straight"));
    }

    @Test
    void Minimum_is_1_maximum_is_5_but_not_a_little_straight() {
        assertEquals(0, yacht.score("11345", "little straight"));
    }

    @Test
    void Big_Straight() {
        assertEquals(30, yacht.score("46253", "big straight"));
    }

    @Test
    void Big_Straight_as_little_straight() {
        assertEquals(0, yacht.score("65432", "little straight"));
    }

    @Test
    void No_pairs_but_not_a_big_straight() {
        assertEquals(0, yacht.score("65431", "big straight"));
    }

    @Test
    void Choice() {
        assertEquals(23, yacht.score("33566", "choice"));
    }

    @Test
    void Yacht_as_choice() {
        assertEquals(10, yacht.score("22222", "choice"));
    }

}