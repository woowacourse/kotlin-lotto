package lotto.model

class LottoMachine {
    private val lottoNumbersGenerator = LottoNumbersGenerator()

    fun createLottos(lottoCount: Int): Lottos {
        val lottoBundle = List(lottoCount) { createLotto() }
        return Lottos(lottoBundle)
    }

    private fun createLotto(): Lotto {
        val lottoNumbers = lottoNumbersGenerator.generateLottoNumbers()
        return Lotto(lottoNumbers)
    }
}
