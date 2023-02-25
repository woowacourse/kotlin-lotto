package domain

object LottoMachine {
    fun generateAutoLottos(count: Int, lottoGenerator: LottoGenerator): List<Lotto> {
        return (1..count).map { lottoGenerator.generateLotto() }
    }

    fun generateManualLottos(lottos: List<IntArray>): List<Lotto> {
        return lottos.map { Lotto(*it) }
    }
}
