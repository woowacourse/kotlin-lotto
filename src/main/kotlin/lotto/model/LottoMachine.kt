package lotto.model

class LottoMachine {
    fun createLotto(lottoNumbers: List<LottoNumber>): Lotto = Lotto.from(lottoNumbers)
}
