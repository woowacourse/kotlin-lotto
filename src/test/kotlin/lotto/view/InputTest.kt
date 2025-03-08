package lotto.view

import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class InputTest {
    @ParameterizedTest
    @ValueSource(strings = [""])
    fun `구입금액에 공백이 들어오면 예외를 발생시킨다`(input: String) {
        assertThrows<IllegalArgumentException> {
            InputValidator().validateInteger(input)
        }
    }

    @ParameterizedTest
    @ValueSource(strings = ["가나다라", "a0"])
    fun `구입금액에 문자열이 들어오면 예외를 발생시킨다`(input: String) {
        assertThrows<IllegalArgumentException> {
            InputValidator().validateInteger(input)
        }
    }

    @ParameterizedTest
    @ValueSource(strings = ["0", "-1"])
    fun `구입금액에 0이나 음수가 들어오면 예외를 발생시킨다`(input: String) {
        assertThrows<IllegalArgumentException> {
            InputValidator().validateOverZero(input)
        }
    }
}
