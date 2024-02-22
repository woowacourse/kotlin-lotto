package model

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.ValueSource

class BonusTest {
    private val defaultLotto = Lotto((1..6).map { LottoNumber(it) })

    @ParameterizedTest
    @ValueSource(strings = ["1", "2", "3"])
    fun `보너스 번호가 사용자가 입력한 로또번호에 포함된다면 예외 발생`(input: String) {
        assertThatThrownBy {
            Bonus(input, defaultLotto)
        }.isExactlyInstanceOf(IllegalArgumentException::class.java)
            .hasMessage(Bonus.EXCEPTION_DUPLICATED_NUMBER)
    }

    @ParameterizedTest
    @CsvSource("7, 7", "8, 8", "9, 9")
    fun `보너스 번호가 사용자가 입력한 로또번호에 포함되지 않는다면 성공`(input: String, number: Int) {
        assertThat(Bonus(input, defaultLotto).number.number).isEqualTo(number)
    }
}
