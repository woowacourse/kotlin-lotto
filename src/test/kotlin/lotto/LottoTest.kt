package lotto

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class LottoTest {
    @Test
    fun `로또 하나의 숫자는 중복되지 않은 6개의 숫자이다`() {
        // given
        val lotto = Lotto(listOf(1, 2, 3, 4, 5, 6))
        val expected = 6

        // when
        val actual = lotto.numbers.distinct().size

        // then
        Assertions.assertEquals(expected, actual)
    }

    @Test
    fun `로또 숫자가 중복될 경우 예외가 발생한다`() {
        assertThrows<IllegalArgumentException> {
            Lotto(listOf(1, 2, 3, 4, 5, 5))
        }
    }
}
