package model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class LottoDrawingMachineTest {

    private val lottoDrawingMachine = LottoDrawingMachine()
    private val targetLotto = Lotto(listOf(1, 2, 3, 4, 5, 6).map { LottoNumber(it) })

    @ParameterizedTest
    @MethodSource("lottoNumbers, bonusNumber, expectedRank")
    fun `로또 추첨 결과에 대한 당첨 통계를 반환한다`(lottoNumbers: List<Int>, bonusNumber: LottoNumber, expectedRank: Rank) {
        val winningLotto = Lotto(lottoNumbers.map { LottoNumber(it) })
        val expected = LottoDrawingResult(mapOf(expectedRank to 1))
        val actual = lottoDrawingMachine.countRank(listOf(targetLotto), winningLotto, bonusNumber)

        assertThat(actual).isEqualTo(expected)
    }

    companion object {
        @JvmStatic
        fun `lottoNumbers, bonusNumber, expectedRank`(): Stream<Arguments> {
            return Stream.of(
                Arguments.arguments(listOf(1, 2, 3, 4, 5, 6), LottoNumber(1), Rank.FIRST),
                Arguments.arguments(listOf(2, 3, 4, 5, 6, 7), LottoNumber(1), Rank.SECOND),
                Arguments.arguments(listOf(2, 3, 4, 5, 6, 7), LottoNumber(8), Rank.THIRD),
                Arguments.arguments(listOf(3, 4, 5, 6, 7, 8), LottoNumber(1), Rank.FOURTH),
                Arguments.arguments(listOf(4, 5, 6, 7, 8, 9), LottoNumber(1), Rank.FIFTH),
                Arguments.arguments(listOf(5, 6, 7, 8, 9, 10), LottoNumber(1), Rank.MISS),
                Arguments.arguments(listOf(6, 7, 8, 9, 10, 11), LottoNumber(1), Rank.MISS),
                Arguments.arguments(listOf(7, 8, 9, 10, 11, 12), LottoNumber(1), Rank.MISS)
            )
        }
    }
}
