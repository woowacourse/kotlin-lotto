package lotto.domain

import lotto.domain.model.Lotto
import lotto.domain.model.LottoDrawingResult
import lotto.domain.model.LottoNumber
import lotto.domain.model.Rank
import lotto.domain.model.WinningLotto
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
    fun `로또 추첨 결과에 대한 당첨 통계를 반환한다`(
        lottoNumbers: List<Int>,
        bonusNumber: LottoNumber,
        expectedResult: LottoDrawingResult
    ) {
        val winningLotto = WinningLotto(Lotto(lottoNumbers.map { LottoNumber(it) }), bonusNumber)
        val actual = lottoDrawingMachine.countRank(listOf(targetLotto), winningLotto)

        assertThat(actual).isEqualTo(expectedResult)
    }

    companion object {
        @JvmStatic
        fun `lottoNumbers, bonusNumber, expectedRank`(): Stream<Arguments> {
            return Stream.of(
                Arguments.arguments(
                    listOf(1, 2, 3, 4, 5, 6),
                    LottoNumber(7),
                    LottoDrawingResult(
                        mapOf(
                            Rank.FIRST to 1,
                            Rank.SECOND to 0,
                            Rank.THIRD to 0,
                            Rank.FOURTH to 0,
                            Rank.FIFTH to 0,
                            Rank.MISS to 0
                        )
                    )
                ),
                Arguments.arguments(
                    listOf(2, 3, 4, 5, 6, 7),
                    LottoNumber(1),
                    LottoDrawingResult(
                        mapOf(
                            Rank.FIRST to 0,
                            Rank.SECOND to 1,
                            Rank.THIRD to 0,
                            Rank.FOURTH to 0,
                            Rank.FIFTH to 0,
                            Rank.MISS to 0
                        )
                    )
                ),
                Arguments.arguments(
                    listOf(2, 3, 4, 5, 6, 7),
                    LottoNumber(8),
                    LottoDrawingResult(
                        mapOf(
                            Rank.FIRST to 0,
                            Rank.SECOND to 0,
                            Rank.THIRD to 1,
                            Rank.FOURTH to 0,
                            Rank.FIFTH to 0,
                            Rank.MISS to 0
                        )
                    )
                ),
                Arguments.arguments(
                    listOf(3, 4, 5, 6, 7, 8),
                    LottoNumber(1),
                    LottoDrawingResult(
                        mapOf(
                            Rank.FIRST to 0,
                            Rank.SECOND to 0,
                            Rank.THIRD to 0,
                            Rank.FOURTH to 1,
                            Rank.FIFTH to 0,
                            Rank.MISS to 0
                        )
                    )
                ),
                Arguments.arguments(
                    listOf(4, 5, 6, 7, 8, 9),
                    LottoNumber(1),
                    LottoDrawingResult(
                        mapOf(
                            Rank.FIRST to 0,
                            Rank.SECOND to 0,
                            Rank.THIRD to 0,
                            Rank.FOURTH to 0,
                            Rank.FIFTH to 1,
                            Rank.MISS to 0
                        )
                    )
                )
            )
        }
    }
}
