package lotto.model.lottomachine

import lotto.model.Lotto
import lotto.model.LottoCount
import lotto.model.LottoNumber
import lotto.model.LottoNumber.Companion.MAX_NUMBER
import lotto.model.LottoNumber.Companion.MIN_NUMBER

object AutomaticLottoMachine {
    fun createLotto(): Lotto {
        val lottoNumber =
            (MIN_NUMBER..MAX_NUMBER)
                .shuffled()
                .take(Lotto.LOTTO_NUMBERS_COUNT)
                .map { number -> LottoNumber.from(number) }
                .sortedBy { lottoNumber -> lottoNumber.number }
        return Lotto(lottoNumber)
    }

    fun createLottoBundle(lottoCount: LottoCount): List<Lotto> = List(lottoCount.number) { createLotto() }
}
