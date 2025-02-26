package lotto.domain

class LottoMachine {
    fun getLotto(generator: LottoGenerator): List<Lotto> {
        val lottos: List<Lotto> = generator.generate()
        return lottos
    }
}
