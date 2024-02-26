package lotto.model

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class ManualLottoMachineTest {
    @ParameterizedTest
    @CsvSource(
        "7, 10000, 5000",
        "7, 20000, 5000",
        "3, 1000, 1000",
        "3, 2000, 1000",
    )
    fun `수동으로 구입하는 로또의 가격은 예산보다 높으면 안된다`(
        manualLottoBuyCount: Int,
        availableFund: Int,
        pricePerLotto: Int,
    ) {
        assertThrows<IllegalArgumentException> {
            ManualLottoMachine(manualLottoBuyCount, LottoBuyBudget(availableFund, pricePerLotto))
        }
    }

    @Test
    fun `수동으로 입력된 숫자들로 로또가 생성되고 생성된 로또의 가격이 예산에서 차감된다`() {
        val fixedNumbers =
            listOf(
                listOf(1, 2, 3, 4, 5, 6),
                listOf(7, 8, 9, 10, 11, 12),
                listOf(13, 14, 15, 16, 17, 18),
                listOf(19, 20, 21, 22, 23, 24),
            )
        val fixedLottoNumbersGenerator = FixedLottoNumbersGenerator(fixedNumbers)

        val lottoBuyBudget = LottoBuyBudget(20000, 1000)
        val manualLottoMachine = ManualLottoMachine(4, lottoBuyBudget)
        val manualBuyedLottos =
            manualLottoMachine.createLottos(fixedLottoNumbersGenerator)

        Assertions.assertThat(manualBuyedLottos).isEqualTo(
            listOf(
                Lotto.of(1, 2, 3, 4, 5, 6),
                Lotto.of(7, 8, 9, 10, 11, 12),
                Lotto.of(13, 14, 15, 16, 17, 18),
                Lotto.of(19, 20, 21, 22, 23, 24),
            ),
        )
        Assertions.assertThat(lottoBuyBudget.availableFunds).isEqualTo(16000)
    }
}
