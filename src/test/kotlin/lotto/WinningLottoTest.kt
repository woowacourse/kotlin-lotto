package lotto

import lotto.model.Lotto
import lotto.model.LottoNumber
import lotto.model.LottoPurchaseAmount
import lotto.model.WinningLotto
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatIllegalArgumentException
import org.junit.jupiter.api.Assertions.assertDoesNotThrow
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class WinningLottoTest {
    private lateinit var winningNumber: Lotto
    private lateinit var purchaseMoney: LottoPurchaseAmount

    @BeforeEach
    fun setUp() {
        winningNumber = listOf(1, 2, 3, 4, 5, 6).toLotto()
        purchaseMoney = LottoPurchaseAmount(1_000)
    }

    @Test
    fun `당첨 로또는 당첨번호와 보너스 번호를 갖는다`() {
        val bonusNumber = LottoNumber(7)
        val winningLotto = WinningLotto(winningNumber, bonusNumber, purchaseMoney)

        assertThat(winningLotto.winningNumbers).isInstanceOf(Lotto::class.java)
        assertThat(winningLotto.bonusNumber).isEqualTo(LottoNumber(7))
    }

    @Test
    fun `당첨 로또는 당첨 번호와 보너스 번호가 중복될 경우 예외를 발생한다`() {
        val bonusNumber = LottoNumber(6)

        assertThatIllegalArgumentException()
            .isThrownBy {
                WinningLotto(winningNumber, bonusNumber, purchaseMoney)
            }.withMessage("보너스 번호는 당첨 번호와 중복될 수 없습니다.")
    }

    @Test
    fun `당첨 로또는 당첨 번호와 보너스 번호가 중복되지 않은 경우 예외를 발생하지 않는다`() {
        val bonusNumber = LottoNumber(7)
        assertDoesNotThrow {
            WinningLotto(winningNumber, bonusNumber, purchaseMoney)
        }
    }
}
