package domain

import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatIllegalArgumentException
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class PaymentTest {

    @ValueSource(longs = [1000, 2000, 5000, 100000])
    @ParameterizedTest
    fun `금액은 1000원 단위로 받는다`(receivedMoney: Long) {
        // given

        // when

        // then
        assertThat(Payment(receivedMoney).amount).isEqualTo(receivedMoney)
    }

    @ValueSource(longs = [14400, 1, 555, 0])
    @ParameterizedTest
    fun `금액은 1000원 단위가 아니면 에러를 발생시킨다`(receivedMoney: Long) {
        // given

        // when

        // then
        assertThatIllegalArgumentException()
            .isThrownBy { Payment(receivedMoney) }
            .withMessageContaining("[Error] $receivedMoney 받은 돈이 1000원 단위여야합니다.")
    }

    @Test
    fun `구입금액에 해당하는 로또 개수 계산`() {
        // given
        val payment = Payment(10000)

        // when
        val lottoCount: Int = payment.calculateLottoCount()

        // then
        Assertions.assertThat(lottoCount).isEqualTo(10)
    }
}
