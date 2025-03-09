package lottotest.domain.valueobject

import lotto.domain.valueobject.LottoPaymentMoney
import lotto.domain.valueobject.LottoQuantity
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.ValueSource

class LottoPaymentMoneyTest {
    @ParameterizedTest
    @ValueSource(ints = [-1, -1000])
    fun `구입 금액이 음수일 수 없다`(money: Int) {
        assertThrows<IllegalArgumentException> {
            LottoPaymentMoney(money)
        }
    }

    @ParameterizedTest
    @ValueSource(ints = [1, 999, 1001, 9999])
    fun `구입 금액은 로또 한장 단위로 나누어 떨어져야 한다`(money: Int) {
        assertThrows<IllegalArgumentException> {
            LottoPaymentMoney(money)
        }
    }

    @ParameterizedTest
    @ValueSource(ints = [0, 1000, 123_000])
    fun `로또 구입 금액은 로또 구입이 가능한 거스름돈 없는 값을 가진다`(money: Int) {
        // given when
        val lottoPaymentMoney = LottoPaymentMoney(money)

        // then
        assertThat(lottoPaymentMoney.money).isEqualTo(money)
    }

    @ParameterizedTest
    @CsvSource(
        "0, 0",
        "1000, 1",
        "123_000, 123",
    )
    fun `로또 구입 금액은 구매 가능한 로또의 수량을 알려준다`(
        money: Int,
        expectedQuantity: Int,
    ) {
        // given when
        val lottoPaymentMoney = LottoPaymentMoney(money)

        // then
        assertThat(lottoPaymentMoney.calculatePossibleBuyLottoQuantity().quantity).isEqualTo(expectedQuantity)
    }

    @ParameterizedTest
    @CsvSource(
        "1000, 0, 1",
        "1000, 1, 0",
        "123_000, 122, 1",
    )
    fun `로또 구입금액에게 이미 구매한 로또의 수량을 알려주면 남은 구매 가능한 로또의 수량을 알려준다`(
        money: Int,
        rawPartialPurchaseQuantity: Int,
        expectedLeftQuantity: Int,
    ) {
        // given
        val lottoPaymentMoney = LottoPaymentMoney(money)
        val partialPurchaseQuantity = LottoQuantity(rawPartialPurchaseQuantity)

        // when
        val actualLeftQuantity = lottoPaymentMoney.calculateLeftLotoQuantity(partialPurchaseQuantity)

        // then
        assertThat(actualLeftQuantity.quantity).isEqualTo(expectedLeftQuantity)
    }
}
