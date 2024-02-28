package utils

import model.Lottery

class ExplicitLotteriesGenerationStrategy(
    private val explicitLotteriesNumbers: List<Set<Int>>,
) : LotteriesGenerationStrategy {
    override fun issueLotteries(): List<Lottery> = explicitLotteriesNumbers.map { Lottery.fromSet(it) }
}
