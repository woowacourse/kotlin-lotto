package lotto.validator

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
}
