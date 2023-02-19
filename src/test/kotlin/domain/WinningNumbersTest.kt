package domain

import domain.lotto.Lotto
import domain.lotto.LottoNumber
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatIllegalArgumentException
import org.junit.jupiter.api.Test
import util.PREFIX

class WinningNumbersTest {
    @Test
    fun `당첨 번호와 보너스 번호가 중복 되면 에러 발생`() {
        // given
        val lotto = Lotto(1, 2, 3, 4, 5, 6)
        val bonusNumber = LottoNumber.of(6)

        // when

        // then
        assertThatIllegalArgumentException()
            .isThrownBy { WinningNumbers(lotto, bonusNumber) }
            .withMessageContaining("$PREFIX 보너스 번호가 당첨 번호와 중복되면 안된다.")
    }

    @Test
    fun `당첨 번호를 비교하여 결과를 반환한다`() {
        // given
        val purchasedLotto = Lotto(1, 2, 3, 4, 5, 6)
        val winningNumbers = WinningNumbers(Lotto(1, 2, 3, 4, 5, 7), LottoNumber.of(6))
        val expected: Rank = Rank.SECOND

        // when
        val actual: Rank = winningNumbers.compareLotto(purchasedLotto)

        // then
        assertThat(actual).isEqualTo(expected)
    }
}
