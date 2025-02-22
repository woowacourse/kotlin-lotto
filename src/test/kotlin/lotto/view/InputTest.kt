package lotto.view

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class InputTest {
    @ParameterizedTest
    @ValueSource(strings = [""])
    fun `구입금액에 공백이 들어오면 예외를 발생시킨다`(input: String) {
        assertThrows<IllegalArgumentException> {
            InputValidator().validateAmount(input)
        }
    }

    @ParameterizedTest
    @ValueSource(strings = ["가나다라", "a0"])
    fun `구입금액에 문자열이 들어오면 예외를 발생시킨다`(input: String) {
        assertThrows<IllegalArgumentException> {
            InputValidator().validateAmount(input)
        }
    }

    @ParameterizedTest
    @ValueSource(strings = ["0", "-1"])
    fun `구입금액에 0이나 음수가 들어오면 예외를 발생시킨다`(input: String) {
        assertThrows<IllegalArgumentException> {
            InputValidator().validateAmount(input)
        }
    }

    @ParameterizedTest
    @ValueSource(strings = ["500", "1234"])
    fun `구입금액이 1000으로 나누어 떨어지지 않으면 예외를 발생시킨다`(input: String) {
        assertThrows<IllegalArgumentException> {
            InputValidator().validateAmount(input)
        }
    }

    @Test
    fun `지난주 당첨 번호에 문자열이 들어오면 예외를 발생시킨다`() {
        val winningNumbers = listOf("", "a", "가나다")

        assertThrows<IllegalArgumentException> {
            InputValidator().validateWinningNumbers(winningNumbers)
        }
    }
}
