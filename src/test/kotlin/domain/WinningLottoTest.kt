package domain

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatIllegalArgumentException
import org.junit.jupiter.api.Test

class WinningLottoTest {
    @Test
    fun `로또 번호, 로또 번호와 중복되지 않는 보너스 번호로 이루어진 당첨 번호를 생성할 수 있다`() {
        val winningLotto = WinningLotto(listOf(1, 2, 3, 4, 5, 6), 10)
        assertThat(winningLotto.lotto).isEqualTo(Lotto.create(listOf(1, 2, 3, 4, 5, 6)))
        assertThat(winningLotto.bonusNumber).isEqualTo(LottoNumber(10))
    }

    @Test
    fun `보너스 번호가 로또 번호와 중복되면 에러가 발생한다`() {
        val numbers = listOf(1, 2, 3, 4, 5, 6)
        val bonusNumber = 3
        assertThatIllegalArgumentException()
            .isThrownBy { WinningLotto(numbers, bonusNumber) }
            .withMessage("보너스 번호는 당첨 번호와 중복될 수 없습니다. \n잘못된 값 : ${Lotto.create(numbers)}, ${LottoNumber(bonusNumber)}")
    }
}
