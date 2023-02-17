package domain

import common.convertToLottoNumberSet
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
        val bonusNumber = LottoNumber.from(7)
        val winningLotto = WinningLotto(Lotto(winningNumbers.convertToLottoNumberSet()), bonusNumber)
        val lottoStatistics = LottoStatistics(winningLotto)
        val actual = lottoStatistics.matchNumbers(lotto)

        assertThat(actual).isEqualTo(expected)
    }

    @ParameterizedTest(name = "{1}인 경우")
    @MethodSource("provideLottoAndBonusMatchResult")
    fun `보너스 번호와 일치하는지 여부를 확인한다`(lotto: Lotto, expected: Boolean) {
        val winningNumbers = setOf(1, 2, 3, 4, 5, 6)
        val bonusNumber = LottoNumber.from(7)
        val winningLotto = WinningLotto(Lotto(winningNumbers.convertToLottoNumberSet()), bonusNumber)
        val lottoStatistics = LottoStatistics(winningLotto)
        val actual = lottoStatistics.matchBonusNumber(lotto)

        assertThat(actual).isEqualTo(expected)
    }

    @ParameterizedTest(name = "{1}인 경우")
    @MethodSource("provideLottoAndRankMatchResult")
    fun `단일 로또를 넘겨 받아서 당첨 결과를 반환한다`(lotto: Lotto, expected: Rank) {
        val winningNumbers = setOf(1, 2, 3, 4, 5, 6)
        val bonusNumber = LottoNumber.from(7)
        val winningLotto = WinningLotto(Lotto(winningNumbers.convertToLottoNumberSet()), bonusNumber)
        val lottoStatistics = LottoStatistics(winningLotto)
        val actual = lottoStatistics.match(lotto)

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `Ticket을 넘겨 받아서 당첨 번호와 비교한다`() {
        val ticket = Ticket(
            listOf(
                Lotto(setOf(1, 2, 3, 4, 5, 6).convertToLottoNumberSet()),
                Lotto(setOf(1, 2, 3, 4, 5, 6).convertToLottoNumberSet()),
                Lotto(setOf(1, 2, 3, 4, 5, 13).convertToLottoNumberSet()),
                Lotto(setOf(1, 2, 3, 4, 5, 9).convertToLottoNumberSet()),
            )
        )
        val winningNumber = setOf(1, 2, 3, 4, 5, 6)
        val bonusNumber = LottoNumber.from(13)
        val winningLotto = WinningLotto(Lotto(winningNumber.convertToLottoNumberSet()), bonusNumber)
        val lottoStatistics = LottoStatistics(winningLotto)
        val result: Map<Rank, Int> = lottoStatistics.matchTicket(ticket)

        assertThat(result[Rank.FIRST]).isEqualTo(2)
        assertThat(result[Rank.SECOND]).isEqualTo(1)
        assertThat(result[Rank.THIRD]).isEqualTo(1)
    }

    @Test
    fun `총 수익률을 계산한다`() {
        val winResult: MutableMap<Rank, Int> = Rank.values().associateWith { 0 }.toMutableMap()
        winResult[Rank.FIRST] = 0
        winResult[Rank.SECOND] = 0
        winResult[Rank.THIRD] = 0
        winResult[Rank.FOURTH] = 0
        winResult[Rank.FIFTH] = 1
        winResult[Rank.MISS] = 13

        val winningNumber = setOf(1, 2, 3, 4, 5, 6)
        val bonusNumber = LottoNumber.from(13)
        val winningLotto = WinningLotto(Lotto(winningNumber.convertToLottoNumberSet()), bonusNumber)
        val lottoStatistics = LottoStatistics(winningLotto)
        val result = lottoStatistics.yield(winResult)
        val expected = "0.35"
        assertThat(result).isEqualTo(expected)
    }

    companion object {
        @JvmStatic
        fun provideLottoAndMatchCount(): Stream<Arguments> {
            return Stream.of(
                Arguments.arguments(Lotto(setOf(1, 2, 3, 4, 5, 6).convertToLottoNumberSet()), 6),
                Arguments.arguments(Lotto(setOf(1, 2, 3, 4, 5, 7).convertToLottoNumberSet()), 5),
                Arguments.arguments(Lotto(setOf(1, 2, 3, 4, 8, 7).convertToLottoNumberSet()), 4),
                Arguments.arguments(Lotto(setOf(1, 2, 3, 9, 8, 7).convertToLottoNumberSet()), 3),
            )
        }

        @JvmStatic
        fun provideLottoAndBonusMatchResult(): Stream<Arguments> {
            return Stream.of(
                Arguments.arguments(Lotto(setOf(1, 2, 3, 4, 5, 6).convertToLottoNumberSet()), false),
                Arguments.arguments(Lotto(setOf(1, 2, 3, 4, 5, 7).convertToLottoNumberSet()), true)
            )
        }

        @JvmStatic
        fun provideLottoAndRankMatchResult(): Stream<Arguments> {
            return Stream.of(
                Arguments.arguments(Lotto(setOf(1, 2, 3, 4, 5, 6).convertToLottoNumberSet()), Rank.FIRST),
                Arguments.arguments(Lotto(setOf(1, 2, 3, 4, 5, 7).convertToLottoNumberSet()), Rank.SECOND),
                Arguments.arguments(Lotto(setOf(1, 2, 3, 4, 5, 10).convertToLottoNumberSet()), Rank.THIRD),
                Arguments.arguments(Lotto(setOf(1, 2, 3, 4, 10, 11).convertToLottoNumberSet()), Rank.FOURTH),
                Arguments.arguments(Lotto(setOf(1, 2, 3, 11, 12, 45).convertToLottoNumberSet()), Rank.FIFTH),
                Arguments.arguments(Lotto(setOf(1, 2, 11, 12, 15, 45).convertToLottoNumberSet()), Rank.MISS),
            )
        }
    }
}
