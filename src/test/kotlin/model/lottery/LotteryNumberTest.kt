package model.lottery

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class LotteryNumberTest {
    @Test
    fun `로또 번호 한개를 저장한다`() {
        val lotteryNumber = LotteryNumber.of(1)
        assertThat(lotteryNumber.number).isEqualTo(1)
    }

    @ParameterizedTest
    @ValueSource(ints = [1, 2, 44, 45])
    fun `1 이상 45 이하의 숫자여야 한다`(number: Int) {
        val lotteryNumber = LotteryNumber.of(number)
        assertTrue(lotteryNumber.number in 1..45)
    }

    @ParameterizedTest
    @ValueSource(ints = [0, 46])
    fun `1 이상 45 이하의 범위를 벗어나면 예외를 던진다`(input: Int) {
        assertThatThrownBy { LotteryNumber.of(input) }.isExactlyInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("$input 을 입력하셨습니다. 로또 번호는 1 이상 45 이하의 숫자여야 합니다.")
    }
}
