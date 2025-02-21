package lotto.domain.model

import lotto.domain.value.LottoNumber
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class LottoTest {
    @Test
    fun `6개의 로또 번호를 갖는다`() {
        assertDoesNotThrow {
            Lotto.of(1, 2, 3, 4, 5, 6)
        }
    }

    @Test
    fun `로또 번호가 6개가 아니면 예외가 발생한다`() {
        assertThrows<IllegalArgumentException> {
            Lotto.of(1, 2, 3, 4, 5)
        }
    }

    @Test
    fun `로또 번호는 중복되지 않는다`() {
        assertDoesNotThrow {
            Lotto.of(1, 2, 3, 4, 5, 6)
        }
    }

    @Test
    fun `로또 번호가 중복되면 예외가 발생한다`() {
        assertThrows<IllegalArgumentException> {
            Lotto.of(1, 1, 1, 1, 1, 1)
        }
    }

    @ParameterizedTest
    @CsvSource("1,true", "2,true", "3,true", "7,false", "8,false")
    fun `특정 번호가 포함되어 있는지 확인한다`(
        number: Int,
        expected: Boolean,
    ) {
        val lotto = Lotto.of(1, 2, 3, 4, 5, 6)
        assertThat(lotto.contains(LottoNumber.from(number))).isEqualTo(expected)
    }
}
