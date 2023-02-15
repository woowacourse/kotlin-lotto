package domain.lotto

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class PurchasedLottoTest {
    @ParameterizedTest
    @MethodSource("providePurchasedLotto")
    fun `구매한 로또가 주어졌을 때, getSortedLotto 호출시, 정렬된 Lotto를 반환한다`(
        purchasedLotto: PurchasedLotto,
        actualLotto: PurchasedLotto
    ) {
        val sortedPurchasedLotto = purchasedLotto.getSortedLotto()
        for (i in sortedPurchasedLotto.indices) {
            assertEquals(sortedPurchasedLotto[i].value, actualLotto[i].value)
        }
    }

    companion object {
        @JvmStatic
        fun providePurchasedLotto(): List<Arguments> =
            listOf(
                Arguments.of(
                    PurchasedLotto((1..6).map { number -> LottoNumber(number) }),
                    PurchasedLotto((1..6).map { number -> LottoNumber(number) })
                ),
                Arguments.of(
                    PurchasedLotto(listOf(1, 3, 5, 14, 13, 11).map { number -> LottoNumber(number) }),
                    PurchasedLotto(listOf(1, 3, 5, 11, 13, 14).map { number -> LottoNumber(number) })
                ),
                Arguments.of(
                    PurchasedLotto(listOf(45, 44, 43, 42, 41, 40).map { number -> LottoNumber(number) }),
                    PurchasedLotto(listOf(40, 41, 42, 43, 44, 45).map { number -> LottoNumber(number) })
                )
            )
    }
}
