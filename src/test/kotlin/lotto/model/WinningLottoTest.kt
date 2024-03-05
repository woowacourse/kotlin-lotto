package lotto.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class WinningLottoTest {
    @Test
    fun `내가 구매한 로또와 당첨 결과를 비교하여 당첨 현황을 반환한다`() {
        val winningNumbers = Lotto(1, 2, 3, 4, 5, 6)
        val bonusNumber = LottoNumber.valueOf(7)
        val mine = listOf(Lotto(1, 2, 3, 4, 5, 6), Lotto(1, 2, 3, 4, 5, 7))

        val expected = mapOf(WinningRank.FIRST to 1, WinningRank.SECOND to 1)
        val actual = WinningLotto(winningNumbers, bonusNumber!!).generateWinningStatus(mine)
        assertThat(actual).isEqualTo(expected)
    }
}
