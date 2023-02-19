import domain.BonusNumber
import domain.LottoNumber
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class BonusNumberTest {

    @Test
    fun `보너스 번호 객체가 잘 생성되는지 확인`() {
        val bonusNumber = BonusNumber(LottoNumber.from(3))
        assertThat(bonusNumber.number).isEqualTo(3)
    }

    @ValueSource(ints = [0, 46, -1])
    @ParameterizedTest
    fun `보너스 번호가 1에서 45 사이의 값이 아닌 경우 예외 처리`(number: Int) {
        assertThrows<IllegalArgumentException> {
            BonusNumber(LottoNumber.from(number))
        }
    }
}
