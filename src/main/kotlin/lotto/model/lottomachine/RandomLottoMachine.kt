package lotto.model.lottomachine

import lotto.model.Lotto
import lotto.model.LottoNumber
import lotto.model.LottoNumber.Companion.MAX_NUMBER
import lotto.model.LottoNumber.Companion.MIN_NUMBER
import lotto.model.Lottos

class RandomLottoMachine : LottoMachine {
    override fun createLottos(lottoCount: Int): Lottos {
        val lottoBundle = List(lottoCount) { createLotto() }
        return Lottos(lottoBundle)
    }

    override fun createLotto(): Lotto {
        val lottoNumber =
            (MIN_NUMBER..MAX_NUMBER)
                .shuffled()
                .take(Lotto.LOTTO_NUMBERS_COUNT)
                .map { number -> LottoNumber(number) }
                .sortedBy { lottoNumber -> lottoNumber.number }
        return Lotto(lottoNumber)
    }
}
