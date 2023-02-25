package domain

object LottoMachine {
    fun generateAutoLottos(count: Int, lottoGenerator: LottoGenerator): List<Lotto> {
        return List(count) { lottoGenerator.generateLotto() }
    }

    fun generateManualLottos(lottos: List<IntArray>): List<Lotto> {
        return lottos.map { Lotto(*it) }
    }
}
