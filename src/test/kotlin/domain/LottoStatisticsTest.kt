package domain

import org.assertj.core.api.Assertions.assertThat
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
        val actual = lottoStatistics.compareNumbers(lotto)

        assertThat(actual).isEqualTo(expected)
    }

    @ParameterizedTest(name = "{1}인 경우")
    @MethodSource("provideLottoAndBonusMatchResult")
    fun `보너스 번호와 일치하는지 여부를 확인한다`(lotto: Lotto, expected: Boolean) {
        val winningNumbers = setOf(1, 2, 3, 4, 5, 6)
        val bonusNumber = 7
        val winningLotto = WinningLotto(Lotto(winningNumbers), bonusNumber)
        val lottoStatistics = LottoStatistics(winningLotto)
        val actual = lottoStatistics.compareBonusNumber(lotto)

        assertThat(actual).isEqualTo(expected)
    }

    @ParameterizedTest(name = "{1}인 경우")
    @MethodSource("provideLottoAndRankMatchResult")
    fun `단일 로또를 넘겨 받아서 당첨 결과를 반환한다`(lotto: Lotto, expected: Rank) {
        val winningNumbers = setOf(1, 2, 3, 4, 5, 6)
        val bonusNumber = 7
        val winningLotto = WinningLotto(Lotto(winningNumbers), bonusNumber)
        val lottoStatistics = LottoStatistics(winningLotto)
        val actual = lottoStatistics.compare(lotto)

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

        @JvmStatic
        fun provideLottoAndBonusMatchResult(): Stream<Arguments> {
            return Stream.of(
                Arguments.arguments(Lotto(setOf(1, 2, 3, 4, 5, 6)), false),
                Arguments.arguments(Lotto(setOf(1, 2, 3, 4, 5, 7)), true)
            )
        }

        @JvmStatic
        fun provideLottoAndRankMatchResult(): Stream<Arguments> {
            return Stream.of(
                Arguments.arguments(Lotto(setOf(1, 2, 3, 4, 5, 6)), Rank.FIRST),
                Arguments.arguments(Lotto(setOf(1, 2, 3, 4, 5, 7)), Rank.SECOND),
                Arguments.arguments(Lotto(setOf(1, 2, 3, 4, 5, 10)), Rank.THIRD),
                Arguments.arguments(Lotto(setOf(1, 2, 3, 4, 10, 11)), Rank.FOURTH),
                Arguments.arguments(Lotto(setOf(1, 2, 3, 11, 12, 45)), Rank.FIFTH),
                Arguments.arguments(Lotto(setOf(1, 2, 11, 12, 15, 45)), Rank.MISS),
            )
        }
    }
}
