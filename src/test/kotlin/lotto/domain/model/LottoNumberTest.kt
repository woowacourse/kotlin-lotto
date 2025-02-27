package lotto.domain.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class LottoNumberTest {
    @ParameterizedTest
    @ValueSource(ints = [1, 2, 3, 43, 44, 45])
    fun `로또 번호는 1~45 사이여야 한다`(numbers: Int) {
        assertDoesNotThrow { LottoNumber(numbers) }
    }

    @ParameterizedTest
    @ValueSource(ints = [-1, 0, 46, 47])
    fun `로또 번호가 1~45 사이가 아닌 경우 예외가 발생한다`(numbers: Int) {
        val exception = assertThrows<IllegalArgumentException> { LottoNumber(numbers) }
        assertThat(exception.message).isEqualTo("[ERROR] 로또 번호는 1~45 사이여야 합니다.")
    }
}
