package lotto

import lotto.domain.Lotto
import lotto.domain.LottoNumber
import lotto.domain.WinningLotto
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class WinningLottoTest {
    @Test
    fun `보너스 볼은 당첨 번호와 중복되지 않는다`() {
        val bonusNum: LottoNumber = LottoNumber(6)
        val lottoNums = Lotto(listOf(1, 2, 3, 4, 5, 6).map { LottoNumber(it) })

        assertThrows<IllegalArgumentException> { WinningLotto(lottoNums, bonusNum) }
    }
}
