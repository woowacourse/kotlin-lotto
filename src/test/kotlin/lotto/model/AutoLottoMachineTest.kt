package lotto.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class AutoLottoMachineTest {
    val fixedNumbers = (1..10).toList()
    val fixedLottoNumbersGenerator = FixedLottoNumbersGenerator(fixedNumbers)

    @Test
    fun `사전에 정의된 범위로 로또를 생성한다`() {
        val buyedLotto = AutoLottoMachine.createLottos(LottoBuyBudget(Lotto.LOTTO_PRICE), fixedLottoNumbersGenerator).first()
        val expectedLotto = Lotto.of(1, 2, 3, 4, 5, 6)
        assertThat(buyedLotto).isEqualTo(expectedLotto)
    }

    @Test
    fun `로또 한장의 가격이 5,000원일 때 2만원을 투입하면 총 4장의 로또를 발급한다`() {
        val buyedLottos = AutoLottoMachine.createLottos(LottoBuyBudget(20000, 5000), fixedLottoNumbersGenerator)
        assertThat(buyedLottos.size).isEqualTo(4)
    }

    @Test
    fun `로또 한장의 가격이 1,000원일 때 1만원을 투입하면 총 10장의 로또를 발급한다`() {
        val buyedLottos = AutoLottoMachine.createLottos(LottoBuyBudget(10000, 1000), fixedLottoNumbersGenerator)
        assertThat(buyedLottos.size).isEqualTo(10)
    }

    @Test
    fun `현재 로또 예산(LottoBuyBudget)으로 살 수 있는 만큼 로또를 생성한다 그 후에는 LottoBuyBudget의 LottoBuyPrice은 0이 된다`() {
        val lottoBuyBudget = LottoBuyBudget(10000, 1000)
        AutoLottoMachine.createLottos(lottoBuyBudget, fixedLottoNumbersGenerator)
        assertThat(lottoBuyBudget.lottoBuyPrice).isEqualTo(0)
    }
}
