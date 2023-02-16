package domain

object RandomLottoGenerator : LottoGenerator {
    private val lottoNumbers = (LottoNumber.MINIMUM_NUMBER..LottoNumber.MAXIMUM_NUMBER).map { LottoNumber(it) }
    override fun generateLotto(): Lotto = Lotto(lottoNumbers.shuffled().take(6).toSet())
}
