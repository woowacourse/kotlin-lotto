package lotto.model

import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource

class ManualLottoBuyCountTest {
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
        count: Int,
        lottoPrice: Int,
        buyPrices: Array<Int>,
    ) {
        buyPrices.forEach { buyPrice ->
            assertThrows<IllegalArgumentException> {
                ManualLottoBuyCount(count, LottoBuyPrice(buyPrice, lottoPrice))
            }
        }
    }
}
