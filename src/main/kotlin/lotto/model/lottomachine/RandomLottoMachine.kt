package lotto.model.lottomachine

import lotto.model.Lotto
import lotto.model.Lottos

class RandomLottoMachine : LottoMachine {
    override fun createLottos(lottoCount: Int): Lottos {
        val lottoBundle = List(lottoCount) { createLotto() }
        return Lottos(lottoBundle)
    }

    override fun createLotto(): Lotto {
        val lottoNumber =
            lottoNumberBundle.shuffled().take(Lotto.LOTTO_NUMBERS_COUNT).sortedBy { lottoNumber -> lottoNumber.number }
        return Lotto(lottoNumber)
    }
}
