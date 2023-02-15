package domain

import model.Lotto
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class LottoTest {

    @Test
    fun `로또 번호가 6개가 아니면 예외를 발생한다`() {
        // given
        val lotto = listOf(1, 2, 3, 4, 5, 6)

        assertThrows<IllegalArgumentException> {
            Lotto(lotto)
        }
    }

    @Test
    fun `로또 번호가 1부터 45가 아니라면 예외를 발생한다`() {
        // given
        val lotto = listOf(1, 2, 2, 3, 4, 47)

        assertThrows<IllegalArgumentException> { Lotto(lotto) }
        Rank.valueOf(3,true)
    }

    @Test
    fun `로또의 번호가 중복이면 예외를 발생한다`() {
        // given
        val lotto = listOf(1, 2, 3, 6, 4, 5)

        assertThrows<IllegalArgumentException> { Lotto(lotto) }
    }


}