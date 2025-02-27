package lotto.domain

import lotto.util.Rank
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class WinningLottoTest {
    @Test
    fun `로또 번호와 보너스 번호가 중복되면 오류가 발생한다`() {
        assertThrows<IllegalArgumentException> {
            WinningLotto(
                winningLottoNumber =
                    Lotto(
                        listOf(
                            1,
                            1,
                            3,
                            4,
                            5,
                            6,
                        ).map { LottoNumber(it) }.toSet(),
                    ),
                winningBonusNumber = LottoNumber(1),
            )
        }
    }

    @Test
    fun `로또 번호를 넣으면 당첨 등수를 반환한다`() {
        val winningLottoTicket =
            Lotto(listOf(LottoNumber(1), LottoNumber(2), LottoNumber(3), LottoNumber(4), LottoNumber(5), LottoNumber(6)).toSet())
        val winningBonusNumber = LottoNumber(7)
        val winningLotto = WinningLotto(winningLottoTicket, winningBonusNumber)
        val lotto = Lotto(listOf(LottoNumber(1), LottoNumber(2), LottoNumber(3), LottoNumber(4), LottoNumber(5), LottoNumber(6)).toSet())
        assertThat(winningLotto.match(lotto)).isEqualTo(Rank.FIRST)
    }
}
