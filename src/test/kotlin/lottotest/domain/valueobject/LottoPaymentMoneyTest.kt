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
    fun `구입 금액이 음수라면 인스턴스를 생성하지 않는다`(money: Int) {
        assertThrows<IllegalArgumentException> {
            LottoPaymentMoney(money)
        }
    }

    @ParameterizedTest
    @ValueSource(ints = [1, 999, 1001, 9999])
    fun `구입 금액이 로또 한장 단위로 나누어 떨어지지 않는다면 인스턴스를 생성하지 않는다`(money: Int) {
        assertThrows<IllegalArgumentException> {
            LottoPaymentMoney(money)
        }
    }

    @ParameterizedTest
    @ValueSource(ints = [0, 1000, 123_000])
    fun `양수이고 로또 한장 단위로 나누어 떨어지는 구입 금액으로 로또 지불금을 생성하면 입력한 금액 값을 포함한다`(money: Int) {
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
    fun `로또 지불금으로 구매 가능한 전체 로또 수량을 계산하면 로또 수량 객체에 수량 정보를 담아 반환한다`(
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
    fun `전체 지불 금액만큼의 수량에서 부분 구매한 로또 수량 인스턴스를 제공하면 나머지 로또 수량 객체를 반환한다`(
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
