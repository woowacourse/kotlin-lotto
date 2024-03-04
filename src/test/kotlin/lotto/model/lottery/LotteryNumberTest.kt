package lotto.model.lottery

import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class LotteryNumberTest {
    @ParameterizedTest
    @ValueSource(ints = [46, 0, 100])
    fun `1 ~ 45 범위의 숫자가 아니라면 예외 발생`(input: Int) {
        assertThatThrownBy {
            LotteryNumber.from(input)
        }.isExactlyInstanceOf(IllegalArgumentException::class.java)
            .hasMessage(LotteryNumber.EXCEPTION_INVALID_RANGE)
    }
}
