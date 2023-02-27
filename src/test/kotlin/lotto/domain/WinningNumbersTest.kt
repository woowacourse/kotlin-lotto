package lotto.domain

import lotto.domain.model.Lotto
import lotto.domain.model.LottoNumber
import lotto.domain.model.WinningNumbers
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class WinningNumbersTest {
    @Test
    fun `입력받은 당첨번호와 보너스 번호가 중복되지 않는다`() {
        assertThrows<IllegalArgumentException> {
            WinningNumbers(
                makeLotto(listOf(1, 2, 3, 4, 5, 6)),
                LottoNumber.from(5)
            )
        }
    }

    private fun makeLotto(numbers: List<Int>): Lotto {
        return Lotto(numbers.map { LottoNumber.from(it) }.toSet())
    }
}
