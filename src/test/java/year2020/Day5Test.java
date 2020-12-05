package year2020;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

class Day5Test {

  @Test
  void decode() {
    /*BFFFBBFRRR: row 70, column 7, seat ID 567.
    FFFBBBFRRR: row 14, column 7, seat ID 119.
    BBFFBBFRLL: row 102, column 4, seat ID 820.*/
    Day5.SithId r0 = Day5.decode("FBFBBFFRLR");
    Day5.SithId r1 = Day5.decode("BFFFBBFRRR");
    Day5.SithId r2 = Day5.decode("FFFBBBFRRR");
    Day5.SithId r3 = Day5.decode("BBFFBBFRLL");

    assertEquals(44, r0.row);
    assertEquals(5, r0.col);
    assertEquals(357, r0.seatId);

    assertEquals(70, r1.row);
    assertEquals(7, r1.col);
    assertEquals(567, r1.seatId);

    assertEquals(14, r2.row);
    assertEquals(7, r2.col);
    assertEquals(119, r2.seatId);

    assertEquals(102, r3.row);
    assertEquals(4, r3.col);
    assertEquals(820, r3.seatId);
  }
}
