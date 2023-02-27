package domain

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

    @ValueSource(strings = ["", "a", " "])
    @ParameterizedTest
    fun `숫자가 아닌 값이 들어오면 에러를 발생시킨다`(receivedMoney: String) {
        // given

        // when

        // then
        assertThatIllegalArgumentException()
            .isThrownBy { Payment(receivedMoney) }
            .withMessageContaining("[Error] 1000 단위의 숫자로 값을 입력해주세요.")
    }

    @Test
    fun `구입금액으로 최대로 살 수 있는 로또 개수 계산`() {
        // given
        val payment = Payment(10000)

        // when
        val lottoCount: Int = payment.calculateMaxLottoCount()

        // then
        assertThat(lottoCount).isEqualTo(10)
    }

    @Test
    fun `자동으로 발급해야할 로또 개수를 계산한다`() {
        // given
        val payment = Payment(10000)

        // when
        val actual: Int = payment.calculateAutoLottoCount(10, 5)

        // then
        assertThat(actual).isEqualTo(5)
    }
}
