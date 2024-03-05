package lotto.model.lottery.strategy

import lotto.model.lottery.Lottery

class ExplicitLotteriesGenerationStrategy(
    private val explicitLotteriesNumbers: List<Set<Int>>,
) : LotteriesGenerationStrategy {
    override fun issueLotteries(): List<Lottery> = explicitLotteriesNumbers.map { Lottery.fromSet(it) }
}
