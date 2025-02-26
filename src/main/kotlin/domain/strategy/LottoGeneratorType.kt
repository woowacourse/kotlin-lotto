package domain.strategy

import domain.model.Lotto

interface LottoGeneratorType {
    fun generateNumber(): List<Lotto>
}
