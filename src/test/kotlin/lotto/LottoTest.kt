package lotto

import lotto.domain.Lotto
import lotto.domain.LottoNumber
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class LottoTest {
    @Test
    fun `로또는 6개의 숫자를 가진다`() {
        val lottoNumbers =
            listOf(
                LottoNumber(1),
                LottoNumber(2),
                LottoNumber(3),
                LottoNumber(4),
                LottoNumber(5),
                LottoNumber(6),
            )
        val lotto = Lotto(lottoNumbers)
        assertThat(lotto.lottoNums.size).isEqualTo(6)
    }

    @Test
    fun `로또 번호는 중복되지 않는다`() {
        val lottoNumbers =
            listOf(
                LottoNumber(1),
                LottoNumber(2),
                LottoNumber(3),
                LottoNumber(4),
                LottoNumber(6),
                LottoNumber(6),
            )
        assertThrows<IllegalArgumentException> { Lotto(lottoNumbers) }
    }

    @Test
    fun `자동 생성된 로또와 당첨 번호를 비교할 수 있다`() {
        val lottoNumbers =
            listOf(
                LottoNumber(1),
                LottoNumber(2),
                LottoNumber(3),
                LottoNumber(4),
                LottoNumber(5),
                LottoNumber(6),
            )
        val winningLottoNumber =
            listOf(
                LottoNumber(1),
                LottoNumber(2),
                LottoNumber(3),
                LottoNumber(4),
                LottoNumber(5),
                LottoNumber(9),
            )
        val lotto = Lotto(lottoNumbers)
        val winningLotto = Lotto(winningLottoNumber)
        val result = lotto.compareWithWinningLotto(winningLotto)

        assertThat(result).isEqualTo(5)
    }

    @Test
    fun `자동 생성된 로또와 보너스 번호를 비교할 수 있다`() {
        val lottoNumbers =
            listOf(
                LottoNumber(1),
                LottoNumber(2),
                LottoNumber(3),
                LottoNumber(4),
                LottoNumber(5),
                LottoNumber(6),
            )
        val lotto = Lotto(lottoNumbers)
        val bonusNumber = "3"
        val result = lotto.compareWithBonusNumber(bonusNumber.toInt())

        assertThat(result).isTrue()
    }
}
