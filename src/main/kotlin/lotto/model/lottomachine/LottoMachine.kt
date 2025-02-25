package lotto.model.lottomachine

import lotto.model.Lotto
import lotto.model.LottoCount
import lotto.model.Lottos

interface LottoMachine {
    fun createLotto(): Lotto

    fun createLottos(lottoCount: LottoCount): Lottos
}
