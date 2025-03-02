package lotto.domain

class LottoMachine {
    fun produce(generator: LottoGenerator): List<Lotto> {
        val lottos: List<Lotto> = generator.generate()
        return lottos
    }
}
