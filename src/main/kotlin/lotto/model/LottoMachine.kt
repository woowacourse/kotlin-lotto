package lotto.model

class LottoMachine {
    private val lottoNumbersGenerator = LottoNumbersGenerator()

    fun createLottos(lottoCount: Int): Lottos {
        val lottoBundle = mutableListOf<Lotto>()
        repeat(lottoCount) {
            lottoBundle.add(createLotto())
        }
        return Lottos(lottoBundle.toList())
    }

    private fun createLotto(): Lotto {
        val lottoNumbers = lottoNumbersGenerator.generateLottoNumbers()
        return Lotto(lottoNumbers)
    }
}
