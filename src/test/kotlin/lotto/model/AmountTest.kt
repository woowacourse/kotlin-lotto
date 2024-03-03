package lotto.model

import io.kotest.matchers.shouldBe
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.ValueSource

class AmountTest {
    @ParameterizedTest
    @ValueSource(strings = ["500", "600", "700", "0", "-1000"])
    fun `로또 구입 금액이 1000원 미만일 경우 예외 발생`(input: String) {
        assertThatThrownBy {
            Amount.fromInput(input)
        }.isExactlyInstanceOf(IllegalArgumentException::class.java)
            .hasMessage(Amount.EXCEPTION_LESS_THAN_THOUSAND)
    }

    @ParameterizedTest
    @ValueSource(strings = ["hannah", "pangtae", "23pobi", "@-01af+"])
    fun `금액이 숫자가 아니라면 null 반환`(input: String) {
        assertThat(Amount.fromInput(input)).isNull()
    }

    @ParameterizedTest
    @CsvSource("1000, 1000", "1500, 1500", "2000, 2000", "10000, 10000")
    fun `로또 구입 금액이 1000원 이상일 경우 구입 완료`(
        input: String,
        money: Int,
    ) {
        val amount = Amount.fromInput(input)

        if (amount != null) {
            money shouldBe amount.money
        }
    }
}
