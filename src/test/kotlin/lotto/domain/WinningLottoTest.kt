package lotto.domain

import lotto.util.Rank
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class WinningLottoTest {
    fun createLotto(vararg numbers: Int): Lotto {
        return Lotto.of(setOf(*numbers.map { LottoNumber.from(it) }.toTypedArray())) ?: error("로또 생성 실패")
    }

    @Test
    fun `로또 번호와 보너스 번호가 중복되면 오류가 발생한다`() {
        assertThrows<IllegalArgumentException> {
            WinningLotto.of(createLotto(1, 2, 3, 4, 5, 6), LottoNumber.from(1))
        }
    }

    @Test
    fun `로또 번호를 넣으면 당첨 등수를 반환한다`() {
        val winningLottoTicket =
            createLotto(1, 2, 3, 4, 5, 6)
        val winningBonusNumber = LottoNumber.from(7)
        val winningLotto = WinningLotto.of(winningLottoTicket, winningBonusNumber)
        val lotto = createLotto(1, 2, 3, 4, 5, 6)
        assertThat(winningLotto.match(lotto)).isEqualTo(Rank.FIRST)
    }
}
