package domain

class LottoMachine(val lottoGenerator: LottoGenerator) {
    fun generateAutoLottos(count: Int): List<Lotto> {
        return (1..count).map { lottoGenerator.generateLotto() }
    }

    fun generateManaulLottos(lottos: List<String>): List<Lotto> {
        return lottos.map { Lotto(it.trim().split(",").map { LottoNumber.from(it.toInt()) }) }
    }
}
