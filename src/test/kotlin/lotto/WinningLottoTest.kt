package lotto

import lotto.domain.Lotto
import lotto.domain.LottoNumber
import lotto.domain.WinningLotto
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class WinningLottoTest {
    @Test
    fun `보너스 볼은 당첨 번호와 중복되지 않는다`() {
        val winningNumbers =
            listOf(
                LottoNumber(1),
                LottoNumber(2),
                LottoNumber(3),
                LottoNumber(4),
                LottoNumber(5),
                LottoNumber(6),
            )
        val winningLotto = Lotto(winningNumbers)
        val bonusNum: Int = 6

        assertThrows<IllegalArgumentException> { WinningLotto(winningLotto, bonusNum) }
    }

    @Test
    fun `당첨 번호는 정수 형태로 입력되어야 한다`() {
        val lottoNumbers =
            listOf(
                LottoNumber(1),
                LottoNumber(2),
                LottoNumber(3),
                LottoNumber(4),
                LottoNumber(5),
                LottoNumber(6),
            )
        val lottoNums = Lotto(lottoNumbers)
        val bonusNum: String = "lotto"

        assertThrows<IllegalArgumentException> { WinningLotto(lottoNums, bonusNum.toInt()) }
    }
}
