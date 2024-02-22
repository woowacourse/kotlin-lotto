package utils

import model.Lotto

interface LottoGenerationStrategy {
    fun generateLottos(): List<Lotto>
}
