package lotto.model.lottery.strategy

import lotto.model.lottery.Lottery

interface LotteriesGenerationStrategy {
    fun issueLotteries(): List<Lottery>
}
