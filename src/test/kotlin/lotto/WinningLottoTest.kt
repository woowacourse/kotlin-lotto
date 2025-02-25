package lotto

import lotto.model.Lotto
import lotto.model.LottoNumber
import lotto.model.WinningLotto
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class WinningLottoTest {
    @Test
    fun `당첨번호가 중복되면 예외가 발생한다 `() {
        val exception =
            assertThrows<IllegalArgumentException> {
                WinningLotto(
                    Lotto(listOf(1, 2, 3, 4, 6, 6).map { LottoNumber(it) }),
                    LottoNumber(7),
                )
            }
        assertThat(exception).hasMessage("로또 번호가 중복될 수 없습니다.")
    }

    @Test
    fun `당첨번호가 6개가 아니면 예외가 발생한다 `() {
        val exception =
            assertThrows<IllegalArgumentException> {
                WinningLotto(
                    Lotto(listOf(1, 2, 3, 4, 5, 6, 7).map { LottoNumber(it) }),
                    LottoNumber(8),
                )
            }
        assertThat(exception).hasMessage("로또 번호는 6개여야 합니다.")
    }

    @Test
    fun `당첨 번호는 1부터 45 범위 내에 있지 않으면 예외가 발생한다 `() {
        val exception =
            assertThrows<IllegalArgumentException> {
                WinningLotto(
                    Lotto(listOf(1, 2, 3, 4, 5, 46).map { LottoNumber(it) }),
                    LottoNumber(6),
                )
            }
        assertThat(exception).hasMessage("로또 번호는 1에서 45 범위 내에서 있어야 합니다.")
    }

    @Test
    fun `당첨 번호는 보너스 번호와 중복되면 예외가 발생한다 `() {
        val exception =
            assertThrows<IllegalArgumentException> {
                WinningLotto(
                    Lotto(listOf(1, 2, 3, 4, 5, 6).map { LottoNumber(it) }),
                    LottoNumber(1),
                )
            }
        assertThat(exception).hasMessage("당첨 번호와 보너스 번호는 중복될 수 없습니다.")
    }

    @Test
    fun `보너스 번호는 1부터 45 범위 내에 있지 않으면 예외가 발생한다 `() {
        val exception =
            assertThrows<IllegalArgumentException> {
                WinningLotto(
                    Lotto(listOf(1, 2, 3, 4, 5, 6).map { LottoNumber(it) }),
                    LottoNumber(46),
                )
            }
        assertThat(exception).hasMessage("로또 번호는 1에서 45 범위 내에서 있어야 합니다.")
    }
}
