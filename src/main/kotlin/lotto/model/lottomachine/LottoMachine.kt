package lotto.model.lottomachine

import lotto.model.Lotto
import lotto.model.LottoNumber
import lotto.model.Lottos

interface LottoMachine {
    val lottoNumberBundle: Set<LottoNumber>
        get() = (LottoNumber.MIN_NUMBER..LottoNumber.MAX_NUMBER).map { number -> LottoNumber(number) }.toSet()

    fun createLotto(): Lotto

    fun createLottos(lottoCount: Int): Lottos
}
