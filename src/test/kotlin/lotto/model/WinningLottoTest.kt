package lotto.model

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class WinningLottoTest {
    private val lotto = Lotto.create(listOf(1, 2, 3, 4, 5, 6))

    @Test
    fun `당첨 번호와 보너스 번호가 중복되지 않으면 예외가 발생하지 않는다`() {
        assertDoesNotThrow { WinningLotto(lotto, LottoNumber.from(7)) }
    }

    @Test
    fun `당첨 번호와 보너스 번호가 중복되면 예외가 발생한다`() {
        assertThrows<IllegalArgumentException> { WinningLotto(lotto, LottoNumber.from(6)) }
    }

    @ParameterizedTest
    @MethodSource("로또 당첨 결과 테스트 데이터")
    fun `로또 당첨 결과의 개수를 확인한다`(
        winningLottoNumbers: List<Int>,
        bonusNumber: Int,
        expected: LottoPrize,
    ) {
        // given
        val winningLotto = WinningLotto(Lotto.create(winningLottoNumbers), LottoNumber.from(bonusNumber))
        val lottos = List(5) { Lotto.create(listOf(1, 2, 3, 4, 5, 6)) }

        // when
        val actual = winningLotto.calculateWinningStatistics(lottos)

        // then
        Assertions.assertThat(actual.getPrizeCount(expected)).isEqualTo(5)
    }

    companion object {
        @JvmStatic
        fun `로또 당첨 결과 테스트 데이터`() =
            listOf(
                Arguments.of(listOf(1, 2, 3, 4, 5, 6), 7, LottoPrize.FIRST),
                Arguments.of(listOf(1, 2, 3, 4, 5, 7), 6, LottoPrize.SECOND),
                Arguments.of(listOf(1, 2, 3, 4, 5, 7), 8, LottoPrize.THIRD),
                Arguments.of(listOf(1, 2, 3, 4, 7, 8), 9, LottoPrize.FOURTH),
                Arguments.of(listOf(1, 2, 3, 7, 8, 9), 10, LottoPrize.FIFTH),
                Arguments.of(listOf(10, 11, 12, 13, 14, 15), 16, LottoPrize.NOTHING),
            )
    }
}
