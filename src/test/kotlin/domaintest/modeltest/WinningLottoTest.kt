package domaintest.modeltest

import domain.model.WinningLotto
import domain.model.lotto.Lotto
import domain.model.lotto.LottoNumber
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows

class WinningLottoTest {

    @Test
    fun `당첨 로또가 가지고 있는 로또번호와 보너스 번호가 겹지지 않은 경우`() {
        val catchLotto = Lotto(
            setOf(
                LottoNumber.from(1),
                LottoNumber.from(2),
                LottoNumber.from(3),
                LottoNumber.from(4),
                LottoNumber.from(5),
                LottoNumber.from(6)
            )
        )
        val bonusNumber = LottoNumber.from(7)

        assertDoesNotThrow {
            WinningLotto(catchLotto, bonusNumber)
        }
    }

    @Test
    fun `당첨 로또가 가지고 있는 로또번호와 보너스 번호가 겹친 경우 예외가 발생`() {
        val catchLotto = Lotto(
            setOf(
                LottoNumber.from(1),
                LottoNumber.from(2),
                LottoNumber.from(3),
                LottoNumber.from(4),
                LottoNumber.from(5),
                LottoNumber.from(6)
            )
        )
        val bonusNumber = LottoNumber.from(5)

        assertThrows<IllegalArgumentException> {
            WinningLotto(catchLotto, bonusNumber)
        }
    }
}
