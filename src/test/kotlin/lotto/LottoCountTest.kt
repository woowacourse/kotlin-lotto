package lotto

import lotto.model.LottoCount
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class LottoCountTest {
    @ParameterizedTest(name = "현재 수량이 {0}이고 구매할 수량이 {1}이라면 구매 가능 여부는 {2}를 반환한다.")
    @CsvSource(value = ["3,0,true", "3,2,true", "3,3,true", "3,4,false"])
    fun `현재 로또 수량에서 구매 가능한 수량인지 판단한다`(
        _currentLottoCount: Int,
        _purchaseLottoCount: Int,
        expected: Boolean,
    ) {
        val currentLottoCount = LottoCount(_currentLottoCount)
        val purchaseLottoCount = LottoCount(_purchaseLottoCount)

        val actual = currentLottoCount.isAvailPurchaseLottoCount(purchaseLottoCount)

        assertEquals(expected, actual)
    }
}
