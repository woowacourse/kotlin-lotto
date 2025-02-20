package lotto.domain.model

import lotto.domain.value.LottoNumber
import lotto.domain.value.PurchaseAmount
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows

class PurchaseDetailTest {
    private lateinit var lotto: Lotto

    @BeforeEach
    fun setUp() {
        val lottoNumbers = (1..6).map { LottoNumber(it) }
        lotto = Lotto(lottoNumbers)
    }

    @Test
    fun `구입 금액 만큼의 로또 정보를 갖는다`() {
        assertDoesNotThrow {
            val purchaseAmount = PurchaseAmount(1000)
            val lottos = listOf(lotto)
            PurchaseDetail(purchaseAmount, lottos)
        }
    }

    @Test
    fun `구입 금액 만큼의 로또 정보를 갖지 않으면 예외가 발생한다`() {
        assertThrows<IllegalArgumentException> {
            val purchaseAmount = PurchaseAmount(1000)
            val lottos = listOf(lotto, lotto)
            PurchaseDetail(purchaseAmount, lottos)
        }
    }
}
