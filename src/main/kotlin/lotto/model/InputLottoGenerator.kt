package lotto.model

import lotto.entity.Lotto

class InputLottoGenerator(val input: () -> Lotto) : LottoGenerator {
    override fun generate(): Lotto {
        return input()
    }
}
