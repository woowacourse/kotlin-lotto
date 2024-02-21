package model

import utils.EmptyLottoGenerationStrategy
import utils.LottoGenerationStrategy

object LottoStore {
    private var lottoGenerationStrategy: LottoGenerationStrategy = EmptyLottoGenerationStrategy()

    fun setStrategy(lottoGenerationStrategy: LottoGenerationStrategy) {
        this.lottoGenerationStrategy = lottoGenerationStrategy
    }

    fun makeLotto(): List<Lotto> = lottoGenerationStrategy.generateLottos()
}
