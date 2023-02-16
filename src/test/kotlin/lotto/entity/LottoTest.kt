package lotto.entity

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class LottoTest {
    @Test
    fun `(1,2,3,4,5,6,7) 로또 번호의 개수가 6개가 아닐 경우 예외가 발생한다`() {
        assertThrows<IllegalArgumentException> { Lotto(listOf(1, 2, 3, 4, 5, 6, 7)) }
    }

    @Test
    fun `(1,2,3,3,5,6) 로또 번호끼리 중복 될 경우 예외가 발생한다`() {
        assertThrows<IllegalArgumentException> { Lotto(listOf(1, 2, 3, 3, 5, 6)) }
    }

    @Test
    fun `(1,2,3,4,5,6), (3,4,5,6,7,8) 두 로또의 같은 숫자의 개수는 4개이다`() {
        val lotto1 = Lotto(listOf(1, 2, 3, 4, 5, 6))
        val lotto2 = Lotto(listOf(3, 4, 5, 6, 7, 8))
        assertThat(lotto1.matchLottoNumberCount(lotto2)).isEqualTo(4)
    }

    @Test
    fun `(1,2,3,4,5,6) 로또에서 보너스 번호 3은 포함된다`() {
        val lotto = Lotto(listOf(1, 2, 3, 4, 5, 6))
        val bonus = LottoNumber(3)
        assertThat(lotto.isMatchBonus(bonus)).isTrue
    }
}
