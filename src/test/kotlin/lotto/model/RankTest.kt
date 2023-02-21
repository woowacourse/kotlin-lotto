package lotto.model

import lotto.entity.Lotto
import lotto.entity.LottoNumber
import lotto.entity.WinLotto
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test

internal class RankTest {

    @Test
    fun `번호가 5개 일치하고, 보너스 번호가 일치하면 2등이다`() {
        val lotto = Lotto.from(
            listOf(
                LottoNumber(1),
                LottoNumber(2),
                LottoNumber(3),
                LottoNumber(4),
                LottoNumber(5),
                LottoNumber(6)
            )
        )
        val countOfMatch = lotto.determineCountOfMatch(winLotto.winNumber)
        val matchBonus = lotto.determineMatchBonus(winLotto.bonus)

        assertThat(Rank.valueOf(countOfMatch, matchBonus)).isEqualTo(Rank.SECOND)
    }

    @Test
    fun `번호가 4개 일치하면 4등이다`() {
        val lotto = Lotto.from(
            listOf(
                LottoNumber(1),
                LottoNumber(2),
                LottoNumber(3),
                LottoNumber(4),
                LottoNumber(6),
                LottoNumber(8)
            )
        )
        val countOfMatch = lotto.determineCountOfMatch(winLotto.winNumber)
        val matchBonus = lotto.determineMatchBonus(winLotto.bonus)

        assertThat(Rank.valueOf(countOfMatch, matchBonus)).isEqualTo(Rank.FOURTH)
    }

    companion object {
        private lateinit var winLotto: WinLotto

        @BeforeAll
        @JvmStatic
        fun init() {
            winLotto = WinLotto(
                Lotto.from(
                    listOf(
                        LottoNumber(1),
                        LottoNumber(2),
                        LottoNumber(3),
                        LottoNumber(4),
                        LottoNumber(5),
                        LottoNumber(7)
                    )
                ),
                LottoNumber(6)
            )
        }
    }
}
