package lotto.domain

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class WinningNumbersTest {
    @Test
    fun `입력받은 당첨번호와 보너스 번호가 중복되지 않는다`() {
        assertThrows<IllegalArgumentException> {
            WinningNumbers(
                Lotto(
                    listOf(
                        LottoNumber(1),
                        LottoNumber(2),
                        LottoNumber(3),
                        LottoNumber(4),
                        LottoNumber(5),
                        LottoNumber(6)
                    )
                ), LottoNumber(5)
            )
        }
    }
}