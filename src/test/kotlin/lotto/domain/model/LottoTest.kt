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
            val lottoNumbers = (1..6).map { LottoNumber(it) }
            Lotto(lottoNumbers)
        }
    }

    @Test
    fun `로또 번호가 6개가 아니면 예외가 발생한다`() {
        assertThrows<IllegalArgumentException> {
            val lottoNumbers = (1..5).map { LottoNumber(it) }
            Lotto(lottoNumbers)
        }
    }

    @Test
    fun `로또 번호는 중복되지 않는다`() {
        assertDoesNotThrow {
            val lottoNumbers = (1..6).map { LottoNumber(it) }
            Lotto(lottoNumbers)
        }
    }

    @Test
    fun `로또 번호가 중복되면 예외가 발생한다`() {
        assertThrows<IllegalArgumentException> {
            val lottoNumbers = (1..6).map { LottoNumber(1) }
            Lotto(lottoNumbers)
        }
    }

    @ParameterizedTest
    @CsvSource("1,true", "2,true", "3,true", "7,false", "8,false")
    fun `특정 번호가 포함되어 있는지 확인한다`(
        number: Int,
        expected: Boolean,
    ) {
        val lottoNumbers = (1..6).map { LottoNumber(it) }
        val lotto = Lotto(lottoNumbers)
        assertThat(lotto.contains(LottoNumber(number))).isEqualTo(expected)
    }
}
