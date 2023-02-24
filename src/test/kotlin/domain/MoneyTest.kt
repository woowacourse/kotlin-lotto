package domain

import io.kotest.matchers.throwable.shouldHaveMessage
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import util.PREFIX

class MoneyTest {
    @ValueSource(doubles = [-1000.0, -1.0, -15000.0, -1234.0])
    @ParameterizedTest
    fun `금액은 음수일 수 없다`(receivedMoney: Double) {
        // given

        // when

        // then
        assertThrows<IllegalArgumentException> { Money(receivedMoney) }
            .shouldHaveMessage("$PREFIX 돈이 음수일 수 없습니다. 들어온 돈은 ${receivedMoney}입니다")
    }
}
