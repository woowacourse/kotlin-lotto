package lotto

import org.assertj.core.api.Assertions.assertThatIllegalArgumentException
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class BonusNumberTest {
    @Test
    fun `보너스 번호가 1 ~ 45 사이의 수일 경우 예외를 발생하지 않는다`() {
        assertDoesNotThrow {
            BonusNumber(13)
        }
    }

    @ParameterizedTest
    @ValueSource(ints = [0, 46, -15])
    fun `보너스 번호의 숫자가  1 ~ 45 숫자를 벗어날 경우 예외를 발생한다`(bonusNumber: Int) {
        assertThatIllegalArgumentException()
            .isThrownBy { BonusNumber(bonusNumber) }
            .withMessage("보너스 번호는 1 ~ 45 사이의 숫자입니다.")
    }
}
