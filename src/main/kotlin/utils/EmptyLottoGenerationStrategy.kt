package utils

import model.Lotto

class EmptyLottoGenerationStrategy : LottoGenerationStrategy {
    override fun generateLottos(): List<Lotto> = emptyList()
}
