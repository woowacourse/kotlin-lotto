package domain.service

import domain.model.Lotto
import domain.model.LottoNumber

class ManualLottoMachine(private val lotto: List<Int>) : LottoMachine {
    override fun generate(): Lotto {
        return Lotto(lotto.map { LottoNumber(it) })
    }
}
