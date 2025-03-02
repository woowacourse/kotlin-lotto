package lotto

import lotto.model.Lotto
import lotto.model.LottoNumber
import lotto.model.Rank
import lotto.model.WinningLotto
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class LottoTest {
    @Test
    fun `로또 하나의 숫자는 중복되지 않은 6개의 숫자이다`() {
        val lotto = Lotto.from(1, 2, 3, 4, 5, 6)
        val expected = 6
        val actual = lotto.numbers.distinct().size

        assertEquals(expected, actual)
    }

    @Test
    fun `로또 숫자가 중복될 경우 예외가 발생한다`() {
        assertThrows<IllegalArgumentException> {
            Lotto.from(1, 2, 3, 4, 5, 5)
        }
    }

    @Test
    fun `로또 숫자가 6개가 아닐 경우 예외를 발생한다`() {
        assertThrows<IllegalArgumentException> {
            Lotto.from(1, 2, 3, 4, 5)
        }
    }

    @Test
    fun `당첨 로또를 입력하면 로또가 몇 등인지 반환한다`() {
        val winningLotto = WinningLotto(Lotto.from(1, 2, 3, 4, 5, 6), LottoNumber(7))
        val lotto = Lotto.from(1, 2, 3, 4, 5, 7)

        val expected = Rank.SECOND

        assertThat(lotto.getRank(winningLotto)).isEqualTo(expected)
    }
}
