package lotto

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class LottoTest {
    @Test
    fun `로또는 6개의 숫자를 가진다`() {
        val lotto = lottoOf(1, 2, 3, 4, 5, 6)
        assertThat(lotto.lottoNums.size).isEqualTo(6)
    }

    @Test
    fun `로또 번호는 중복되지 않는다`() {
        assertThrows<IllegalArgumentException> {
            lottoOf(1, 2, 3, 4, 6, 6)
        }
    }

    @Test
    fun `자동 생성된 로또와 당첨 번호를 비교할 수 있다`() {
        val lotto = lottoOf(1, 2, 3, 4, 5, 6)
        val winningNumbers = lottoOf(1, 2, 3, 4, 5, 9)

        val result = lotto.compareWithWinningLotto(winningNumbers)

        assertThat(result).isEqualTo(5)
    }

    @Test
    fun `자동 생성된 로또와 보너스 번호를 비교할 수 있다`() {
        val lotto = lottoOf(1, 2, 3, 4, 5, 6)

        val bonusNumber: Int = 3
        val result = lotto.compareWithBonusNumber(bonusNumber)

        assertThat(result).isTrue()
    }
}
