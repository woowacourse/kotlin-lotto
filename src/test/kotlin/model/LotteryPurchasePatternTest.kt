package model

import io.kotest.matchers.shouldBe
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class LotteryPurchasePatternTest {
    @ParameterizedTest
    @CsvSource("5000, 6", "10000, 11")
    fun `금액보다 더 많은 수동 로또 카운트를 입력 시 예외 발생`(
        amountInput: String,
        manualCountInput: String,
    ) {
        val amount = Amount.fromInput(amountInput)
        assertThatThrownBy {
            LotteryPurchasePattern.ofManual(amount, manualCountInput)
        }.isExactlyInstanceOf(IllegalArgumentException::class.java)
            .hasMessage(LotteryPurchasePattern.EXCEPTION_INSUFFICIENT_FUNDS)
    }

    @ParameterizedTest
    @CsvSource("5000, 5, 0, 5", "10000, 9, 1, 9", "10000, 0, 10, 0")
    fun `금액보다 작거나 같은 수동 로또 카운트를 입력하면 성공`(
        amountInput: String,
        manualCountInput: String,
        autoCount: Int,
        manualCount: Int,
    ) {
        val amount = Amount.fromInput(amountInput)
        val lotteryPurchasePattern = LotteryPurchasePattern.ofManual(amount, manualCountInput)

        lotteryPurchasePattern.autoLottoCount shouldBe autoCount
        lotteryPurchasePattern.manualLottoCount shouldBe manualCount
    }
}
