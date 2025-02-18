package lotto

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class WinningNumbersTest {
    @Test
    fun `당첨번호와 로또 번호를 비교해, 몇개 일치하는 지 확인`() {
        val lottoNumbers: List<Int> = listOf(1, 2, 3, 4, 5, 6)
        val winningNumbers: List<Int> = listOf(1, 2, 7, 8, 9, 10)
        val bonusNumber = 12
        assertThat(winningNumbers.intersect(lottoNumbers).size).isEqualTo(2)
        assertThat(bonusNumber in lottoNumbers).isFalse()
    }

    @Test
    fun `수익률 계산`() {
        val winningMoney = Rank.THIRD.winningMoney
        val winningCount = 10
        val purchaseAmount = 20_000

        assertThat(String.format("%.2f", winningMoney * winningCount / purchaseAmount.toDouble())).isEqualTo("750.00")
    }
}

enum class Rank(val countOfMatch: Int, val winningMoney: Int) {
    FIRST(6, 2_000_000_000),
    SECOND(5, 30_000_000),
    THIRD(5, 1_500_000),
    FOURTH(4, 50_000),
    FIFTH(3, 5_000),
    MISS(0, 0),

//    companion object {
//        fun valueOf(countOfMatch: Int, matchBonus: Boolean): Rank? {
//            return values().find {
//               it.countOfMatch == countOfMatch
//            }
//        }
//    }
}
