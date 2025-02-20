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

class WinningLottoTest {
    private lateinit var winningNumber: Lotto

    @BeforeEach
    fun setUp() {
        winningNumber =
            Lotto(
                listOf(
                    LottoNumber(1),
                    LottoNumber(2),
                    LottoNumber(3),
                    LottoNumber(4),
                    LottoNumber(5),
                    LottoNumber(6),
                ),
            )
    }

    @Test
    fun `당첨 로또는 당첨번호와 보너스 번호를 갖는다`() {
        // given
        val bonusNumber = LottoNumber(7)
        val winningLotto = WinningLotto(winningNumber, bonusNumber)

        assertThat(winningLotto.winningNumbers.numbers).isEqualTo(
            listOf(
                LottoNumber(1),
                LottoNumber(2),
                LottoNumber(3),
                LottoNumber(4),
                LottoNumber(5),
                LottoNumber(6),
            ),
        )
        assertThat(winningLotto.bonusNumber).isEqualTo(LottoNumber(7))
    }

    @Test
    fun `당첨 로또는 당첨 번호와 보너스 번호가 중복될 경우 예외를 발생한다`() {
        // given
        val bonusNumber = LottoNumber(6)

        assertThatIllegalArgumentException()
            .isThrownBy {
                WinningLotto(winningNumber, bonusNumber)
            }.withMessage("보너스 번호는 당첨 번호와 중복될 수 없습니다.")
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
        val lotto =
            Lotto(
                listOf(
                    LottoNumber(1),
                    LottoNumber(2),
                    LottoNumber(3),
                    LottoNumber(4),
                    LottoNumber(5),
                    LottoNumber(6),
                ),
            )
        val bonusNumber = LottoNumber(7)
        val winningLotto = WinningLotto(winningNumber, bonusNumber)

        assertThat(winningLotto.findLottoRank(lotto)).isEqualTo(Rank.FIRST)
    }
}
