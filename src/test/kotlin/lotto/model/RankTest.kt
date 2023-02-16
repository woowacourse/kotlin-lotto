package lotto.model

import lotto.entity.Bonus
import lotto.entity.Lotto
import lotto.entity.WinLotto
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class RankTest {
    @Test
    fun `번호가 5개 일치하고, 보너스 번호가 일치하면 2등이다`() {
        val lotto = Lotto(listOf(1, 2, 3, 4, 5, 6))
        val winNumber = Lotto(listOf(1, 2, 3, 4, 5, 7))
        val bonus = Bonus(6)
        val winLotto = WinLotto(winNumber, bonus)
        val rankDeterminer = LottoRankDeterminer()

        assertThat(rankDeterminer.determine(lotto, winLotto)).isEqualTo(Rank.SECOND)
    }

    @Test
    fun `번호가 4개 일치하면 4등이다`() {
        val lotto = Lotto(listOf(1, 2, 3, 4, 5, 6))
        val winNumber = Lotto(listOf(1, 2, 3, 4, 7, 8))
        val bonus = Bonus(6)
        val winLotto = WinLotto(winNumber, bonus)
        val rankDeterminer = LottoRankDeterminer()

        assertThat(rankDeterminer.determine(lotto, winLotto)).isEqualTo(Rank.FOURTH)
    }
}
