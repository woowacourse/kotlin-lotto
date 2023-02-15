package domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class LottoStatisticsTest {
    @ParameterizedTest(name = "{1}개가 일치하는 경우")
    @MethodSource("provideLottoAndMatchCount")
    fun `당첨 번호와 일치하는 숫자의 개수를 찾는다`(lotto: Lotto, expected: Int) {
        val winningNumbers = setOf(1, 2, 3, 4, 5, 6)
        val bonusNumber = 7
        val winningLotto = WinningLotto(Lotto(winningNumbers), bonusNumber)
        val lottoStatistics = LottoStatistics(winningLotto)
        val actual = lottoStatistics.compareNumbers(lotto.numbers)

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `보너스 번호와 일치하는지 여부를 확인한다`() {
        val winningNumbers = setOf(1, 2, 3, 4, 5, 6)
        val bonusNumber = 7
        val winningLotto = WinningLotto(Lotto(winningNumbers), bonusNumber)
        val lotto = Lotto(setOf(1, 2, 3, 4, 5, 7))
        val lottoStatistics = LottoStatistics(winningLotto)
        val expected = lottoStatistics.compareBonusNumber(lotto.numbers)
        val actual = true
        assertThat(actual).isEqualTo(expected)
    }

    companion object {
        @JvmStatic
        fun provideLottoAndMatchCount(): Stream<Arguments> {
            return Stream.of(
                Arguments.arguments(Lotto(setOf(1, 2, 3, 4, 5, 6)), 6),
                Arguments.arguments(Lotto(setOf(1, 2, 3, 4, 5, 7)), 5),
                Arguments.arguments(Lotto(setOf(1, 2, 3, 4, 8, 7)), 4),
                Arguments.arguments(Lotto(setOf(1, 2, 3, 9, 8, 7)), 3),
            )
        }
    }
}
