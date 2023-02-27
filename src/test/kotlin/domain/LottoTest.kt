package domain

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class LottoTest {
    @Test
    fun `로또 번호의 개수가 6개가 아니라면 예외를 발생한다`() {
        val exception = assertThrows<IllegalArgumentException> {
            Lotto(1, 2, 3, 4, 5, 6, 7)
        }
        assertEquals("[ERROR] 로또의 번호가 6개가 아닙니다.", exception.message)
    }

    @Test
    fun `로또가 중복된 로또 번호를 가지고 있으면 예외를 발생한다`() {
        val exception = assertThrows<IllegalArgumentException> {
            Lotto(1, 1, 3, 4, 5, 6)
        }
        assertEquals("[ERROR] 중복된 로또 번호가 있습니다.", exception.message)
    }

    @Test
    fun `(1,2,3,4,5,6)인 로또와 (1,2,3,4,5,7)인 당첨번호를 비교하면 일치하는 개수 5를 반환한다`() {
        // given
        val lotto = Lotto(1, 2, 3, 4, 5, 6)

        val winningLotto = Lotto(1, 2, 3, 4, 5, 7)

        // when
        val actual = lotto.getCountOfMatch(winningLotto)
        val expected = 5

        // then
        assertEquals(expected, actual)
    }
}
