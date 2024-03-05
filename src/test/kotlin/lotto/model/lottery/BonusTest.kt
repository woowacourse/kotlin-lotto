package lotto.model.lottery

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.ValueSource

class BonusTest {
    private val defaultLottery = Lottery((1..6).map { LotteryNumber.from(it) }.toSet())

    @ParameterizedTest
    @ValueSource(ints = [1, 2, 3])
    fun `보너스 번호가 사용자가 입력한 로또번호에 포함된다면 예외 발생`(input: Int) {
        assertThatThrownBy {
            Bonus.fromInput(input, defaultLottery)
        }.isExactlyInstanceOf(IllegalArgumentException::class.java)
            .hasMessage(Bonus.EXCEPTION_DUPLICATED_NUMBER)
    }

    @ParameterizedTest
    @CsvSource("7, 7", "8, 8", "9, 9")
    fun `보너스 번호가 사용자가 입력한 로또번호에 포함되지 않는다면 성공`(
        input: Int,
        number: Int,
    ) {
        assertThat(Bonus.fromInput(input, defaultLottery).lotteryNumber.toString().toInt()).isEqualTo(number)
    }
}
