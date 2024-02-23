import org.junit.jupiter.api.Assertions.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import util.InputValidator

class InputTest {
    @ParameterizedTest
    @ValueSource(strings = ["8000", "1000", "15000"])
    fun `올바른 구입금액 입력`(purchaseAmount: String) {
        assertDoesNotThrow { InputValidator.validatePurchaseAmount(purchaseAmount) }
    }

    @ParameterizedTest
    @ValueSource(strings = ["-1000", "0", "999", " 1000 ", " ", "", "oneThousand"])
    fun `구입금액 입력 예외 처리 (구입 금액이 1,000원 이상의 숫자가 아닌 경우)`(purchaseAmount: String) {
        assertThrows<IllegalArgumentException> { InputValidator.validatePurchaseAmount(purchaseAmount) }
    }

    @ParameterizedTest
    @ValueSource(strings = ["1100", "1010", "1001"])
    fun `구입금액 입력 예외 처리 (구입 금액이 1,000원으로 나누어 떨어지지 않는 경우)`(purchaseAmount: String) {
        assertThrows<IllegalArgumentException> { InputValidator.validatePurchaseAmount(purchaseAmount) }
    }

    @ParameterizedTest
    @ValueSource(strings = ["1,2,3,4,5,6", "23,17,42,31,29,5"])
    fun `올바른 당첨 번호 입력`(winningNumbers: String) {
        assertDoesNotThrow { InputValidator.validateWinningNumbers(winningNumbers) }
    }

    @ParameterizedTest
    @ValueSource(strings = ["1,2,3,4,5", "1,2,3,4,5,6,7"])
    fun `당첨 번호 입력 예외 처리 (당첨 번호의 개수가 6개가 아닌 경우)`(winningNumbers: String) {
        assertThrows<IllegalArgumentException> { InputValidator.validateWinningNumbers(winningNumbers) }
    }

    @ParameterizedTest
    @ValueSource(strings = ["0,1,2,3,4,5", "-1,2,3,4,5,6", "1,2,3,4,5,46", " 1,2,3,4,5,6", "1,2,3,4,5,6 ", " ,2,3,4,5,6", "one,2,3,4,5,6"])
    fun `당첨 번호 입력 예외 처리 (당첨 번호가 1~45사이의 숫자가 아닌 경우)`(winningNumbers: String) {
        assertThrows<IllegalArgumentException> { InputValidator.validateWinningNumbers(winningNumbers) }
    }

    @ParameterizedTest
    @ValueSource(strings = ["1,2,3,4,5,5", "1,1,3,4,5,6"])
    fun `당첨 번호 입력 예외 처리 (당첨 숫자가 중복되는 경우)`(winningNumbers: String) {
        assertThrows<IllegalArgumentException> { InputValidator.validateWinningNumbers(winningNumbers) }
    }

    @ParameterizedTest
    @ValueSource(strings = ["7", "23", "45"])
    fun `올바른 보너스 번호 입력`(bonusNumber: String) {
        val winningNumbers = listOf(1, 2, 3, 4, 5, 6)
        assertDoesNotThrow { InputValidator.validateBonusNumber(bonusNumber, winningNumbers) }
    }

    @ParameterizedTest
    @ValueSource(strings = ["-1", "0", "46", "one", " 1", "1 ", " ", ""])
    fun `보너스 번호 입력 예외 처리 (보너스 번호가 1~45사이의 숫자가 아닌 경우)`(bonusNumber: String) {
        val winningNumbers = listOf(1, 2, 3, 4, 5, 6)
        assertThrows<IllegalArgumentException> { InputValidator.validateBonusNumber(bonusNumber, winningNumbers) }
    }

    @ParameterizedTest
    @ValueSource(strings = ["1", "2", "3", "4", "5", "6"])
    fun `보너스 번호 입력 예외 처리 (보너스 번호가 당첨 숫자와 중복되는 경우)`(bonusNumber: String) {
        val winningNumbers = listOf(1, 2, 3, 4, 5, 6)
        assertThrows<IllegalArgumentException> { InputValidator.validateBonusNumber(bonusNumber, winningNumbers) }
    }
}
