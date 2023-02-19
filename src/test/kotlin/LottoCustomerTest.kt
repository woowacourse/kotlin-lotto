import domain.LottoCustomer
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
    fun `사용자가 구매하려고하는 자동로또의 개수를 구하기`() {
        assertThat(
            LottoCustomer(PurchaseMoney(10000), 3).getAutomaticLottosCountToPurchase()
        ).isEqualTo(7)
    }
}