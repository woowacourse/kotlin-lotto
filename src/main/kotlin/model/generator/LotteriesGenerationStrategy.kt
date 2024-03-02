package model.generator

import model.Lottery

interface LotteriesGenerationStrategy {
    fun issueLotteries(): List<Lottery>
}
