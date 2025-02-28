package lotto.model

class LottoMachine {
    private val lottoNumbersGenerator = LottoNumbersGenerator()

    fun createManualLotto(lottoNumbers: List<LottoNumber>): Lotto = Lotto(lottoNumbers)

    fun createAutoLotto(): Lotto = Lotto(lottoNumbersGenerator.generateLottoNumbers())
}
