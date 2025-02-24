package lotto.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class LottoMachineTest {
    @Test
    fun `주문서를 주면 자동으로 구입할 로또와 수동으로 구입한 로또를 합쳐서 반환한다`() {
        val purchaseAmount = LottoPurchaseAmount(2000, 1000)
        val manualLottoCount = ManualLottoCount(1, 2)
        val manualLottoNumber: List<Lotto> =
            listOf(Lotto(listOf(LottoNumber(1), LottoNumber(2), LottoNumber(3), LottoNumber(4), LottoNumber(5), LottoNumber(6))))

        val orderSheet = OrderSheet(purchaseAmount, manualLottoCount, manualLottoNumber)
        val lottoMachine = LottoMachine()
        assertThat(lottoMachine.buyLottos(orderSheet).size).isEqualTo(2)
    }

    @Test
    fun `수동으로 구입할 로또 번호를 넣으면 로또를 생산한다`() {
        val input: List<String> = listOf("1, 2, 3, 4, 5, 6", "2, 3, 4, 5, 6, 7")
        val correctLotto1: Lotto =
            Lotto(listOf(LottoNumber(1), LottoNumber(2), LottoNumber(3), LottoNumber(4), LottoNumber(5), LottoNumber(6)))
        val correctLotto2: Lotto =
            Lotto(listOf(LottoNumber(2), LottoNumber(3), LottoNumber(4), LottoNumber(5), LottoNumber(6), LottoNumber(7)))
        val correctOutput: List<Lotto> = listOf(correctLotto2, correctLotto1)

        assertThat(LottoMachine().buyManualLottos(input).toSet())
            .isEqualTo(correctOutput.toSet())
    }
}
