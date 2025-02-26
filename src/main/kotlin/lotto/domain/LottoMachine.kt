package lotto.domain

class LottoMachine {
    fun makeLotto(generator: LottoGenerator): List<Lotto> {
        val lottos: List<Lotto> = generator.generate()
        return lottos
    }
}
