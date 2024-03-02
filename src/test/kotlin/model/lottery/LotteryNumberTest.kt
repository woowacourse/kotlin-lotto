package model.lottery

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.RepeatedTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class LotteryNumberTest {
    @Test
    fun `로또 번호 한개를 저장한다`() {
        val lotteryNumber = LotteryNumber(1)
        assertThat(lotteryNumber.number).isEqualTo(1)
    }

    @ParameterizedTest
    @ValueSource(ints = [1, 2, 44, 45])
    fun `1 이상 45 이하의 숫자여야 한다`(number: Int) {
        val lotteryNumber = LotteryNumber(number)
        assertTrue(lotteryNumber.number in 1..45)
    }

    @ParameterizedTest
    @ValueSource(ints = [-1, 0, 46])
    fun `1 이상 45 이하의 숫자가 아니라면 예외를 발생시킨다_리팩토링 이전`(number: Int) {
        assertThrows<IllegalArgumentException> {
            LotteryNumber(number)
        }
    }

    @ParameterizedTest
    @ValueSource(strings = ["0", "46"])
    fun `입력으로 받은 보너스 번호가 1~45의 범위를 벗어나면 예외를 던진다`(input: String) {
        assertThrows<IllegalArgumentException> {
            LotteryNumber.from(input)
        }
    }

    @ParameterizedTest
    @ValueSource(strings = ["1", "22", "45"])
    fun `입력으로 받은 보너스 번호가 1~45의 범위에 있으면 통과한다_리팩토링 이전`(input: String) {
        assertDoesNotThrow {
            LotteryNumber.from(input)
        }
    }

    @ParameterizedTest
    @ValueSource(ints = [1, 3])
    fun `수동으로 1 ~ 45 사이의 로또 번호를 생성한다`(input: Int) {
        val lotteryNumber = LotteryNumber.fromManual(input)
        assertThat(lotteryNumber.number).isEqualTo(input)
    }

    @RepeatedTest(1000)
    fun `자동으로 1~45 사이의 로또 번호를 생성한다`() {
        assertDoesNotThrow { LotteryNumber.fromRandom() }
    }
}
