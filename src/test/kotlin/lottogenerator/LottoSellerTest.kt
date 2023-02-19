package lottogenerator

import domain.LottoSeller
import domain.model.PurchaseMoney
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows

class LottoSellerTest {

    private lateinit var lottoSeller: LottoSeller

    @BeforeEach
    fun setUp() {
        lottoSeller = LottoSeller()
    }

    @Test
    fun `사용자가 입력한 돈이 1000원 단위인 경우`(){
        assertDoesNotThrow {
            lottoSeller.receiveMoneyFromCustomer(PurchaseMoney(10000), 3)
        }
    }

    @Test
    fun `사용자가 입력한 돈이 1000원 단위이 아닌 경우 예외가 발생`(){
        assertThrows<IllegalArgumentException> {
            lottoSeller.receiveMoneyFromCustomer(PurchaseMoney(10500), 3)
        }
    }

    @Test
    fun `로또 6개중 3개를 `() {

    }
}