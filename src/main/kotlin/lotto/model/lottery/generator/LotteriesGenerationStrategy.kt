package lotto.model.lottery.generator

import lotto.model.lottery.Lottery

interface LotteriesGenerationStrategy {
    fun issueLotteries(): List<Lottery>
}
