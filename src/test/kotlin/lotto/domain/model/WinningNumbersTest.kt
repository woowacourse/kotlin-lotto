package lotto.domain.model

import lotto.domain.service.FixedLottoNumbersGenerator
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class WinningNumbersTest {
    @Test
    fun `보너스 번호가 당첨 번호와 중복되면 예외가 발생한다`() {
        val lotto = listOf(1, 2, 3, 4, 5, 6)
        val exception =
            assertThrows<IllegalArgumentException> {
                WinningNumbers(lotto, 6)
            }

        assertThat(exception.message).isEqualTo("보너스 번호는 당첨 번호와 중복될 수 없습니다.")
    }

    @MethodSource("calculateLottoRanksTest")
    @ParameterizedTest
    fun `로또 랭크를 구할 수 있다`(
        lottoNumbers: List<Int>,
        actual: LottoRank,
    ) {
        val winningNumbers = WinningNumbers(listOf(1, 2, 3, 4, 5, 6), 8)
        assertThat(
            winningNumbers.calculateLottoRanks(
                LottoMachine(generator = FixedLottoNumbersGenerator(lottoNumbers)).generateLottoBundle(1),
            ).lottoRanks.keys.first(),
        ).isEqualTo(actual)
    }

    companion object {
        @JvmStatic
        fun calculateLottoRanksTest(): List<Arguments> {
            return listOf(
                Arguments.arguments(listOf(1, 2, 3, 4, 5, 6), LottoRank.FIRST),
                Arguments.arguments(listOf(1, 2, 3, 4, 8, 6), LottoRank.SECOND),
                Arguments.arguments(listOf(1, 2, 3, 7, 5, 6), LottoRank.THIRD),
                Arguments.arguments(listOf(1, 2, 3, 4, 8, 12), LottoRank.FOURTH),
                Arguments.arguments(listOf(1, 2, 3, 22, 8, 18), LottoRank.FIFTH),
                Arguments.arguments(listOf(7, 8, 9, 10, 11, 12), LottoRank.MISS),
            )
        }
    }
}
