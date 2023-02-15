package lotto.domain

import lotto.constants.Rank
import org.junit.jupiter.api.Test
import org.assertj.core.api.Assertions.assertThat


class UserLottoTest {
    @Test
    fun `현재 사용자가 가지고 있는 로또가 몇등인지 판단한다`() {
        val lotto = listOf(Lotto(listOf(1, 2, 3, 9, 10, 11)), Lotto(listOf(7, 8, 9, 10, 11, 12)))
        val userLotto = UserLotto(lotto)
        assertThat(userLotto.calculateTotalRank(WinningNumbers(Lotto(listOf(1, 2, 3, 4, 5, 6)), 7))).isEqualTo(listOf(
            Rank.FIFTH, Rank.MISS))
    }
}