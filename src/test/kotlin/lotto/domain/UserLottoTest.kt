package lotto.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class UserLottoTest {
    @Test
    fun `현재 사용자가 가지고 있는 로또가 몇등인지 판단한다`() {
        assertThat(
            UserLotto(
                listOf(
                    makeLotto(listOf(1, 2, 3, 9, 10, 11)),
                    makeLotto(listOf(7, 8, 9, 10, 11, 12))
                )
            ).calculateTotalRank(
                WinningNumbers(
                    makeLotto(listOf(1, 2, 3, 4, 5, 6)),
                    LottoNumber.from(7)
                )
            )
        ).isEqualTo(
            listOf(Rank.FIFTH, Rank.MISS)
        )
    }

    private fun makeLotto(numbers: List<Int>): Lotto {
        return Lotto(numbers.map { LottoNumber.from(it) })
    }
}
