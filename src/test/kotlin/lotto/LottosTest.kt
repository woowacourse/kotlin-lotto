package lotto

import lotto.model.LottoNumbers
import lotto.model.Lottos
import lotto.model.WinningNumbers
import lotto.model.WinningRank
import model.Lotto
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class LottosTest {
    private fun setUpLotto(vararg numbers: Int): Lotto = Lotto(LottoNumbers.lottoNumbersOf(*numbers))

    @Test
    fun `Lottos에 추가한 Lotto가 있는지 테스트`() {
        val testLotto1 = setUpLotto(1, 2, 3, 4, 5, 6)
        val testLotto2 = setUpLotto(11, 12, 13, 14, 15, 16)
        val lottos = Lottos(listOf(testLotto1, testLotto2))
        assertThat(lottos.lottos).usingRecursiveComparison().isEqualTo(
            listOf(testLotto1, testLotto2),
        )
    }

    @Test
    fun `결과 계산 테스트 - 1등`() {
        val lottos = Lottos(listOf(setUpLotto(1, 2, 3, 4, 5, 6)))
        val winningNumbers = WinningNumbers.of(listOf("1", "2", "3", "4", "5", "6"), "10")
        assertThat(
            lottos.winningResult(
                winningNumbers,
            ),
        ).isEqualTo(mapOf(WinningRank.FIRST to 1))
    }

    @Test
    fun `결과 계산 테스트 - 2등`() {
        val lottos = Lottos(listOf(setUpLotto(1, 2, 3, 4, 5, 6)))
        val winningNumbers = WinningNumbers.of(listOf("1", "2", "3", "4", "5", "7"), "6")
        assertThat(
            lottos.winningResult(
                winningNumbers,
            ),
        ).isEqualTo(mapOf(WinningRank.SECOND to 1))
    }

    @Test
    fun `결과 계산 테스트 - 3등`() {
        val lottos = Lottos(listOf(setUpLotto(1, 2, 3, 4, 5, 6)))
        val winningNumbers = WinningNumbers.of(listOf("1", "2", "3", "4", "5", "7"), "10")
        assertThat(
            lottos.winningResult(
                winningNumbers,
            ),
        ).isEqualTo(mapOf(WinningRank.THIRD to 1))
    }

    @Test
    fun `결과 계산 테스트 - 4등`() {
        val lottos = Lottos(listOf(setUpLotto(1, 2, 3, 4, 5, 6)))
        val winningNumbers = WinningNumbers.of(listOf("1", "2", "3", "4", "15", "25"), "10")
        assertThat(
            lottos.winningResult(
                winningNumbers,
            ),
        ).isEqualTo(mapOf(WinningRank.FOURTH to 1))
    }

    @Test
    fun `결과 계산 테스트 - 5등`() {
        val lottos = Lottos(listOf(setUpLotto(1, 2, 3, 4, 5, 6)))
        val winningNumbers = WinningNumbers.of(listOf("1", "2", "3", "14", "15", "17"), "40")
        assertThat(
            lottos.winningResult(
                winningNumbers,
            ),
        ).isEqualTo(mapOf(WinningRank.FIFTH to 1))
    }

    @Test
    fun `결과 계산 테스트 - 꽝`() {
        val lottos = Lottos(listOf(setUpLotto(1, 2, 3, 4, 5, 6)))
        val winningNumbers = WinningNumbers.of(listOf("11", "22", "33", "34", "35", "37"), "17")
        assertThat(
            lottos.winningResult(
                winningNumbers,
            ),
        ).isEqualTo(mapOf(WinningRank.NONE to 1))
    }
}
