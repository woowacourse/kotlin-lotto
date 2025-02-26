package lotto.model.lottomachine

import lotto.model.Lotto
import lotto.model.LottoNumber

object ManualLottoMachine {
    fun createLotto(lottoNumbers: List<LottoNumber>): Lotto = Lotto(lottoNumbers)
}
