package domain.service

import domain.model.lotto.Lotto

class FakeAutoLottoMachine(private val fixedNumber: Lotto) : LottoMachine {
    override fun generate(): Lotto {
        return fixedNumber
    }
}
