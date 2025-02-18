package lotto

import org.assertj.core.api.Assertions.assertThatIllegalArgumentException
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class WinningNumberTest {
    @Test
    fun `당첨 번호는 1 ~ 45 사이의 6개의 중복되지 않은 숫자다`() {
        // given
        val winningNumber = WinningNumber(listOf(1, 2, 3, 4, 5, 6))
        val expected = 6

        // when
        val actual = winningNumber.numbers.distinct().size

        // then
        Assertions.assertEquals(expected, actual)
    }

    @Test
    fun `당첨 번호는 6개의 숫자가 아닌 경우 예외가 발생한다`() {
        assertThatIllegalArgumentException()
            .isThrownBy { WinningNumber(listOf(1, 2, 3, 4, 5)) }
            .withMessage("당첨 번호는 6개의 숫자입니다.")
    }

    @Test
    fun `당첨 번호에 중복되는 숫자가 있을 경우 예외가 발생한다`() {
        assertThatIllegalArgumentException()
            .isThrownBy { WinningNumber(listOf(1, 2, 3, 4, 5, 5)) }
            .withMessage("중복된 숫자가 존재합니다.")
    }

    @Test
    fun `당첨 번호 중 하나 이상의 숫자가  1 ~ 45 숫자를 벗어날 경우 예외가 발생한다`() {
        assertThatIllegalArgumentException()
            .isThrownBy { WinningNumber(listOf(0, 1, 2, 3, 4, 5)) }
            .withMessage("당첨 번호는 1 ~ 45 사이의 숫자입니다.")
    }
}
