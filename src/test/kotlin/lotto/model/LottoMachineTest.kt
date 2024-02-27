package lotto.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class LottoMachineTest {
    @Test
    fun `수동 구매 시 입력된 숫자들로 로또가 생성이 된다`() {
        val fixedNumbers = (1..24 step 6).map { (it..it + 5).toList() }
        val fixedLottoNumbersGenerator = FixedLottoNumbersGenerator(fixedNumbers)
        val maualPurchasedLottos = LottoMachine.createLottos(4, fixedLottoNumbersGenerator)
        assertThat(maualPurchasedLottos).isEqualTo(fixedNumbers.map { Lotto(it.map(LottoNumber::of)) })
    }
}
