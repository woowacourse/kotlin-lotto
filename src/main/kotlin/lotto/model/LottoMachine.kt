package lotto.model

class LottoMachine {
    private val lottoNumbersGenerator = LottoNumbersGenerator()

    fun createLotto(): Lotto = Lotto(lottoNumbersGenerator.generateLottoNumbers())
}
