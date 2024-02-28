package utils

import model.Lottery

interface LotteriesGenerationStrategy {
    fun issueLotteries(): List<Lottery>
}
