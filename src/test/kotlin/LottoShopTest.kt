import domain.model.PurchaseMoney
import domain.model.lotto.Lotto
import domain.model.lotto.LottoTicket
import domain.model.lotto.LottoShop
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class LottoShopTest {

    @Test
    fun `수동 로또의 개수는 구입한 로또 개수를 넘을 수 없다`() {
        val errorMessage = assertThrows<IllegalArgumentException> {
            LottoShop(PurchaseMoney(1000), 3)
        }
        assertThat(errorMessage.message).isEqualTo("[ERROR] 구입한 로또 개수를 초과했습니다.")
    }

    @Test
    fun `수동 로또의 개수는 음수일 수 없다`() {
        val errorMessage = assertThrows<IllegalArgumentException> {
            LottoShop(PurchaseMoney(1000), -1)
        }
        assertThat(errorMessage.message).isEqualTo("[ERROR] 음수일 수 없습니다.")
    }

    @Test
    fun `수동 로또를 구매한다`() {
        val lottoShop = LottoShop(PurchaseMoney(1000), 1)
        val lotto = lottoShop.purchaseManualLotto(
            LottoTicket(listOf(1, 2, 3, 4, 5, 6))
        )
        assertThat(lotto).isEqualTo(
            Lotto.create(
                LottoTicket(listOf(1, 2, 3, 4, 5, 6))
            )
        )
    }

    @Test
    fun `구입 금액이 5000원이고, 수동 로또의 수가 2개이면 자동 로또의 수는 3개다`() {
        val lottoShop = LottoShop(PurchaseMoney(5000), 2)
        assertThat(lottoShop.numberOfAutoLotto()).isEqualTo(3)
    }
}
