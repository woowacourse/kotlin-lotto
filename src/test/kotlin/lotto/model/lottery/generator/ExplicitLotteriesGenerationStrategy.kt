package lotto.model.lottery.generator

import lotto.model.lottery.Lottery

class ExplicitLotteriesGenerationStrategy(
    private val explicitLotteriesNumbers: List<Set<Int>>,
) : LotteriesGenerationStrategy {
    override fun issueLotteries(): List<Lottery> = explicitLotteriesNumbers.map { Lottery.fromSet(it) }
}
