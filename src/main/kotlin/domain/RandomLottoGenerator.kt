package domain

object RandomLottoGenerator : LottoGenerator {
    private val lottoNumbers = LottoNumber.NUMBERS

    override fun generateLotto(): Lotto = Lotto(lottoNumbers.values.shuffled().take(6).toSet())
}
