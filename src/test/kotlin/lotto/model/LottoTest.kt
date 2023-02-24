package lotto.model

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
class LottoTest {
    @ParameterizedTest
    @CsvSource(
        "1 2 3 4 5 6, FIRST",
        "1 2 3 4 5 7, SECOND",
        "1 2 3 4 5 9, THIRD",
        "1 2 3 9 10 11, FIFTH"
    )
    fun `일치하는 번호 개수에 따라 Rank를 구한다`(numbers: String, result: Rank) {
        val winning = WinningLotto(listOf(1, 2, 3, 4, 5, 6), 7)
        val number = numbers.split(" ").map { it.toInt() }

        val actual = Lotto.create(number).getRank(winning)

        assertEquals(result, actual)
    }

    @Test
    fun `로또 숫자 개수가 6개가 맞지 않는 경우 생성 시 오류가 발생한다`() {
        val list = listOf(1, 2, 3, 4, 5)
        assertThrows<IllegalArgumentException> { Lotto.create(list) }
    }

    @Test
    fun `로또 번호 내에 중복된 번호가 있는 경우 생성 시 오류가 발생한다`() {
        val list = listOf(1, 2, 3, 4, 4, 5)
        assertThrows<IllegalArgumentException> { Lotto.create(list) }
    }

    @Test
    fun `로또 번호 내에 범위를 벗어난 번호가 있는 경우 생성 시 오류가 발생한다`() {
        val list = listOf(1, 2, 3, 4, 5, 66)
        assertThrows<IllegalArgumentException> { Lotto.create(list) }
    }
}
