package domain

import domain.lotto.Lotto
import domain.lotto.LottoBundleDto
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
        val winningNumbers = WinningNumbers(Lotto(1, 2, 3, 4, 5, 6), LottoNumber.of(7))

        val purchasedLottoBundle = LottoBundleDto(
            listOf(
                Lotto(1, 2, 3, 4, 5, 6),
                Lotto(2, 3, 4, 5, 6, 7),
                Lotto(3, 4, 5, 6, 7, 8),
            ),
        )
        val expected = WinningResultDto(
            listOf(
                ComparingResultDto(6, false),
                ComparingResultDto(5, true),
                ComparingResultDto(4, true),
            ),
        )
        // when
        val actual: WinningResultDto = winningNumbers.compareLottoBundle(purchasedLottoBundle)

        // then
        assertThat(actual).isEqualTo(expected)
    }
}
