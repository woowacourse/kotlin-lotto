package lotto.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class UserLottoTest {
    @Test
    fun `현재 사용자가 가지고 있는 로또가 몇등인지 판단한다`() {
        val userLotto = makeUserLotto(listOf(listOf(1, 2, 3, 9, 10, 11), listOf(7, 8, 9, 10, 11, 12)))
        val winningNumbers = makeWinningNumbers(listOf(1, 2, 3, 4, 5, 6), 7)

        val actual = userLotto.calculateTotalRank(winningNumbers)

        val expected = listOf(Rank.FIFTH, Rank.MISS)
        assertThat(actual).isEqualTo(expected)
    }

    private fun makeUserLotto(numbers: List<List<Int>>): UserLotto {
        return UserLotto(numbers.map { makeLotto(it) })
    }

    private fun makeWinningNumbers(numbers: List<Int>, bonusNumber: Int): WinningNumbers {
        return WinningNumbers(makeLotto(numbers), LottoNumber.from(bonusNumber))
    }

    private fun makeLotto(numbers: List<Int>): Lotto {
        return Lotto(numbers.map { LottoNumber.from(it) })
    }
}
