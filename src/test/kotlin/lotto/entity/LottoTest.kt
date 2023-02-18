package lotto.entity

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class LottoTest {
    @Test
    fun `(1,2,3,4,5,6,7) 로또 번호의 개수가 6개가 아닐 경우 예외가 발생한다`() {
        // given
        val lotto = listOf(1, 2, 3, 4, 5, 6, 7)

        // when
        var thrown = assertThrows<IllegalArgumentException> { Lotto(lotto) }
        var except = String.format(Lotto.ERROR_MESSAGE_WIN_NUMBER_IS_SIX, lotto.size)

        // then
        assertThat(thrown.message).isEqualTo(except)
    }

    @Test
    fun `(1,2,3,4,5,6), (3,4,5,6,7,8) 두 로또의 같은 숫자의 개수는 4개이다`() {
        // given
        val lotto1 = Lotto(listOf(1, 2, 3, 4, 5, 6))
        val lotto2 = Lotto(listOf(3, 4, 5, 6, 7, 8))

        // when
        val matchCount = lotto1.matchLottoNumberCount(lotto2)
        val except = 4

        // then
        assertThat(matchCount).isEqualTo(except)
    }

    @Test
    fun `(1,2,3,4,5,6) 로또에서 보너스 번호 3은 포함된다`() {
        // given
        val lotto = Lotto(listOf(1, 2, 3, 4, 5, 6))
        val bonus = LottoNumber.from(3)

        // when
        val isMatchBonus = lotto.isMatchBonus(bonus)

        // then
        assertThat(isMatchBonus).isTrue
    }
}
