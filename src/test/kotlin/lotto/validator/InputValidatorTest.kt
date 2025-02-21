package lotto.validator

import lotto.domain.Lotto
import lotto.domain.LottoNumber
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class InputValidatorTest {
    @ValueSource(strings = ["1234", "3456", "100"])
    @ParameterizedTest
    fun `구매 가격이 1000 단위가 아니면 IllegalArgumentException이 일어난다`(input: String) {
        assertThrows<IllegalArgumentException> {
            PurchaseAmountValidator(input)
        }
    }

    @ValueSource(strings = ["-1", "0", "-123", "안녕하세요"])
    @ParameterizedTest
    fun `구매 가격에 0 이하의 숫자나, 문자가 들어오면 IllegalArgumentException이 일어난다`(input: String) {
        assertThrows<IllegalArgumentException> {
            PurchaseAmountValidator(input)
        }
    }

    @Test
    fun `보너스 번호는 당첨 번호와 중복되지 않는다`() {
        val bonusNumber = "1"
        val winningNumber = Lotto(intToLottoNumber(listOf(1, 2, 3, 4, 5, 6)))

        assertThrows<IllegalArgumentException> {
            BonusNumberValidator(bonusNumber, winningNumber)
        }
    }

    @ValueSource(strings = ["-1", "안녕", "0", "46"])
    @ParameterizedTest
    fun `보너스 번호는 1~45 사이이다`(input: String) {
        val winningNumber = Lotto(intToLottoNumber(listOf(1, 2, 3, 4, 5, 6)))

        assertThrows<IllegalArgumentException> {
            BonusNumberValidator(input, winningNumber)
        }
    }

    private fun intToLottoNumber(numbers: List<Int>): List<LottoNumber> {
        return numbers.map { LottoNumber(it) }
    }
}
