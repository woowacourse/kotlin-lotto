package lotto.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class LottoMachineTest {
    val fixedNumbers = (1..10).toList()
    val fixedLottoNumbersGenerator = FixedLottoNumbersGenerator(fixedNumbers)

    @Test
    fun `사전에 정의된 범위로 로또를 생성한다`() {
        val buyedLotto = LottoMachine.createAutoLottos(Lotto.LOTTO_PRICE, fixedLottoNumbersGenerator).first()
        val expectedLotto = Lotto.of(1, 2, 3, 4, 5, 6)
        assertThat(buyedLotto).isEqualTo(expectedLotto)
    }

    @Test
    fun `로또 한장의 가격이 5,000원일 때 2만원을 투입하면 총 4장의 로또를 발급한다`() {
        val buyedLottos = LottoMachine.createAutoLottos(20000, fixedLottoNumbersGenerator, 5000)
        assertThat(buyedLottos.size).isEqualTo(4)
    }

    @Test
    fun `로또 한장의 가격이 1,000원일 때 1만원을 투입하면 총 10장의 로또를 발급한다`() {
        val buyedLottos = LottoMachine.createAutoLottos(10000, fixedLottoNumbersGenerator, 1000)
        assertThat(buyedLottos.size).isEqualTo(10)
    }
}
