package lotto.model

import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class LotteryNumberTest {
    @ParameterizedTest
    @ValueSource(strings = ["pang", "hannah", "a12", "%@"])
    fun `숫자가 아니라면 예외 발생`(input: String) {
        assertThatThrownBy {
            LotteryNumber.fromInput(input)
        }.isExactlyInstanceOf(IllegalArgumentException::class.java)
            .hasMessage(LotteryNumber.EXCEPTION_IS_NOT_NUMBER)
    }

    @ParameterizedTest
    @ValueSource(strings = ["46", "0", "100"])
    fun `1 ~ 45 범위의 숫자가 아니라면 예외 발생`(input: String) {
        assertThatThrownBy {
            LotteryNumber.fromInput(input)
        }.isExactlyInstanceOf(IllegalArgumentException::class.java)
            .hasMessage(LotteryNumber.EXCEPTION_INVALID_RANGE)
    }
}
