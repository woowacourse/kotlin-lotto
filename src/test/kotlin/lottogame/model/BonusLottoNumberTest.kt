package lottogame.model

import lottogame.fixture.createLottoNumbers
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class BonusLottoNumberTest {
    @Test
    fun `보너스 넘버는 우승 로또 번호와 중복될 수 없다`() {
        // given
        val lottoNumber: LottoNumber = GeneralLottoNumber(6)
        val winningLotto: List<LottoNumber> = createLottoNumbers(1, 2, 3, 4, 5, 6)
        // when
        assertThrows<IllegalArgumentException> {
            BonusLottoNumber(lottoNumber, winningLotto)
        }
    }
}
