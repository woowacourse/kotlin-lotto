package lottogame.model

import lottogame.model.generator.GeneralLottoNumber
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BonusLottoNumberTest {
    @Test
    fun `보너스 넘버는 우승 로또 번호와 중복될 수 없다`() {
        // given
        val lottoNumber: LottoNumber = GeneralLottoNumber(6)
        val winningLotto =
            listOf(
                GeneralLottoNumber(1),
                GeneralLottoNumber(2),
                GeneralLottoNumber(3),
                GeneralLottoNumber(4),
                GeneralLottoNumber(5),
                GeneralLottoNumber(6),
            )
        // then
        val bonusNumber = BonusLottoNumber.ofNullable(lottoNumber, winningLotto)
        // when
        assertThat(bonusNumber).isNull()
    }
}
