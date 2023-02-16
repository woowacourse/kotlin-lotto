package domain

import model.Payment
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class SellerTest {

    @CsvSource(value = ["14000,14", "15000,15"])
    @ParameterizedTest
    fun `입력 받은 구입 금액을 갯수로 환산한다`(money: Int, count: Int) {
        // when
        val actualResult = Seller(Payment(money)).getLottoCount()
        // then
        assertThat(actualResult).isEqualTo(count)
    }
}
