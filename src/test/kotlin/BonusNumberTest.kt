import domain.BonusNumber
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class BonusNumberTest {

    @Test
    fun `보너스 번호가 생성되었는지 확인`() {
        val bonusNumber = BonusNumber(3)
        assertThat(bonusNumber.number).isEqualTo(3)
    }

    @Test
    fun `보너스 번호가 null일 경우`() {
        assertThrows<IllegalArgumentException> {
            BonusNumber(null)
        }
    }
}
