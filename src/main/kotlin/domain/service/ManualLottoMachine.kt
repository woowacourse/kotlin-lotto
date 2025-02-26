package domain.service

import domain.model.LottoNumber
import domain.model.lotto.Lotto

class ManualLottoMachine(private val lotto: List<Int>) : LottoMachine {
    override fun generate(): Lotto {
        return Lotto(lotto.map { LottoNumber(it) })
    }
}
