package domain

import domain.model.LottoResult
import domain.model.PurchaseMoney
import org.assertj.core.api.Java6Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows

class LottoCustomerTest {

    @Test
    fun `사용자가 구매하려고하는 로또 전체의 개수보다 수동으로 발급받을 로또의 개수가 적은 경우`() {
        assertDoesNotThrow {
            LottoCustomer(PurchaseMoney(10000), 3)
        }
    }

    @Test
    fun `사용자가 구매하려고하는 로또 전체의 개수보다 수동으로 발급받을 로또의 개수가 많은 경우 예외가 발생`() {
        assertThrows<IllegalArgumentException> {
            LottoCustomer(PurchaseMoney(10000), 11)
        }
    }

    @Test
    fun `사용자가 10개의 로또를 구매하려고 할 때 수동 로또가 3개이면 자동 로또는 7개이다`() {
        assertThat(
            LottoCustomer(PurchaseMoney(10000), 3).getAutomaticLottosCountToPurchase()
        ).isEqualTo(7)
    }

    @Test
    fun `수익률을 계산한다`() {
        val lottoCustomer = LottoCustomer(PurchaseMoney(14000), 3)

        assertThat(
            lottoCustomer.getMyProfitRate(
                listOf(LottoResult.FIFTH)
            )
        ).isEqualTo(0.35)
    }
}
