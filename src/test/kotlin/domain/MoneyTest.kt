package domain

import io.kotest.matchers.throwable.shouldHaveMessage
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import util.PREFIX

class MoneyTest {
    @ValueSource(ints = [-1000, -1, -15000, -1234])
    @ParameterizedTest
    fun `금액은 음수일 수 없다`(receivedMoney: Int) {
        // given

        // when

        // then
        assertThrows<IllegalArgumentException> { Money(receivedMoney) }
            .shouldHaveMessage("$PREFIX 돈이 음수일 수 없습니다. 들어온 돈은 ${receivedMoney}입니다")
    }
}
