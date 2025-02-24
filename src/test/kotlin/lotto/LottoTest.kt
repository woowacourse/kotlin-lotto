package lotto

import lotto.domain.Lotto
import lotto.domain.LottoNumber
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class LottoTest {
    @Test
    fun `로또는 6개의 숫자를 가진다`() {
        val nums = listOf(1, 2, 3, 4, 5, 6).map { LottoNumber(it) }
        val lotto = Lotto(nums)
        assertThat(lotto.lottoNums.size).isEqualTo(6)
    }

    @Test
    fun `자동 생성된 로또와 당첨 번호를 비교할 수 있다`() {
        val lotto = Lotto(listOf(1, 2, 3, 4, 5, 6).map { LottoNumber(it) })
        val winningLotto = Lotto(listOf(1, 2, 3, 4, 5, 9).map { LottoNumber(it) })
        val result = lotto.compareWithWinningLotto(winningLotto)

        assertThat(result).isEqualTo(5)
    }

    @Test
    fun `자동 생성된 로또와 보너스 번호를 비교할 수 있다`() {
        val lotto = Lotto(listOf(1, 2, 3, 4, 5, 6).map { LottoNumber(it) })
        val bonusNumber = LottoNumber(3)
        val result = lotto.compareWithBonusNumber(bonusNumber)

        assertThat(result).isTrue()
    }
}
