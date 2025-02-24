package lotto.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class WinningLottoTest {
    @Test
    fun `로또 당첨을 판단한다`() {
        val bonusNumber = LottoNumber(7)

        val winningLotto =
            WinningLotto.create(
                    LottoNumber(1),
                    LottoNumber(2),
                    LottoNumber(3),
                    LottoNumber(4),
                    LottoNumber(5),
                    LottoNumber(6),
                bonusNumber = bonusNumber,
            )

        val lottoTicket =
            LottoTicket.create(
                    LottoNumber(1),
                    LottoNumber(2),
                    LottoNumber(3),
                    LottoNumber(4),
                    LottoNumber(5),
                    LottoNumber(6),
            )

        val rank = winningLotto.getRank(lottoTicket)

        assertThat(rank).isEqualTo(Rank.FIRST)
    }
}
