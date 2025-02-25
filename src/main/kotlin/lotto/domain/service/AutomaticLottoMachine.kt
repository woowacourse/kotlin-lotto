package lotto.domain.service

import lotto.domain.model.Lotto
import lotto.domain.model.LottoOrder

class AutomaticLottoMachine {
    fun generate(order: LottoOrder): List<Lotto> = List(order.count) { Lotto.create() }
}
