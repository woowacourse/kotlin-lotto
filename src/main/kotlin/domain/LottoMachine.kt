package domain

class LottoMachine(val lottoGenerator: LottoGenerator) {
    fun generateAutoLottos(count: Int): List<Lotto> {
        return (1..count).map { lottoGenerator.generateLotto() }
    }

    fun generateManualLottos(lottos: List<IntArray>): List<Lotto> {
        return lottos.map { Lotto(*it) }
    }
}
