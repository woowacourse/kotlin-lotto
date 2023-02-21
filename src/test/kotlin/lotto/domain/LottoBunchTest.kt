package lotto.domain

import lotto.constant.Rank
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class LottoBunchTest {
    val lottoBunch = LottoBunch(
        listOf(
            Lotto(
                setOf(
                    LottoNumber(1),
                    LottoNumber(2),
                    LottoNumber(3),
                    LottoNumber(4),
                    LottoNumber(5),
                    LottoNumber(6),
                ),
            ),
            Lotto(
                setOf(
                    LottoNumber(7),
                    LottoNumber(8),
                    LottoNumber(9),
                    LottoNumber(10),
                    LottoNumber(11),
                    LottoNumber(12),
                ),
            ),
            Lotto(
                setOf(
                    LottoNumber(13),
                    LottoNumber(14),
                    LottoNumber(15),
                    LottoNumber(16),
                    LottoNumber(17),
                    LottoNumber(18),
                ),
            ),
        ),
    )

    val winningLotto: WinningLotto = WinningLotto(
        Lotto(
            setOf(
                LottoNumber(1),
                LottoNumber(10),
                LottoNumber(3),
                LottoNumber(4),
                LottoNumber(5),
                LottoNumber(6),
            ),
        ),
        LottoNumber(2),
    )

    @Test
    fun `2등에 1번 당첨되었다`() {
        assertThat(
            lottoBunch.getRanks(winningLotto),
        ).containsOnlyOnce(Rank.SECOND)
    }

    @Test
    fun `2등에 1번 당첨된경우 최종 당첨금액은 30000000다`() {
        assertThat(lottoBunch.sumTotalPrizeMoney(WinningResult.from(lottoBunch.getRanks(winningLotto)))).isEqualTo(
            30000000,
        )
    }
}
