package domain

class LottoGenerator() : LottoGeneratorInterface {
    override fun generateLotto(): Lotto {
        val possibleLottoNumbers = (LottoNumber.MINIMUM_LOTTO_NUMBER..LottoNumber.MAXIMUM_LOTTO_NUMBER)
        return Lotto(possibleLottoNumbers.shuffled().take(Lotto.LOTTO_NUMBERS_COUNT).sorted().map { LottoNumber(it) })
    }
}
