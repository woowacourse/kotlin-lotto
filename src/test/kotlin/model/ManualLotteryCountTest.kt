package model

import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class ManualLotteryCountTest {
    @ParameterizedTest
    @CsvSource("a12, 2000", "@!~, 3000", "few, 4500")
    fun `수동 로또 수가 숫자가 아니라면 예외 발생`(
        input: String,
        money: String,
    ) {
        val amount = Amount.fromInput(money)
        assertThatThrownBy {
            ManualLotteryCount.fromInput(input, amount, LOTTO_TICKET_PRICE)
        }.isExactlyInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("숫자만 입력하셔야 합니다")
    }

    @ParameterizedTest
    @CsvSource("2, 1000", "3, 2300", "5, 3500")
    fun `수동 로또 수가 구입 금액으로 구매 가능한 숫자가 아니라면 예외 발생`(
        input: String,
        money: String,
    ) {
        val amount = Amount.fromInput(money)
        assertThatThrownBy {
            ManualLotteryCount.fromInput(input, amount, LOTTO_TICKET_PRICE)
        }.isExactlyInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("금액이 부족합니다")
    }

    companion object {
        private const val LOTTO_TICKET_PRICE = 1000
    }
}
