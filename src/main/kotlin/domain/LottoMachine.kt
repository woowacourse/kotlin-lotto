package domain

object LottoMachine {
    fun generateAutoLottos(count: Int, lottoGenerator: LottoGenerator): Lottos {
        return Lottos(List(count) { lottoGenerator.generateLotto() })
    }

    fun generateManualLottos(lottos: List<IntArray>): Lottos {
        return Lottos(lottos.map { Lotto(*it) })
    }
}
