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
                        LottoNumber.from(1),
                        LottoNumber.from(2),
                        LottoNumber.from(3),
                        LottoNumber.from(4),
                        LottoNumber.from(5),
                        LottoNumber.from(6)
                    )
                ),
                LottoNumber.from(5)
            )
        }
    }
}
