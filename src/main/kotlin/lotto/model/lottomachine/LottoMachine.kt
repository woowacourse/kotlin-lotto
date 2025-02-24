package lotto.model.lottomachine

import lotto.model.Lotto
import lotto.model.Lottos

interface LottoMachine {
    fun createLotto(): Lotto

    fun createLottos(lottoCount: Int): Lottos
}
