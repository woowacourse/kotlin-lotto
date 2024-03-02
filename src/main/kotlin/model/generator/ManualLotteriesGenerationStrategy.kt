package model.generator

import model.Lottery

class ManualLotteriesGenerationStrategy(
    private val manualLotteriesNumbers: List<List<String>>,
) : LotteriesGenerationStrategy {
    override fun issueLotteries(): List<Lottery> {
        return manualLotteriesNumbers.map { Lottery.fromInput(it) }
    }
}
