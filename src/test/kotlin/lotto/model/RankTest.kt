package lotto.model

import lotto.entity.Bonus
import lotto.entity.Lotto
import lotto.entity.WinLotto
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test

internal class RankTest {

    @Test
    fun `번호가 5개 일치하고, 보너스 번호가 일치하면 2등이다`() {
        val lotto = Lotto(listOf(1, 2, 3, 4, 5, 6))
        val countOfMatch = lotto.numbers.intersect(winLotto.winNumber.numbers.toSet()).size
        val matchBonus = lotto.numbers.contains(winLotto.bonus.value)

        assertThat(Rank.valueOf(countOfMatch, matchBonus)).isEqualTo(Rank.SECOND)
    }

    @Test
    fun `번호가 4개 일치하면 4등이다`() {
        val lotto = Lotto(listOf(1, 2, 3, 4, 6, 8))
        val countOfMatch = lotto.numbers.intersect(winLotto.winNumber.numbers.toSet()).size
        val matchBonus = lotto.numbers.contains(winLotto.bonus.value)

        assertThat(Rank.valueOf(countOfMatch, matchBonus)).isEqualTo(Rank.FOURTH)
    }

    companion object {
        private lateinit var winLotto: WinLotto

        @BeforeAll
        @JvmStatic
        fun init() {
            winLotto = WinLotto(Lotto(listOf(1, 2, 3, 4, 5, 7)), Bonus(6))
        }
    }
}
