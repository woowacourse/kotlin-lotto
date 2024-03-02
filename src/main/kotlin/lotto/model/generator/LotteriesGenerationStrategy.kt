package lotto.model.generator

import lotto.model.Lottery

interface LotteriesGenerationStrategy {
    fun issueLotteries(): List<Lottery>
}
