package lotto.domain.service

import lotto.domain.model.Lotto
import lotto.domain.value.LottoCount

interface LottoMachine {
    fun generate(lottoCount: LottoCount): List<Lotto>
}
