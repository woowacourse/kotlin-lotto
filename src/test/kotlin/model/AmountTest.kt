package model

import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class AmountTest {
    @ParameterizedTest
    @ValueSource(strings = ["500", "1200", "3500", "5700"])
    fun `금액이 천원 단위가 아니라면 예외 발생`(input: String) {
        assertThrows<IllegalArgumentException> {
            Amount(input)
        }
    }

    @ParameterizedTest
    @ValueSource(strings = ["hannah", "pangtae", "23pobi", "@-01af+"])
    fun `금액이 숫자가 아니라면 예외 발생`(input: String) {
        assertThatThrownBy {
            Amount(input)
        }.isExactlyInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("숫자만 입력하셔야 합니다")
    }

    @ParameterizedTest
    @ValueSource(strings = ["-1000", "0"])
    fun `금액이 양수가 아니라면 예외 발생`(input: String) {
        assertThatThrownBy {
            Amount(input)
        }.isExactlyInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("양수를 입력하셔야 합니다")
    }
}
