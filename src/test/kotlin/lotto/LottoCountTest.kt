package lotto

import lotto.model.LottoCount
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows

class LottoCountTest {
    @Test
    fun `로또 수량은 0보다 크거나 같아야 한다`() {
        assertThrows<java.lang.IllegalArgumentException> { LottoCount(-1) }
        assertDoesNotThrow { LottoCount(0) }
    }

    @Test
    fun `현재 로또 수량에서 구매 가능한 수량인지 판단한다`() {
        val originLottoCount = LottoCount(10)

        val validLottoCount = LottoCount(10)
        val invalidLottoCount = LottoCount(15)

        assertThrows<IllegalArgumentException> { originLottoCount.validateLottoCount(invalidLottoCount) }
        assertDoesNotThrow { originLottoCount.validateLottoCount(validLottoCount) }
    }

    @Test
    fun `현재 로또 수량에서 입력받은 로또 수량을 차감한다`() {
        val lottoCount = LottoCount(5)

        assertThrows<IllegalArgumentException> { lottoCount.minus(LottoCount(6)) }
        assertDoesNotThrow { lottoCount.minus(LottoCount(5)) }
    }
}
