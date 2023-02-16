package domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class SellerTest {

    @CsvSource(value = ["14000,14", "15000,15"])
    @ParameterizedTest
    fun `입력 받은 구입 금액을 갯수로 환산한다`(money: Int, count: Int) {
        // when
        val actualResult = Seller(money).getLottoCount()
        // then
        assertThat(actualResult).isEqualTo(count)
    }

//    @CsvSource(value = ["3000,3", "15000,15"])
//    @ParameterizedTest
//    fun `구매한 수량만큼의 로또를 생성한다`(money: Int, count: Int) {
//        //when
//        val actual = Seller(money).getTicket()
//        //then
//        assertThat(actual.size).isEqualTo(count)
//    }
}
