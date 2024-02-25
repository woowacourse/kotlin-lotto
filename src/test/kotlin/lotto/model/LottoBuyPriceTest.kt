package lotto.model

import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource

class LottoBuyPriceTest {
    companion object {
        @JvmStatic
        fun provideLottoPricesAndInvalidPrices() =
            listOf(
                arrayOf(5000, arrayOf(-1, 0, 4999)),
                arrayOf(1000, arrayOf(-1, 0, 999)),
            )
    }

    @ParameterizedTest
    @MethodSource("provideLottoPricesAndInvalidPrices")
    fun `로또 구입 금액은 로또 1장의 가격 이상이여야합니다`(
        lottoPrice: Int,
        buyPrices: Array<Int>,
    ) {
        buyPrices.forEach { buyPrice ->
            assertThrows<IllegalArgumentException> {
                LottoBuyPrice(buyPrice, lottoPrice)
            }
        }
    }
}
