package model.domain

import model.Count
import model.LottoNumber
import model.Money
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class LottoMachineTest {

    @Test
    fun `금액은 2000원, 수동 1장, 자동 1장, 총 2장`() {
        // given
        val money = Money(2000)
        val manualLottoCount = 1
        val manualLotto = listOf<LottoNumber>(
            LottoNumber(1),
            LottoNumber(2),
            LottoNumber(3),
            LottoNumber(4),
            LottoNumber(5),
            LottoNumber(6),
        )
        val autoLottoCount = Count(money, manualLottoCount).autoLottoCount
        val totalLottoCount = manualLottoCount + autoLottoCount
        val manualLottoGenerator = ManualLottoGenerator()
        val autoLottoGenerator = AutoLottoGenerator()
        manualLottoGenerator.makeBundleOfManualLotto(manualLotto)
        val lottoMachine = LottoMachine()

        // when
        lottoMachine.generateLotto(autoLottoGenerator)
        lottoMachine.generateLotto(manualLottoGenerator)
        val actual = lottoMachine.bundleOfLotto.size

        // then
        assertThat(actual).isEqualTo(totalLottoCount)
    }
}
