package lotto

import lotto.constants.LottoPrize
import lotto.model.Lotto
import lotto.model.LottoNumber
import lotto.model.LottoStore
import lotto.model.PurchaseInfo
import lotto.model.WinningLotto
import lotto.service.LottoNumberGenerator
import lotto.service.ResultCalculator
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class ServiceTest {
    @ParameterizedTest
    @MethodSource("로또 당첨 결과 테스트 데이터")
    private fun `로또 당첨 결과의 개수를 확인한다`(
        winningLottoNumbers: List<Int>,
        bonusNumber: Int,
        expected: LottoPrize,
    ) {
        // given
        val purchaseInfo = PurchaseInfo(5000)
        val lottoStore =
            LottoStore.create(
                purchaseInfo,
                object : LottoNumberGenerator {
                    override fun generate() = listOf(1, 2, 3, 4, 5, 6)
                },
            )
        val winningLotto = WinningLotto(Lotto(winningLottoNumbers), LottoNumber(bonusNumber))

        // when
        val actual = lottoStore.calculatePrizeCount(winningLotto)

        // then
        assertThat(actual.keys.first()).isEqualTo(expected)
        assertThat(actual.values.first()).isEqualTo(purchaseInfo.amount)
    }

    @ParameterizedTest
    @MethodSource("로또 수익률 계산 테스트 데이터")
    fun `로또 수익률을 계산한다`(
        lottoPrice: Int,
        prizeCount: Map<LottoPrize, Int>,
        profitRatio: Double,
    ) {
        // given
        val purchaseInfo = PurchaseInfo(lottoPrice)

        // when
        val actual = ResultCalculator.calculateProfitRatio(purchaseInfo, prizeCount)

        // then
        assertThat(actual).isEqualTo(profitRatio)
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

        @JvmStatic
        fun `로또 수익률 계산 테스트 데이터`() =
            listOf(
                Arguments.of("14000", mapOf(LottoPrize.FIFTH to 1), 0.35),
                Arguments.of("8000", mapOf(LottoPrize.FIFTH to 1), 0.62),
                Arguments.of("1000", mapOf(LottoPrize.FIRST to 1), 2_000_000),
            )
    }
}
