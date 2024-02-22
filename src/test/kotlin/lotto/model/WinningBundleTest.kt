package lotto.model

import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class WinningBundleTest {
    @ParameterizedTest
    @ValueSource(ints = [6, 1])
    fun `로또 게임에서 보너스 번호는 당첨 번호와 중복되면 안된다`(bonusNumber: Int) {
        val winningLotto = Lotto((1..6).map { LottoNumber(it) })

        assertThrows<IllegalArgumentException> {
            WinningBundle(winningLotto, LottoNumber(bonusNumber))
        }
    }
}
