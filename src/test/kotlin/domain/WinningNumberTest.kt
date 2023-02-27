package domain

import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class WinningNumberTest {
    @ParameterizedTest
    @CsvSource("1,2,3,4,5,6")
    fun `보너스 번호가 당첨 번호에 포함 될 수 없다`(number: Int) {
        val lottoNumbers = Lotto(1, 2, 3, 4, 5, 6)
        val bonusNumber = LottoNumber(number)
        assertThrows<IllegalArgumentException> { WinningNumber(lottoNumbers, bonusNumber) }
    }

    @ParameterizedTest
    @CsvSource("1,2,3,4,5,6")
    fun `당첨 번호는 6개이다`(number: Int) {
        val lottoNumbers = Lotto(1, 2, 3, 4, 5, 6)
        val bonusNumber = LottoNumber(number)
        assertThrows<IllegalArgumentException> { WinningNumber(lottoNumbers, bonusNumber) }
    }
}
