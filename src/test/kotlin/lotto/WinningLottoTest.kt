package lotto

import lotto.model.Lotto
import lotto.model.LottoNumber
import lotto.model.Rank
import lotto.model.WinningLotto
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatIllegalArgumentException
import org.junit.jupiter.api.Assertions.assertDoesNotThrow
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class WinningLottoTest {
    private lateinit var winningNumber: Lotto

    @BeforeEach
    fun setUp() {
        winningNumber = Lotto(1, 2, 3, 4, 5, 6)
    }

    @Test
    fun `당첨 로또는 당첨번호와 보너스 번호를 갖는다`() {
        val bonusNumber = LottoNumber(7)
        val winningLotto = WinningLotto(winningNumber, bonusNumber)

        assertAll(
            { assertThat(winningLotto.winningNumbers).isEqualTo(winningNumber) },
            { assertThat(winningLotto.bonusNumber).isEqualTo(LottoNumber(7)) },
        )
    }

    @Test
    fun `당첨 로또는 당첨 번호와 보너스 번호가 중복될 경우 예외를 발생한다`() {
        val bonusNumber = LottoNumber(6)

        assertThatIllegalArgumentException()
            .isThrownBy {
                WinningLotto(winningNumber, bonusNumber)
            }.withMessage("입력한 보너스 번호 6은 당첨 번호와 중복됩니다. 보너스 번호는 당첨 번호와 중복될 수 없습니다.")
    }

    @Test
    fun `당첨 로또는 당첨 번호와 보너스 번호가 중복되지 않은 경우 예외를 발생하지 않는다`() {
        val bonusNumber = LottoNumber(7)
        assertDoesNotThrow {
            WinningLotto(winningNumber, bonusNumber)
        }
    }

    @Test
    fun `구입한 로또를 넣어주면, 등수를 반환한다`() {
        val lotto = Lotto(1, 2, 3, 4, 5, 6)
        val bonusNumber = LottoNumber(7)
        val winningLotto = WinningLotto(winningNumber, bonusNumber)

        assertThat(winningLotto.findLottoRank(lotto)).isEqualTo(Rank.FIRST)
    }
}
