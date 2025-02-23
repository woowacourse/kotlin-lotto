package lotto.domain

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class WinningLottoTest {
    @Test
    fun `로또 번호와 보너스 번호가 중복되면 오류가 발생한다`() {
        assertThrows<IllegalArgumentException> {
            WinningLotto(
                winningLottoNumber =
                    Lotto(
                        listOf(
                            LottoNumber(1),
                            LottoNumber(2),
                            LottoNumber(3),
                            LottoNumber(4),
                            LottoNumber(5),
                            LottoNumber(6),
                        ),
                    ),
                winningBonusNumber = LottoNumber(1),
            )
        }
    }
}
