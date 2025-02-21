package lotto.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class LottoMachineTest {
    @Test
    fun `구입 금액이 로또 가격의 배수가 아닌 경우 예외가 발생한다`() {
        val amount = Amount(9999)
        val exception =
            assertThrows<IllegalArgumentException> {
                LottoMachine(amount, Amount(1000))
            }
        assertThat(exception.message).isEqualTo("[ERROR] 구입 금액은 로또 가격의 배수여야 합니다.")
    }

    @Test
    fun `구입 금액이 로또 가격 미만인 경우 예외가 발생한다`() {
        val amount = Amount(999)
        val exception =
            assertThrows<IllegalArgumentException> {
                LottoMachine(amount, Amount(1000))
            }
        assertThat(exception.message).isEqualTo("[ERROR] 구입 금액이 최소 금액보다 작습니다.")
    }

    @Test
    fun `입력된 금액에 맞게 로또를 발행한다`() {
        val amount = Amount(10000)
        val lottoMachine = LottoMachine(amount, Amount(1000))
        val lottoTickets = lottoMachine.publishLottoTickets()
        assertThat(lottoTickets.size).isEqualTo(amount.money / 1000)
    }
}
