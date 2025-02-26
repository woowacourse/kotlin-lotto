package domain.service

import domain.model.lotto.Lotto
import domain.model.number.LottoNumber

class ManualLottoMachine(private val lotto: List<Int>) : LottoMachine {
    override fun generate(): Lotto {
        return Lotto(lotto.map { LottoNumber(it) })
    }
}
