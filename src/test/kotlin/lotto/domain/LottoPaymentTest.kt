package lotto.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class LottoPaymentTest {
    @ValueSource(ints = [1_000, 1_500, 10_000])
    @ParameterizedTest
    fun `로또 구입 금액은 1,000원 이상이다 `(value: Int) {
        val number = LottoPayment(value)
        assertThat(number.toInt()).isEqualTo(value)
    }

    @ValueSource(ints = [-1, 0, 100, 999])
    @ParameterizedTest
    fun `로또 구입 금액이 1,000원 미만이면 오류를 발생한다`(value: Int) {
        assertThrows<IllegalArgumentException> { LottoPayment(value) }
    }
}
