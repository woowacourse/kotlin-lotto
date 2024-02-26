package lotto.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class AutoLottoMachineTest {
    @Test
    fun `사전에 정의된 범위로 로또를 생성한다`() {
        val fixedNumbers =
            listOf(
                listOf(1, 2, 3, 4, 5, 6),
            )
        val fixedLottoNumbersGenerator = FixedLottoNumbersGenerator(fixedNumbers)
        val autoBuyedLottos =
            AutoLottoMachine.createLottos(LottoBuyBudget(Lotto.PRICE_PER_LOTTO), fixedLottoNumbersGenerator).first()
        val expectedLotto = Lotto.of(1, 2, 3, 4, 5, 6)
        assertThat(autoBuyedLottos).isEqualTo(expectedLotto)
    }

    @Test
    fun `로또 한장의 가격이 5,000원일 때 2만원을 투입하면 총 4장의 로또를 발급한다`() {
        val fixedNumbers =
            listOf(
                listOf(1, 2, 3, 4, 5, 6),
                listOf(7, 8, 9, 10, 11, 12),
                listOf(14, 15, 16, 17, 18, 19),
                listOf(21, 22, 23, 24, 25, 26),
            )
        val fixedLottoNumbersGenerator = FixedLottoNumbersGenerator(fixedNumbers)
        val autoBuyedLottos = AutoLottoMachine.createLottos(LottoBuyBudget(20000, 5000), fixedLottoNumbersGenerator)
        assertThat(autoBuyedLottos).isEqualTo(
            listOf(
                Lotto.of(1, 2, 3, 4, 5, 6),
                Lotto.of(7, 8, 9, 10, 11, 12),
                Lotto.of(14, 15, 16, 17, 18, 19),
                Lotto.of(21, 22, 23, 24, 25, 26),
            ),
        )
    }

    @Test
    fun `로또 한장의 가격이 1,000원일 때 1만원을 투입하면 총 10장의 로또를 발급한다`() {
        val fixedNumbers =
            listOf(
                listOf(1, 2, 3, 4, 5, 6),
                listOf(7, 8, 9, 10, 11, 12),
                listOf(14, 15, 16, 17, 18, 19),
                listOf(21, 22, 23, 24, 25, 26),
                listOf(1, 2, 3, 4, 5, 6),
                listOf(7, 8, 9, 10, 11, 12),
                listOf(14, 15, 16, 17, 18, 19),
                listOf(21, 22, 23, 24, 25, 26),
                listOf(1, 2, 3, 4, 5, 6),
                listOf(7, 8, 9, 10, 11, 12),
            )
        val fixedLottoNumbersGenerator = FixedLottoNumbersGenerator(fixedNumbers)
        val autoBuyedLottos = AutoLottoMachine.createLottos(LottoBuyBudget(10000, 1000), fixedLottoNumbersGenerator)
        assertThat(autoBuyedLottos).isEqualTo(
            listOf(
                Lotto.of(1, 2, 3, 4, 5, 6),
                Lotto.of(7, 8, 9, 10, 11, 12),
                Lotto.of(14, 15, 16, 17, 18, 19),
                Lotto.of(21, 22, 23, 24, 25, 26),
                Lotto.of(1, 2, 3, 4, 5, 6),
                Lotto.of(7, 8, 9, 10, 11, 12),
                Lotto.of(14, 15, 16, 17, 18, 19),
                Lotto.of(21, 22, 23, 24, 25, 26),
                Lotto.of(1, 2, 3, 4, 5, 6),
                Lotto.of(7, 8, 9, 10, 11, 12),
            ),
        )
    }

    @Test
    fun `현재 예산으로 살 수 있는 만큼 로또를 생성한다 그 후 예산이 0이 된다`() {
        val fixedNumbers =
            listOf(
                listOf(1, 2, 3, 4, 5, 6),
                listOf(1, 2, 3, 4, 5, 6),
                listOf(1, 2, 3, 4, 5, 6),
                listOf(1, 2, 3, 4, 5, 6),
                listOf(1, 2, 3, 4, 5, 6),
                listOf(1, 2, 3, 4, 5, 6),
                listOf(1, 2, 3, 4, 5, 6),
                listOf(1, 2, 3, 4, 5, 6),
                listOf(1, 2, 3, 4, 5, 6),
                listOf(1, 2, 3, 4, 5, 6),
            )
        val fixedLottoNumbersGenerator = FixedLottoNumbersGenerator(fixedNumbers)
        val lottoBuyBudget = LottoBuyBudget(10000, 1000)
        val autoBuyedLottos = AutoLottoMachine.createLottos(lottoBuyBudget, fixedLottoNumbersGenerator)
        assertThat(autoBuyedLottos.size).isEqualTo(10)
        assertThat(lottoBuyBudget.availableFunds).isEqualTo(0)
    }
}
