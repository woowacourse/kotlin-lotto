package domain.model

import domain.model.lotto.Lotto
import domain.model.machine.LottoMachine

class FakeAutoLottoMachine(private val fixedNumber: Lotto) : LottoMachine {
    override fun generate(): Lotto {
        return fixedNumber
    }
}
