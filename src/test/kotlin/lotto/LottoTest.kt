package lotto

import lotto.model.Lotto
import lotto.model.Lotto.Companion.LOTTO_BOUND_MESSAGE
import lotto.model.Lotto.Companion.LOTTO_COUNT_MESSAGE
import lotto.model.Lotto.Companion.LOTTO_DISTINCT_MESSAGE
import lotto.model.LottoNumber
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class LottoTest {
    @Test
    fun `발급하는 로또 번호는 6개여야 한다 `() {
        val lotto = Lotto(List(6) { LottoNumber(it + 1) })
        assertThat(lotto.getNumbers()).hasSize(6)
    }

    @Test
    fun `발급하는 로또 번호는 6개가 아니면 예외가 발생한다 `() {
        val exception = assertThrows<IllegalArgumentException> { Lotto(List(7) { LottoNumber(it + 1) }) }
        assertThat(exception).hasMessage(LOTTO_COUNT_MESSAGE)
    }

    @Test
    fun `발급하는 로또 번호는 1부터 45 범위 내에 있어야 한다 `() {
        val exception =
            assertThrows<IllegalArgumentException> {
                Lotto(
                    listOf(
                        LottoNumber(1),
                        LottoNumber(2),
                        LottoNumber(3),
                        LottoNumber(4),
                        LottoNumber(5),
                        LottoNumber(46),
                    ),
                )
            }
        assertThat(exception).hasMessage(LOTTO_BOUND_MESSAGE)
    }

    @Test
    fun `로또 번호는 중복되지 않아야 한다 `() {
        val exception =
            assertThrows<IllegalArgumentException> {
                Lotto(listOf(LottoNumber(1), LottoNumber(2), LottoNumber(3), LottoNumber(4), LottoNumber(5), LottoNumber(5)))
            }
        assertThat(exception).hasMessage(LOTTO_DISTINCT_MESSAGE)
    }
}
