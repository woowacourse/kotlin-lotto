package lotto.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class TestShuffle : ShuffleStrategy {
    override fun shuffle(list: List<Int>): List<Int> = listOf(45, 40, 35, 30, 25, 20)
}

class LottoMachineTest {
    @Test
    fun `입력된 금액에 맞게 로또를 발행한다`() {
        val amount = Amount(10000)
        val lottoMachine = LottoMachine()
        val lottoTickets = lottoMachine.publishAutoTickets(Amount(LOTTO_PRIZE))
        assertThat(lottoTickets.size).isEqualTo(amount.money / LOTTO_PRIZE)
    }

    @Test
    fun `로또 번호는 랜덤으로 섞인 리스트에서 6개를 뽑아서 지정된다`() {
        val amount = Amount(1000)
        val lottoMachine = LottoMachine(TestShuffle())
        val lottoTickets = lottoMachine.publishAutoTickets(Amount(LOTTO_PRIZE))
        assertThat(lottoTickets[0].getNumbers().map { it.value }).isEqualTo(listOf(20, 25, 30, 35, 40, 45))
    }

    companion object {
        const val LOTTO_PRIZE = 1000
    }
}
