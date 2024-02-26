package lotto.model

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource

class ManualLottoMachineTest {
    @ParameterizedTest
    @MethodSource("provideLottoPricesAndInvalidPrices")
    fun `수동으로 구입하는 로또의 가격은 예산(구입 금액)이하다`(
        manualLottoBuyCount: Int,
        availableFunds: Array<Int>,
        pricePerLotto: Int,
    ) {
        availableFunds.forEach { availableFund ->
            assertThrows<IllegalArgumentException> {
                ManualLottoMachine(manualLottoBuyCount, LottoBuyBudget(availableFund, pricePerLotto))
            }
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

    companion object {
        @JvmStatic
        fun provideLottoPricesAndInvalidPrices() =
            listOf(
                arrayOf(7, arrayOf(10000, 20000, 30000), 5000),
                arrayOf(3, arrayOf(1000, 1500, 2000), 1000),
            )
    }
}
