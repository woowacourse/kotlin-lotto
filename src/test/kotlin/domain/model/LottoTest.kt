package domain.model

import org.assertj.core.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class LottoTest {
    @ValueSource(strings = ["1,2,3,4,5,6,7", "1,2,3,4", "1,2,3,4,5"])
    @ParameterizedTest
    fun `로또 번호는 6개가 아니면 예외가 발생한다`(value: String) {
        Assertions
            .assertThatThrownBy {
                Lotto(splitValueSource(value).map { LottoNumber(it) })
            }.isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage(INVALID_LOTTO_SIZE)
    }

    @ValueSource(strings = ["1,56,2,3,4,5", "45,56,67,87,23,1"])
    @ParameterizedTest
    fun `로또 번호는 1 부터 45 사이가 아니면 예외가 발생한다`(value: String) {
        val values = value.split(",").map { it.toInt() }
        Assertions
            .assertThatThrownBy {
                Lotto(splitValueSource(value).map { LottoNumber(it) })
            }.isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage(INVALID_LOTTO_NUMBERS)
    }

    @ValueSource(strings = ["1,2,2,3,4,5", "3,3,3,3,3,3"])
    @ParameterizedTest
    fun `로또 번호에 중복이 있으면 예외가 발생한다`(value: String) {
        Assertions
            .assertThatThrownBy {
                Lotto(splitValueSource(value).map { LottoNumber(it) })
            }.isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage(DUPLICATED_LOTTO_NUMBERS)
    }

    companion object {
        const val INVALID_LOTTO_SIZE = "[ERROR] 로또 번호는 6개 입니다."
        const val INVALID_LOTTO_NUMBERS = "[ERROR] 로또 번호는 1부터 45 사이입니다."
        const val DUPLICATED_LOTTO_NUMBERS = "[ERROR] 로또 번호는 중복이 없습니다."

        private fun splitValueSource(value: String): List<Int> = value.split(",").map { it.toInt() }
    }
}
