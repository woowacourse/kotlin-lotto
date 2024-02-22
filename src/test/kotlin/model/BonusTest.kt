package model

import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class BonusTest {
    private val defaultLottoNumbers = (1..6).map { LottoNumber(it) }.run { Lotto(this) }

    @ParameterizedTest
    @ValueSource(strings = ["pang", "hannah", "a12", "%@"])
    fun `숫자가 아니라면 예외 발생`(input: String) {
        assertThrows<IllegalArgumentException> {
            Bonus(input, defaultLottoNumbers)
        }
    }

    @ParameterizedTest
    @ValueSource(strings = ["46", "0", "100"])
    fun `1 ~ 45 범위의 숫자가 아니라면 예외 발생`(input: String) {
        assertThrows<IllegalArgumentException> {
            Bonus(input, defaultLottoNumbers)
        }
    }

    @ParameterizedTest
    @ValueSource(strings = ["1", "2", "3"])
    fun `사용자가 입력한 로또번호에 포함된다면 예외 발생`(input: String) {
        assertThrows<IllegalArgumentException> {
            Bonus(input, defaultLottoNumbers)
        }
    }
}
