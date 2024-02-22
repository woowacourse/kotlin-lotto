package utils

import model.Lotto

class ExplicitLottoGenerationStrategy(val lotteries: List<Lotto>) : LottoGenerationStrategy {
    override fun generateLotteries() = lotteries
}
