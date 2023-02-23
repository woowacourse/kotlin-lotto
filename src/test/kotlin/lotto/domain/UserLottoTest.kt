package lotto.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class UserLottoTest {
    private fun Lotto(vararg numbers: Int): Lotto {
        return Lotto(numbers.map { LottoNumber(it) })
    }

    @Test
    fun `현재 사용자가 가지고 있는 로또가 몇등인지 판단한다`() {
        val lotto = listOf(
            Lotto(1, 2, 3, 9, 10, 11),
            Lotto(7, 8, 9, 10, 11, 12)
        )
        val userLotto = UserLotto(lotto)
        assertThat(
            userLotto.calculateTotalRank(
                WinningNumbers(
                    Lotto(1, 2, 3, 4, 5, 6),
                    LottoNumber(7)
                )
            )
        ).isEqualTo(
            listOf(
                Rank.FIFTH, Rank.MISS
            )
        )
    }
}
