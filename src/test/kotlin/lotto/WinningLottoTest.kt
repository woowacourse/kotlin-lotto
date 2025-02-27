package lotto

import lotto.domain.LottoNumber
import lotto.domain.WinningLotto
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class WinningLottoTest {
    @Test
    fun `보너스 볼은 당첨 번호와 중복되지 않는다`() {
        val winningNumbers = lottoOf(1, 2, 3, 4, 5, 6)
        val bonusNumber: LottoNumber = LottoNumber.of(6)

        assertThrows<IllegalArgumentException> { WinningLotto(winningNumbers, bonusNumber) }
    }

    @ParameterizedTest
    @ValueSource(ints = [7, 8, 9, 10, 22, 44])
    fun `보너스 볼 번호는 1~45 숫자 사이에 해당한다`(bonusNumber: Int) {
        val winningNumbers = lottoOf(1, 2, 3, 4, 5, 6)
        assertDoesNotThrow { WinningLotto(winningNumbers, LottoNumber.of(bonusNumber)) }
    }
}
