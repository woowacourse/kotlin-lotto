package lotto.view

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class InputTest {
    private val inputView = InputView()

    @ParameterizedTest
    @ValueSource(strings = [""])
    fun `구입금액에 공백이 들어오면 예외를 발생시킨다`(input: String) {
        assertThrows<IllegalArgumentException> {
            inputView.validateAmount(input)
        }
    }

    @ParameterizedTest
    @ValueSource(strings = ["가나다라", "a0"])
    fun `구입금액에 문자열이 들어오면 예외를 발생시킨다`(input: String) {
        assertThrows<IllegalArgumentException> {
            inputView.validateAmount(input)
        }
    }

    @ParameterizedTest
    @ValueSource(strings = [" ", "", "a1", "A"])
    fun `수동 구매 개수에 정수 이외의 값이 들어오면 예외를 발생시킨다`(input: String) {
        assertThrows<IllegalArgumentException> {
            inputView.validateManualCount(input)
        }
    }

    @ParameterizedTest
    @ValueSource(strings = ["-1", "-99", "-99999"])
    fun `수동 구매 개수에 0미만의 값이 들어오면 예외를 발생시킨다`(input: String) {
        assertThrows<IllegalArgumentException> {
            inputView.validateManualCount(input)
        }
    }

    @Test
    fun `수동 구매 번호에 정수 이외의 값이 들어오면 예외를 발생시킨다`() {
        val winningNumbers = listOf("1", "2", "a", "4", "5", "b")
        assertThrows<IllegalArgumentException> {
            inputView.validateManualNumbers(winningNumbers)
        }
    }

    @ParameterizedTest
    @ValueSource(strings = ["500", "0", "-1"])
    fun `구입금액에 1000원 미만이 들어오면 예외를 발생시킨다`(input: String) {
        assertThrows<IllegalArgumentException> {
            inputView.validateAmount(input)
        }
    }

    @Test
    fun `당첨 번호가 6개가 아니면 예외를 발생시킨다`() {
        val winningNumbers = listOf("1", "2", "3", "4")
        assertThrows<IllegalArgumentException> {
            inputView.validateWinningNumbers(winningNumbers)
        }
    }

    @Test
    fun `당첨 번호에 문자열이 들어오면 예외를 발생시킨다`() {
        val winningNumbers = listOf("", "a", "가나다")
        assertThrows<IllegalArgumentException> {
            inputView.validateWinningNumbers(winningNumbers)
        }
    }

    @Test
    fun `당첨 번호가 서로 중복되면 예외를 발생시킨다`() {
        val winningNumbers = listOf("1", "2", "3", "4", "5", "5")
        assertThrows<IllegalArgumentException> {
            inputView.validateWinningNumbers(winningNumbers)
        }
    }

    @ParameterizedTest
    @ValueSource(strings = ["", " ", "a", "1a"])
    fun `보너스 번호에 정수 이외의 값이 들어오면 예외를 발생시킨다`(input: String) {
        assertThrows<IllegalArgumentException> {
            inputView.validateBonusNumber(input)
        }
    }
}
