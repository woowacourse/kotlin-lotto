package utils

import model.Lotto

class ExplicitLottoGenerationStrategy(val lottos: List<Lotto>) : LottoGenerationStrategy {
    override fun generateLottos() = lottos
}
