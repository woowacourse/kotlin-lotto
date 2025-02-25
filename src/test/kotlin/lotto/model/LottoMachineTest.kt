package lotto.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class TestShuffle : ShuffleStrategy {
    override fun shuffle(list: List<Int>): List<Int> = listOf(45, 40, 35, 30, 25, 20)
}

class LottoMachineTest {
    @Test
    fun `입력된 금액에 맞게 로또를 발행한다`() {
        val amount = Amount(10000)
        val lottoMachine = LottoMachine(amount)
        val lottoTickets = lottoMachine.publishAutoTickets(Amount(LOTTO_PRIZE))
        assertThat(lottoTickets.size).isEqualTo(amount.money / LOTTO_PRIZE)
    }

    @Test
    fun `입력된 금액보다 수동으로 구매 할 로또 총액이 크면 예외처리한다`() {
        val amount = Amount(10000)
        val lottoMachine = LottoMachine(amount)
        val exception = assertThrows<IllegalArgumentException> { lottoMachine.useMoney(Amount(100000)) }
        assertThat(exception.message).isEqualTo("[ERROR] 입력된 금액보다 더 많은 수량의 로또는 살 수 없습니다.")
    }

    @Test
    fun `유저가 입력한 숫자를 가진 로또를 발행한다`() {
        val amount = Amount(10000)
        val lottoMachine = LottoMachine(amount)
        val lottoList =
            lottoMachine.publishManualLottoList(
                listOf(listOf(1, 2, 3, 4, 5, 6), listOf(2, 3, 4, 5, 6, 7), listOf(3, 4, 5, 6, 7, 8)),
            )
        assertThat(lottoList.size).isEqualTo(3)
        assertThat(lottoList[0].getNumbers()).isEqualTo(Lotto(listOf(1, 2, 3, 4, 5, 6)).getNumbers())
    }

    @Test
    fun `로또 번호는 랜덤으로 섞인 리스트에서 6개를 뽑아서 지정된다`() {
        val amount = Amount(1000)
        val lottoMachine = LottoMachine(amount, TestShuffle())
        val lottoTickets = lottoMachine.publishAutoTickets(Amount(LOTTO_PRIZE))
        assertThat(lottoTickets[0].getNumbers().map { it.value }).isEqualTo(listOf(20, 25, 30, 35, 40, 45))
    }

    fun Lotto(numbers: List<Int>): Lotto = Lotto(LottoNumbers(numbers.map { number -> LottoNumber(number) }))

    companion object {
        const val LOTTO_PRIZE = 1000
    }
}
