package lotto.model.purchaseinformation

import io.kotest.matchers.shouldBe
import lotto.model.puchaseinformation.PurchaseInformation
import org.assertj.core.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.ValueSource

class PurchaseInformationTest {
    @ParameterizedTest
    @ValueSource(strings = ["500", "600", "700", "0", "-1000"])
    fun `로또 구입 금액이 1000원 미만일 경우 예외 발생`(money: Int) {
        Assertions.assertThatThrownBy {
            PurchaseInformation(money, MIN_MANUAL_LOTTERY_COUNT)
        }.isExactlyInstanceOf(IllegalArgumentException::class.java)
            .hasMessage(EXCEPTION_LESS_THAN_THOUSAND)
    }

    @ParameterizedTest
    @CsvSource("1000, 1000", "1500, 1500", "2000, 2000", "10000, 10000")
    fun `로또 구입 금액이 1000원 이상일 경우 구입 완료`(
        input: Int,
        money: Int,
    ) {
        val amount = PurchaseInformation(input, MIN_MANUAL_LOTTERY_COUNT).amount

        money shouldBe amount
    }

    @ParameterizedTest
    @CsvSource("2, 1000", "3, 2300", "5, 3500")
    fun `수동 로또 수가 구입 금액으로 구매 가능한 숫자가 아니라면 예외 발생`(
        input: Int,
        money: Int,
    ) {
        Assertions.assertThatThrownBy {
            PurchaseInformation(money, input)
        }.isExactlyInstanceOf(IllegalArgumentException::class.java)
            .hasMessage(EXCEPTION_INSUFFICIENT_MONEY)
    }

    companion object {
        private const val MIN_MANUAL_LOTTERY_COUNT = 0
        private const val LOTTERY_TICKET_PRICE = 1000
        private const val EXCEPTION_INSUFFICIENT_MONEY = "금액이 부족합니다"
        private const val EXCEPTION_LESS_THAN_THOUSAND = "로또 구입 금액은 ${LOTTERY_TICKET_PRICE}원 이상이어야 합니다"
    }
}
