package lotto.model

import lotto.domain.model.LottoNumber
import lotto.domain.model.LottoTicket
import lotto.domain.model.WinningLotto
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class LottoTicketTest {
    private fun lotto(vararg numbers: Int): Set<LottoNumber> = numbers.map { LottoNumber(it) }.toSet()

    @Test
    fun `보너스 번호가 당첨 번호와 중복되면 예외를 발생시킨다`() {
        val winningNumbers = lotto(1, 2, 3, 4, 5, 6)
        val bonusNumber = 6

        assertThrows<IllegalArgumentException> {
            WinningLotto(winningNumbers, LottoNumber(bonusNumber))
        }
    }

    @Test
    fun `로또 번호가 서로 중복되면 예외를 발생시킨다`() {
        val lottoNumbers =
            lotto(1, 2, 3, 4, 5, 5)

        assertThrows<IllegalArgumentException> {
            LottoTicket(lottoNumbers)
        }
    }
}
