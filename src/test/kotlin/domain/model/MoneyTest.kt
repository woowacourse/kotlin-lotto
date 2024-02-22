package domain.model

import domain.model.Money.Companion.MINIMUM_VALUE_EXCEPTION_MESSAGE
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class MoneyTest {

    @ParameterizedTest
    @ValueSource(longs = [-1, -999])
    fun `구입 금액이 0원 미만이면 예외를 던진다`(value: Long) {
        val exception = assertThrows<IllegalArgumentException> { Money(value) }

        assertThat(exception.message).isEqualTo(MINIMUM_VALUE_EXCEPTION_MESSAGE)
    }
}
