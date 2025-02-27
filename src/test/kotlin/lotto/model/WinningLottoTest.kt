package lotto.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class WinningLottoTest {
    @Test
    fun `로또 당첨을 판단한다`() {
        val winningLotto = WinningLotto.create(1, 2, 3, 4, 5, 6, bonusNumber = 7)
        val lottoTicket = LottoTicket.create(1,2,3,4,5,6)
        val rank = winningLotto.getRank(lottoTicket)

        assertThat(rank).isEqualTo(Rank.FIRST)
    }
}
