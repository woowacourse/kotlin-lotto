package lotto.model

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource

class ManualLottoMachineTest {
    companion object {
        @JvmStatic
        fun provideLottoPricesAndInvalidPrices() =
            listOf(
                arrayOf(7, 5000, arrayOf(10000, 20000, 30000)),
                arrayOf(3, 1000, arrayOf(1000, 1500, 2000)),
            )
    }

    @ParameterizedTest
    @MethodSource("provideLottoPricesAndInvalidPrices")
    fun `수동으로 구입하는 로또의 가격은 로또 구입 가격이하다`(
        buyCount: Int,
        lottoPrice: Int,
        buyPrices: Array<Int>,
    ) {
        buyPrices.forEach { buyPrice ->
            assertThrows<IllegalArgumentException> {
                ManualLottoMachine(buyCount, LottoBuyBudget(buyPrice, lottoPrice))
            }
        }
    }

    @Test
    fun `수동으로 입력된 숫자들로 로또가 생성되고 생성된 로또의 가격이 예산에서 차감된다`() {
        val lottoBuyBudget = LottoBuyBudget(20000, 1000)
        val manualLottoMachine = ManualLottoMachine(2, lottoBuyBudget)
        val manualInputNumbers =
            listOf(
                listOf(1, 2, 3, 4, 5, 6),
                listOf(7, 8, 9, 10, 11, 12),
            )
        val manualBuyedLottos =
            manualLottoMachine.createLottosFrom(manualInputNumbers)

        Assertions.assertThat(manualBuyedLottos).isEqualTo(
            listOf(
                Lotto.of(1, 2, 3, 4, 5, 6),
                Lotto.of(7, 8, 9, 10, 11, 12),
            ),
        )
        Assertions.assertThat(lottoBuyBudget.availableFunds).isEqualTo(18000)
    }
}
