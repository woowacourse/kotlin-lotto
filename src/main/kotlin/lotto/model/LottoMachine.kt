package lotto.model

class LottoMachine(
    private val lottoGenerator: LottoGenerator,
) {
    fun generateLotto(): Lotto = lottoGenerator.getLottoNumbers()
}
