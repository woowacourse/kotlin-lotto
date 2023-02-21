import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class WinningNumberTest {
    @ParameterizedTest
    @CsvSource("1,2,3,4,5,6")
    fun `보너스 번호가 당첨 번호에 포함 될 수 없다`(number: Int) {
        val lottoNumbers = Lotto(
            listOf(
                LottoNumber(1),
                LottoNumber(2),
                LottoNumber(3),
                LottoNumber(4),
                LottoNumber(5),
                LottoNumber(6),
            ),
        )
        val bonusNumber = LottoNumber(number)
        assertThrows<IllegalArgumentException> { WinningNumber(lottoNumbers, bonusNumber) }
    }

    @ParameterizedTest
    @CsvSource("1,2,3,4,5,6")
    fun `당첨 번호는 6개이다`(number: Int) {
        val lottoNumbers = Lotto(
            listOf(
                LottoNumber(1),
                LottoNumber(2),
                LottoNumber(3),
                LottoNumber(4),
                LottoNumber(5),
                LottoNumber(6),
            ),
        )
        val bonusNumber = LottoNumber(number)
        assertThrows<IllegalArgumentException> { WinningNumber(lottoNumbers, bonusNumber) }
    }
}
