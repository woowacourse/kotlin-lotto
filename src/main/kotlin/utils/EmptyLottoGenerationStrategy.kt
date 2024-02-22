package utils

import model.Lotto

class EmptyLottoGenerationStrategy : LottoGenerationStrategy {
    override fun generateLotteries(): List<Lotto> = emptyList()
}
