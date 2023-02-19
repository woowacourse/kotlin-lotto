package lotto.model

import lotto.entity.Lotto
import lotto.entity.LottoNumber
import lotto.entity.WinLotto
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class RankTest {
    @Test
    fun `번호가 5개 일치하고, 보너스 번호가 일치하면 2등이다`() {
        // given
        val lotto = Lotto(listOf(1, 2, 3, 4, 5, 6))
        val winNumber = Lotto(listOf(1, 2, 3, 4, 5, 7))
        val bonus = LottoNumber.from(6)
        val winLotto = WinLotto(winNumber, bonus)

        // when
        val determine = Rank.determine(lotto, winLotto)
        val except = Rank.SECOND

        // then
        assertThat(determine).isEqualTo(except)
    }

    @Test
    fun `번호가 4개 일치하면 4등이다`() {
        // given
        val lotto = Lotto(listOf(1, 2, 3, 4, 5, 6))
        val winNumber = Lotto(listOf(1, 2, 3, 4, 7, 8))
        val bonus = LottoNumber.from(6)
        val winLotto = WinLotto(winNumber, bonus)
        // when
        val determine = Rank.determine(lotto, winLotto)
        val except = Rank.FOURTH

        // then
        assertThat(determine).isEqualTo(except)
    }
}
