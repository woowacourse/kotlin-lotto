package lotto.domain.service

import lotto.domain.model.Lotto
import lotto.domain.model.LottoOrder
import lotto.domain.value.LottoNumber

class ManualLottoMachine {
    fun generate(order: LottoOrder): List<Lotto> =
        order.numbers.map { numbers ->
            Lotto(numbers.map { LottoNumber.from(it) })
        }
}
