package lottotest.domain.valueobject

import lotto.domain.valueobject.LottoPaymentMoney
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class LottoPaymentMoneyTest {
    @ParameterizedTest
    @ValueSource(ints = [-1, -1000])
    fun `구입 금액이 음수라면 인스턴스를 생성하지 않는다`(money:Int) {
        assertThrows<IllegalArgumentException> {
            LottoPaymentMoney(money)
        }
    }

    @ParameterizedTest
    @ValueSource(ints = [1, 999, 1001, 9999])
    fun `구입 금액이 로또 한장 단위로 나누어 떨어지지 않는다면 인스턴스를 생성하지 않는다`(money:Int) {
        assertThrows<IllegalArgumentException> {
            LottoPaymentMoney(money)
        }
    }

    @ParameterizedTest
    @ValueSource(ints = [0, 1000, 123_000])
    fun `양수이고 로또 한장 단위로 나누어 떨어지는 구입 금액으로 로또 지불금을 생성하면 입력한 금액 값을 포함한다`(money:Int) {
        // given when
        val payment = LottoPaymentMoney(money)

        // then
        assertThat(payment.money).isEqualTo(money)
    }
}