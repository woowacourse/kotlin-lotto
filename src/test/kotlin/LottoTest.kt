import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class LottoTest {
    @Test
    fun `로또 번호의 개수가 6개가 아니라면 예외를 발생한다`() {
        val exception = assertThrows<IllegalArgumentException> {
            Lotto(
                listOf(
                    LottoNumber.from(1),
                    LottoNumber.from(2),
                    LottoNumber.from(3),
                    LottoNumber.from(4),
                    LottoNumber.from(5),
                    LottoNumber.from(6),
                    LottoNumber.from(7),
                ),
            )
        }
        assertEquals(Lotto.LOTTO_NUMBERS_COUNT_ERROR, exception.message)
    }
}
