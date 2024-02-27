package lotto.model

import lotto.service.LottoNumberGenerator
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class WinningLottoTest {
    @Test
    fun `당첨 번호와 보너스 번호가 중복되면 예외가 발생한다`() {
        org.junit.jupiter.api.assertThrows<IllegalArgumentException> {
            WinningLotto(Lotto(setOf(1, 2, 3, 4, 5, 6)), LottoNumber(6))
        }
    }

    @Test
    fun `당첨 번호와 보너스 번호가 중복되지 않으면 예외가 발생하지 않는다`() {
        org.junit.jupiter.api.assertDoesNotThrow {
            WinningLotto(Lotto(setOf(1, 2, 3, 4, 5, 6)), LottoNumber(7))
        }
    }

    @ParameterizedTest
    @MethodSource("로또 당첨 결과 테스트 데이터")
    fun `로또 당첨 결과의 개수를 확인한다`(
        winningLottoNumbers: Set<Int>,
        bonusNumber: Int,
        expected: LottoPrize,
    ) { // given
        val purchaseInfo = PurchaseInfo("5000")
        val lottoStore =
            LottoStore(
                purchaseInfo,
                object : LottoNumberGenerator {
                    override fun generate() = setOf(1, 2, 3, 4, 5, 6)
                },
            )
        val winningLotto = WinningLotto(Lotto(winningLottoNumbers), LottoNumber(bonusNumber))

        // when
        val actual = winningLotto.calculatePrizeCount(lottoStore)

        // then
        Assertions.assertThat(actual.keys.first()).isEqualTo(expected)
        Assertions.assertThat(actual.values.first()).isEqualTo(purchaseInfo.amount)
    }

    companion object {
        @JvmStatic
        fun `로또 당첨 결과 테스트 데이터`() =
            listOf(
                Arguments.of(setOf(1, 2, 3, 4, 5, 6), 7, LottoPrize.FIRST),
                Arguments.of(setOf(1, 2, 3, 4, 5, 7), 6, LottoPrize.SECOND),
                Arguments.of(setOf(1, 2, 3, 4, 5, 7), 8, LottoPrize.THIRD),
                Arguments.of(setOf(1, 2, 3, 4, 7, 8), 9, LottoPrize.FOURTH),
                Arguments.of(setOf(1, 2, 3, 7, 8, 9), 10, LottoPrize.FIFTH),
                Arguments.of(setOf(10, 11, 12, 13, 14, 15), 16, LottoPrize.NOTHING),
            )
    }
}
