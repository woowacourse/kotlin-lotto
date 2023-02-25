package lotto.model

import lotto.entity.Lotto
import lotto.entity.LottoCount
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class LottoMachineTest {

    @Test
    fun `수동과 자동 로또 생성`() {
        // given
        val exceptManualLottos = listOf(Lotto(listOf(1, 2, 3, 4, 5, 6)))
        val manualLottoGenerator = SequentialLottoNumberGenerator(exceptManualLottos)
        val exceptAutoLottos = listOf(Lotto(listOf(7, 8, 9, 10, 11, 12)))
        val autoLottoGenerator = SequentialLottoNumberGenerator(exceptAutoLottos)
        val lottoMachine = LottoMachine(manualLottoGenerator, autoLottoGenerator)

        // when
        val actual = lottoMachine.producePurchasedLottos(LottoCount(1), LottoCount(1))
        val except = listOf(Lotto(listOf(1, 2, 3, 4, 5, 6)), Lotto(listOf(7, 8, 9, 10, 11, 12)))

        // then
        assertThat(actual.value).isEqualTo(except)
    }
}
