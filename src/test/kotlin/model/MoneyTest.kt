package model

import model.Money.Companion.INVALID_UNIT_EXCEPTION_MESSAGE
import model.Money.Companion.MINIMUM_VALUE_EXCEPTION_MESSAGE
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class MoneyTest {

    @ParameterizedTest
    @ValueSource(longs = [0, 999])
    fun `구입 금액이 1000원 미만이면 예외를 던진다`(value: Long) {
        val exception = assertThrows<IllegalArgumentException> { Money(value) }

        assertThat(exception.message).isEqualTo(MINIMUM_VALUE_EXCEPTION_MESSAGE)
    }

    @Test
    fun `구입 금액이 1000원 단위가 아니면 예외를 던진다 `() {
        val exception = assertThrows<IllegalArgumentException> { Money(1234) }

        assertThat(exception.message).isEqualTo(INVALID_UNIT_EXCEPTION_MESSAGE)
    }
}
