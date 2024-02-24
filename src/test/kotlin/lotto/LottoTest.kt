package lotto

import lotto.model.LottoNumbers
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows

class LottoTest {
    @Test
    fun `로또 번호의 개수가 6개보다 적으면 예외가 발생한다`() {
        assertThrows<IllegalArgumentException> {
            LottoNumbers.lottoNumbersOf(listOf(1, 2, 3, 4))
        }
    }

    @Test
    fun `로또 번호의 개수가 6개보다 많으면 예외가 발생한다`() {
        assertThrows<IllegalArgumentException> {
            LottoNumbers.lottoNumbersOf(listOf(1, 2, 3, 4, 5, 6, 7))
        }
    }

    @Test
    fun `로또 번호의 개수가 6개면 예외가 발생하지 않는다`() {
        assertDoesNotThrow { LottoNumbers.lottoNumbersOf(listOf(1, 2, 3, 4, 5, 6)) }
    }

    @Test
    fun `로또 번호에 중복이 있을 경우 예외가 발생한다`() {
        assertThrows<IllegalArgumentException> {
            LottoNumbers.lottoNumbersOf(listOf(1, 2, 3, 4, 5, 5))
        }
    }

    @Test
    fun `로또 번호에 중복이 없을 경우 예외가 발생하지 않는다`() {
        assertDoesNotThrow { LottoNumbers.lottoNumbersOf(listOf(11, 12, 13, 25, 26, 27)) }
    }
}
