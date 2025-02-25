package lotto.domain.model

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class WinningNumbersTest {
    @Test
    fun `보너스 번호가 당첨 번호와 중복되면 예외가 발생한다`() {
        val lotto = listOf(1, 2, 3, 4, 5, 6)
        val bonusNumber = 6
        assertThatThrownBy { WinningNumbers(lotto, bonusNumber) }.isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("보너스 번호 ${bonusNumber}은(는) 당첨 번호와 중복 될 수 없습니다.")
    }

    @MethodSource("calculateLottoRanksTest")
    @ParameterizedTest
    fun `로또 랭크를 구할 수 있다`(
        lotto: Lotto,
        actual: LottoRank,
    ) {
        val winningNumbers = WinningNumbers(listOf(1, 2, 3, 4, 5, 6), 8)
        assertThat(winningNumbers.calculateLottoRanks(listOf(lotto)).lottoRanks).isEqualTo(listOf(actual))
    }

    companion object {
        @JvmStatic
        fun calculateLottoRanksTest(): List<Arguments> {
            return listOf(
                Arguments.arguments(Lotto(listOf(1, 2, 3, 4, 5, 6)), LottoRank.FIRST),
                Arguments.arguments(Lotto(listOf(1, 2, 3, 4, 8, 6)), LottoRank.SECOND),
                Arguments.arguments(Lotto(listOf(1, 2, 3, 7, 5, 6)), LottoRank.THIRD),
                Arguments.arguments(Lotto(listOf(1, 2, 3, 4, 8, 12)), LottoRank.FOURTH),
                Arguments.arguments(Lotto(listOf(1, 2, 3, 22, 8, 18)), LottoRank.FIFTH),
                Arguments.arguments(Lotto(listOf(7, 8, 9, 10, 11, 12)), LottoRank.MISS),
            )
        }
    }
}
