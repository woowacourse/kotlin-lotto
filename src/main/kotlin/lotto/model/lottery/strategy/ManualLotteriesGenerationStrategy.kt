package lotto.model.lottery.strategy

import lotto.model.lottery.Lottery

class ManualLotteriesGenerationStrategy(
    private val manualLotteriesNumbers: List<List<Int>>,
) : LotteriesGenerationStrategy {
    override fun issueLotteries(): List<Lottery> {
        return manualLotteriesNumbers.map { Lottery.fromInput(it) }
    }
}
