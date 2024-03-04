package lotto.model

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class LottoTest {
    @Test
    fun `올바른 로또에 대해서, 에러를 던지지 않아야한다`() {
        val numbers =
            setOf(
                LottoNumber(1),
                LottoNumber(2),
                LottoNumber(3),
                LottoNumber(4),
                LottoNumber(5),
                LottoNumber(6),
            )
        Assertions.assertDoesNotThrow { numbers }
    }

    @Test
    fun `로또 숫자가 중복인 경우에 대해서, 에러를 던져야 한다`() {
        val numbers =
            setOf(
                LottoNumber(1),
                LottoNumber(2),
                LottoNumber(3),
                LottoNumber(5),
                LottoNumber(5),
                LottoNumber(5),
            )
        assertThrows<IllegalArgumentException> {
            Lotto(numbers)
        }
    }
}
