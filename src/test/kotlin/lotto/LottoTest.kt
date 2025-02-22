package lotto

import lotto.model.Lotto
import lotto.model.LottoNumber
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class LottoTest {
    @Test
    fun `로또 하나의 숫자는 중복되지 않은 6개의 숫자이다`() {
        val lotto = listOf(1, 2, 3, 4, 5, 6).toLotto()
        val expected = 6
        val actual = lotto.numbers.distinct().size

        Assertions.assertEquals(expected, actual)
    }

    @Test
    fun `로또 숫자가 중복될 경우 예외가 발생한다`() {
        assertThrows<IllegalArgumentException> {
            listOf(1, 2, 3, 4, 5, 5).toLotto()
        }
    }

    @Test
    fun `로또 숫자가 6개가 아닐 경우 예외를 발생한다`() {
        assertThrows<IllegalArgumentException> {
            listOf(1, 2, 3, 4, 5).toLotto()
        }
    }
}

// Fake Constructor
fun List<Int>.toLotto() = Lotto(this.map { number -> LottoNumber(number) })
