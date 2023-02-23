package lotto.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class WinningLottoTest {

    @Test
    fun `보너스 번호를 가진다`() {
        assertThat(
            winningLottoOf(
                mainLottoNumbers = intArrayOf(3, 45, 34, 1, 2, 4),
                bonusLottoNumber = 10,
            ).bonusLottoNumber.value,
        ).isEqualTo(10)
    }

    @Test
    fun `메인과 보너스 번호를 포함한 모든 번호는 서로 중복되지 않는다`() {
        assertThrows<IllegalArgumentException> {
            winningLottoOf(mainLottoNumbers = intArrayOf(1, 2, 3, 4, 5, 6), bonusLottoNumber = 6)
        }
    }

    companion object {
        private fun winningLottoOf(vararg mainLottoNumbers: Int, bonusLottoNumber: Int): WinningLotto =
            WinningLotto(Lotto(mainLottoNumbers.map { LottoNumber(it) }.toSet()), LottoNumber(bonusLottoNumber))
    }
}
