package lotto

import lotto.model.Lotto
import lotto.model.LottoNumber
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class LottoTest {
    @Test
    fun `로또 하나의 숫자는 중복되지 않은 6개의 숫자이다`() {
        val lotto = Lotto(1, 2, 3, 4, 5, 6)
        val expected = 6
        val actual = lotto.numbers.distinct().size

        assertEquals(expected, actual)
    }

    @Test
    fun `로또 숫자가 중복될 경우 예외가 발생한다`() {
        assertThrows<IllegalArgumentException> {
            Lotto(1, 2, 3, 4, 5, 5)
        }
    }

    @Test
    fun `로또 숫자가 6개가 아닐 경우 예외를 발생한다`() {
        assertThrows<IllegalArgumentException> {
            Lotto(1, 2, 3, 4, 5)
        }
    }

    @Test
    fun `다른 로또와 본인 로또를 비교하여 일치 개수를 반환한다`() {
        val lotto = Lotto(1, 2, 3, 4, 5, 6)
        val otherLotto = Lotto(1, 2, 3, 4, 7, 9)

        assertThat(lotto.getMatchCount(otherLotto)).isEqualTo(4)
    }

    @Test
    fun `로또 번호가 본인 로또에 포함되어 있을 경우 true 를 반환한다`() {
        val lotto = Lotto(1, 2, 3, 4, 5, 6)
        val number = LottoNumber(6)

        assertThat(lotto.containsNumber(number)).isEqualTo(true)
    }

    @Test
    fun `로또 번호가 본인 로또에 포함되어 있지 않을 경우 false 를 반환한다`() {
        val lotto = Lotto(1, 2, 3, 4, 5, 6)
        val number = LottoNumber(10)

        assertThat(lotto.containsNumber(number)).isEqualTo(false)
    }
}
