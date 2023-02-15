package lotto.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class LotteryNumberTest {
    @ParameterizedTest
    @ValueSource(ints = [1, 45])
    fun `번호를 하나 가진다`(number: Int) {
        val lotteryNumber = LotteryNumber(number)
        assertThat(lotteryNumber.number).isEqualTo(number)
    }
}
