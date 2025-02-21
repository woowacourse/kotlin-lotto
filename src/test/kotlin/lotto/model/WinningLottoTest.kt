package lotto.model

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class WinningLottoTest {
    @ParameterizedTest
    @MethodSource("lottoCasesForResultTest")
    fun `각 로또는 해당되는 당첨 번호에 맞게 랭크가 반환된다`(
        lottoNumbers: List<Int>,
        expectedRank: Rank,
    ) {
        val lotto = Lotto(lottoNumbers)
        val winningLotto = WinningLotto(lotto)

        val winningNumbers = listOf(1, 2, 3, 4, 5, 6)
        val bonusNumber = 7

        val rank = winningLotto.getRank(winningNumbers, bonusNumber)

        assertEquals(expectedRank, rank)
    }

    @Test
    fun `보너스 번호가 일치하면서 5개의 번호가 동일한 경우 2등을 반환한다`() {
        val winningNumbers = listOf(1, 2, 3, 4, 5, 6)
        val bonusNumber = 7

        val lotto = Lotto(listOf(1, 2, 3, 4, 5, 7))
        val winningLotto = WinningLotto(lotto)
        val rank = winningLotto.getRank(winningNumbers, bonusNumber)

        assertEquals(Rank.SECOND, rank)
    }

    @Test
    fun `당첨 번호에서 중복되는 번호가 있을 경우 오류를 반환한다`() {
        val lottoNumbers = listOf(1, 2, 3, 4, 5, 6)
        val lotto = Lotto(lottoNumbers)
        val winningLotto = WinningLotto(lotto)

        val winningNumbers = listOf(1, 1, 2, 3, 4, 5)
        val bonusNumber = 6

        assertThrows<IllegalArgumentException> {
            winningLotto.getRank(winningNumbers, bonusNumber)
        }
    }

    @Test
    fun `당첨 번호는 중복되지 않았을 때, 보너스 번호와 중복되면 오류를 반환한다`() {
        val lottoNumbers = listOf(1, 2, 3, 4, 5, 6)
        val lotto = Lotto(lottoNumbers)
        val winningLotto = WinningLotto(lotto)

        val winningNumbers = listOf(1, 2, 3, 4, 5, 6)
        val bonusNumber = 1

        assertThrows<IllegalArgumentException> {
            winningLotto.getRank(winningNumbers, bonusNumber)
        }
    }

    companion object {
        @JvmStatic
        fun lottoCasesForResultTest(): Stream<Arguments> =
            Stream.of(
                Arguments.of(listOf(1, 2, 3, 4, 5, 6), Rank.FIRST),
                Arguments.of(listOf(1, 2, 3, 4, 5, 8), Rank.THIRD),
                Arguments.of(listOf(1, 2, 3, 4, 9, 10), Rank.FOURTH),
                Arguments.of(listOf(1, 2, 3, 8, 9, 10), Rank.FIFTH),
                Arguments.of(listOf(1, 2, 23, 24, 25, 26), Rank.MISS),
                Arguments.of(listOf(1, 22, 23, 24, 25, 26), Rank.MISS),
                Arguments.of(listOf(21, 22, 23, 24, 25, 26), Rank.MISS),
            )
    }
}
