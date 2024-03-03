package lottogame.model

import lottogame.fixture.createLottoNumbers
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BonusLottoNumberTest {
    @Test
    fun `보너스 넘버는 우승 로또 번호와 중복될 수 없다`() {
        // given
        val lottoNumber: LottoNumber = GeneralLottoNumber(6)
        val winningLotto: List<LottoNumber> = createLottoNumbers(1, 2, 3, 4, 5, 6)
        // then
        val bonusNumber = BonusLottoNumber.ofNullable(lottoNumber, winningLotto)
        // when
        assertThat(bonusNumber).isNull()
    }
}
