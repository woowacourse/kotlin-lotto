package lotto.validator

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class InputValidatorTest {
    private val inputValidator = InputValidator()

    @ValueSource(strings = ["1234", "3456", "100"])
    @ParameterizedTest
    fun `구매 가격이 1000 단위가 아니면 IllegalArgumentException이 일어난다`(input: String) {
        assertThrows<IllegalArgumentException> {
            inputValidator.validatePurchaseAmount(input)
        }
    }

    @ValueSource(strings = ["-1", "0", "-123", "안녕하세요"])
    @ParameterizedTest
    fun `구매 가격에 0 이하의 숫자나, 문자가 들어오면 IllegalArgumentException이 일어난다`(input: String) {
        assertThrows<IllegalArgumentException> {
            inputValidator.validatePurchaseAmount(input)
        }
    }

    @Test
    fun `중복된 당첨 번호가 있으면 IllegalArgumentException이 일어난다`() {
        assertThrows<IllegalArgumentException> {
            inputValidator.validateWinningNumber(listOf("1", "1", "2", "3", "4", "5"))
        }
    }

    @ValueSource(strings = ["-1", "0", "-123", "안녕하세요"])
    @ParameterizedTest
    fun `당첨 번호가 0 이하의 숫자나 문자가 들어오면 IllegalArgumentException이 일어난다`(input: String) {
        assertThrows<IllegalArgumentException> {
            inputValidator.validateWinningNumber(listOf(input, "1", "40", "10", "13", "23"))
        }
    }

    @ValueSource(ints = [1, 2, 3, 4, 5])
    @ParameterizedTest
    fun `당첨 번호가 6개가 아니면 IllegalArgumentException이 일어난다`(input: Int) {
        val testList = List<String>(input) { "1" }
        assertThrows<IllegalArgumentException> {
            inputValidator.validateWinningNumber(testList)
        }
    }
}
