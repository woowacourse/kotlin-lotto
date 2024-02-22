package utils

import model.Lotto

interface LottoGenerationStrategy {
    fun generateLotteries(): List<Lotto>
}
