import domain.LottoNumber
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class LottoNumberTest {
    @ValueSource(ints = [0, 1, 45, 46])
    @ParameterizedTest
    fun `로또 숫자는 1에서 45 사이 이외의 숫자로 생성될 시 오류가 발생한다`(number: Int) {
        assertThrows<IllegalArgumentException> { LottoNumber.from(number) }
    }
}
