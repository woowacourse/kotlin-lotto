package domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.lang.reflect.Method
import java.util.stream.Stream

class LottoStatisticsTest {
    @ParameterizedTest(name = "{1}개가 일치하는 경우")
    @MethodSource("provideLottoAndMatchCount")
    fun `당첨 번호와 일치하는 숫자의 개수를 찾는다`(lotto: Lotto, expected: Int) {
        // given
        val winningNumbers = listOf(1, 2, 3, 4, 5, 6).map { number -> LottoNumber(number) }.toSet()
        val bonusNumber = LottoNumber(7)
        val winningLotto = WinningLotto(Lotto(winningNumbers), bonusNumber)
        val lottoStatistics = LottoStatistics(winningLotto)

        val method = lottoStatistics.javaClass.declaredMethods.find {
            it.name == "compareNumbers"
        }
        method?.isAccessible = true

        // when
        val actual = method?.invoke(lottoStatistics, lotto)

        // then
        assertThat(actual).isEqualTo(expected)
    }

    @ParameterizedTest(name = "{1}인 경우")
    @MethodSource("provideLottoAndBonusMatchResult")
    fun `보너스 번호와 일치하는지 여부를 확인한다`(lotto: Lotto, expected: Boolean) {
        // given
        val winningNumbers = listOf(1, 2, 3, 4, 5, 6).map { number -> LottoNumber(number) }.toSet()
        val bonusNumber = LottoNumber(7)
        val winningLotto = WinningLotto(Lotto(winningNumbers), bonusNumber)
        val lottoStatistics = LottoStatistics(winningLotto)

        val method: Method? = lottoStatistics.javaClass.declaredMethods.find {
            it.name == "compareBonusNumber"
        }
        method?.isAccessible = true

        // when
        val actual = method?.invoke(lottoStatistics, lotto)

        // then
        assertThat(actual).isEqualTo(expected)
    }

    @ParameterizedTest(name = "{1}인 경우")
    @MethodSource("provideLottoAndRankMatchResult")
    fun `단일 로또를 넘겨 받아서 당첨 결과를 반환한다`(lotto: Lotto, expected: Rank) {
        // given
        val winningNumbers = listOf(1, 2, 3, 4, 5, 6).map { number -> LottoNumber(number) }.toSet()
        val bonusNumber = LottoNumber(7)
        val winningLotto = WinningLotto(Lotto(winningNumbers), bonusNumber)
        val lottoStatistics = LottoStatistics(winningLotto)

        val method: Method? = lottoStatistics.javaClass.declaredMethods.find {
            it.name == "compare"
        }
        method?.isAccessible = true

        // when
        val actual = method?.invoke(lottoStatistics, lotto)

        // then
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `Ticket을 넘겨 받아서 당첨 번호와 비교한다`() {
        val ticket = Ticket(
            listOf(
                Lotto(listOf(1, 2, 3, 4, 5, 6).map { number -> LottoNumber(number) }.toSet()),
                Lotto(listOf(1, 2, 3, 4, 5, 6).map { number -> LottoNumber(number) }.toSet()),
                Lotto(listOf(1, 2, 3, 4, 5, 13).map { number -> LottoNumber(number) }.toSet()),
                Lotto(listOf(1, 2, 3, 4, 5, 9).map { number -> LottoNumber(number) }.toSet()),
            )
        )
        val winningNumber = listOf(1, 2, 3, 4, 5, 6).map { number -> LottoNumber(number) }.toSet()
        val bonusNumber = LottoNumber(13)
        val winningLotto = WinningLotto(Lotto(winningNumber), bonusNumber)
        val lottoStatistics = LottoStatistics(winningLotto)
        val result: List<Int> = lottoStatistics.compareTicket(ticket)

        assertThat(result[0]).isEqualTo(2)
        assertThat(result[1]).isEqualTo(1)
        assertThat(result[2]).isEqualTo(1)
    }

    @Test
    fun `총 수익률을 계산한다`() {
        val winResult = listOf(0, 0, 0, 0, 1, 13)
        val winningNumber = listOf(1, 2, 3, 4, 5, 6).map { number -> LottoNumber(number) }.toSet()
        val bonusNumber = LottoNumber(13)
        val winningLotto = WinningLotto(Lotto(winningNumber), bonusNumber)
        val lottoStatistics = LottoStatistics(winningLotto)
        val result = lottoStatistics.yield(winResult)
        val expected = "0.35"
        assertThat(result).isEqualTo(expected)
    }

    companion object {
        @JvmStatic
        fun provideLottoAndMatchCount(): Stream<Arguments> {
            return Stream.of(
                Arguments.arguments(Lotto(listOf(1, 2, 3, 4, 5, 6).map { number -> LottoNumber(number) }.toSet()), 6),
                Arguments.arguments(Lotto(listOf(1, 2, 3, 4, 5, 7).map { number -> LottoNumber(number) }.toSet()), 5),
                Arguments.arguments(Lotto(listOf(1, 2, 3, 4, 8, 7).map { number -> LottoNumber(number) }.toSet()), 4),
                Arguments.arguments(Lotto(listOf(1, 2, 3, 9, 8, 7).map { number -> LottoNumber(number) }.toSet()), 3),
            )
        }

        @JvmStatic
        fun provideLottoAndBonusMatchResult(): Stream<Arguments> {
            return Stream.of(
                Arguments.arguments(
                    Lotto(listOf(1, 2, 3, 4, 5, 6).map { number -> LottoNumber(number) }.toSet()),
                    false
                ),
                Arguments.arguments(Lotto(listOf(1, 2, 3, 4, 5, 7).map { number -> LottoNumber(number) }.toSet()), true)
            )
        }

        @JvmStatic
        fun provideLottoAndRankMatchResult(): Stream<Arguments> {
            return Stream.of(
                Arguments.arguments(
                    Lotto(listOf(1, 2, 3, 4, 5, 6).map { number -> LottoNumber(number) }.toSet()),
                    Rank.FIRST
                ),
                Arguments.arguments(
                    Lotto(listOf(1, 2, 3, 4, 5, 7).map { number -> LottoNumber(number) }.toSet()),
                    Rank.SECOND
                ),
                Arguments.arguments(
                    Lotto(listOf(1, 2, 3, 4, 5, 10).map { number -> LottoNumber(number) }.toSet()),
                    Rank.THIRD
                ),
                Arguments.arguments(
                    Lotto(listOf(1, 2, 3, 4, 10, 11).map { number -> LottoNumber(number) }.toSet()),
                    Rank.FOURTH
                ),
                Arguments.arguments(
                    Lotto(listOf(1, 2, 3, 11, 12, 45).map { number -> LottoNumber(number) }.toSet()),
                    Rank.FIFTH
                ),
                Arguments.arguments(
                    Lotto(listOf(1, 2, 11, 12, 15, 45).map { number -> LottoNumber(number) }.toSet()),
                    Rank.MISS
                ),
            )
        }
    }
}
