package lotto

import org.assertj.core.api.Assertions.assertThatIllegalArgumentException
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class BonusNumberTest {
    private lateinit var winningNumber: WinningNumber

    @BeforeEach
    fun setUp() {
        winningNumber = WinningNumber(listOf(1, 2, 3, 4, 5, 6))
    }

    @Test
    fun `보너스 번호가 당첨 번호와 중복되지 않고, 1 ~ 45 사이의 수일 경우 예외를 발생하지 않는다`() {
        assertDoesNotThrow {
            BonusNumber(winningNumber, 13)
        }
    }

    @Test
    fun `보너스 번호가 당첨 번호와 중복될 경우 예외를 발생한다`() {
        assertThatIllegalArgumentException()
            .isThrownBy { BonusNumber(winningNumber, 1) }
            .withMessage("당첨 번호와 중복된 숫자가 존재합니다.")
    }

    @ParameterizedTest
    @ValueSource(ints = [0, 46, -15])
    fun `보너스 번호의 숫자가  1 ~ 45 숫자를 벗어날 경우 예외를 발생한다`(bonusNumber: Int) {
        assertThatIllegalArgumentException()
            .isThrownBy { BonusNumber(winningNumber, bonusNumber) }
            .withMessage("보너스 번호는 1 ~ 45 사이의 숫자입니다.")
    }
}
