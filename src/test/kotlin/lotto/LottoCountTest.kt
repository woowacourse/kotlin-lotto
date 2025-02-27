package lotto

import lotto.model.LottoCount
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows

class LottoCountTest {
    @Test
    fun `로또 수량은 0보다 크거나 같아야 한다`() {
        assertThrows<java.lang.IllegalArgumentException> { LottoCount(-1) }
        assertDoesNotThrow { LottoCount(0) }
    }

    @Test
    fun `로또 갯수가 입력한 갯수보다 적은지 알 수 있다`() {
        val lottoCount = LottoCount(10)
        assertAll(
            { assertTrue(lottoCount.isPurchasableLottoCount(LottoCount(10))) },
            { assertFalse(lottoCount.isPurchasableLottoCount(LottoCount(11))) },
        )
    }

    @Test
    fun `현재 로또 수량에서 입력받은 로또 수량을 차감한다`() {
        val lottoCount = LottoCount(5)

        assertThrows<IllegalArgumentException> { lottoCount.minus(LottoCount(6)) }
        assertDoesNotThrow { lottoCount.minus(LottoCount(5)) }
    }
}
