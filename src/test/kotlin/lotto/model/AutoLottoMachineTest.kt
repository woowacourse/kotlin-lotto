package lotto.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class AutoLottoMachineTest {
    @Test
    fun `로또 한장의 가격이 5,000원일 때 2만원을 투입하면 총 4장의 로또를 사전에 약속된 숫자들로 발급하고 예산은 0이된다`() {
        val lottoBuyBudget = LottoBuyBudget(20000, 5000)

        val fixedNumbers = (1..24 step 6).map { (it..it + 5).toList() }
        val fixedLottoNumbersGenerator = FixedLottoNumbersGenerator(fixedNumbers)
        val autoBuyedLottos = AutoLottoMachine.createLottos(lottoBuyBudget, fixedLottoNumbersGenerator)
        assertThat(autoBuyedLottos).isEqualTo(fixedNumbers.map { Lotto(it.map(LottoNumber::of)) })

        assertThat(lottoBuyBudget.availableFunds).isEqualTo(0)
    }

    @Test
    fun `로또 한장의 가격이 1,000원일 때 7천을 투입하면 총 7장의 로또를 사전에 약속된 숫자들로 발급하고 예산은 0이된다`() {
        val lottoBuyBudget = LottoBuyBudget(7000, 1000)

        val fixedNumbers = (1..42 step 6).map { (it..it + 5).toList() }
        val fixedLottoNumbersGenerator = FixedLottoNumbersGenerator(fixedNumbers)
        val autoBuyedLottos = AutoLottoMachine.createLottos(lottoBuyBudget, fixedLottoNumbersGenerator)
        assertThat(autoBuyedLottos).isEqualTo(fixedNumbers.map { Lotto(it.map(LottoNumber::of)) })
        assertThat(lottoBuyBudget.availableFunds).isEqualTo(0)
    }
}
