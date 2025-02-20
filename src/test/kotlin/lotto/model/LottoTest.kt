package lotto.model

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource

class LottoTest {
    @Test
    fun `로또가 6개가 아닌 번호를 가질 때 오류를 반환한다`() {
        val numbers = listOf(1, 2, 3, 4, 5)
        assertThrows<IllegalArgumentException> { Lotto(numbers) }
    }

    @ParameterizedTest
    @MethodSource("lottoCasesForRankTest")
    fun `각 로또는 해당되는 당첨 번호에 맞게 랭크가 반환된다`(matchCase: Pair<List<Int>, Rank>) {
        val (lottoNumbers, expectedRank) = matchCase
        val lotto = Lotto(lottoNumbers)

        val winningNumbers = listOf(1, 2, 3, 4, 5, 6)
        val bonusNumber = 7

        val rank = lotto.getRank(winningNumbers, bonusNumber)

        assertEquals(expectedRank, rank)
    }

    @Test
    fun `보너스 번호가 일치하면서 5개의 번호가 동일한 경우 2등을 반환한다`() {
        val winningNumbers = listOf(1, 2, 3, 4, 5, 6)
        val bonusNumber = 7

        val lotto = Lotto(listOf(1, 2, 3, 4, 5, 7))
        val rank = lotto.getRank(winningNumbers, bonusNumber)

        assertEquals(Rank.SECOND, rank)
    }

    companion object {
        @JvmStatic
        fun lottoCasesForRankTest(): List<Pair<List<Int>, Rank>> =
            listOf(
                listOf(1, 2, 3, 4, 5, 6) to Rank.FIRST,
                listOf(1, 2, 3, 4, 5, 8) to Rank.THIRD,
                listOf(1, 2, 3, 4, 9, 10) to Rank.FOURTH,
                listOf(1, 2, 3, 8, 9, 10) to Rank.FIFTH,
                listOf(1, 2, 23, 24, 25, 26) to Rank.MISS,
                listOf(1, 22, 23, 24, 25, 26) to Rank.MISS,
                listOf(21, 22, 23, 24, 25, 26) to Rank.MISS,
            )
    }
}
