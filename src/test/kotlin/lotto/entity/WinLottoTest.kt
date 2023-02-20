package lotto.entity

import lotto.model.Rank
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class WinLottoTest {
    @Test
    fun `보너스 번호 1과 당첨번호 1이 중복되면 예외가 발생한다`() {
        // given
        val winNumber = Lotto(listOf(1, 2, 3, 4, 5, 6))
        val bonus = LottoNumber.from(1)

        // when
        val thrown = assertThrows<IllegalArgumentException> { WinLotto(winNumber, bonus) }
        val except = "보너스 번호와 당첨 번호는 중복될 수 없습니다. 입력된 당첨 번호 : %s, 보너스 번호 : %d".format(
            winNumber.toString(),
            bonus.value
        )

        // then
        assertThat(thrown.message).isEqualTo(except)
    }

    @Test
    fun `당첨 번호 ((1,2,3,4,5,6), 7)과 로또 번호 (1,2,3,4,5,7) 을 비교하면 2등이다`() {
        // given
        val winNumber = Lotto(listOf(1, 2, 3, 4, 5, 6))
        val bonus = LottoNumber.from(7)
        val winLotto = WinLotto(winNumber, bonus)
        val lotto = Lotto(listOf(1, 2, 3, 4, 5, 7))

        // when
        val rank = winLotto.determineRank(lotto)

        // then
        assertThat(rank).isEqualTo(Rank.SECOND)
    }
}
