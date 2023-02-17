package domain

class RandomLottoGenerator : LottoGenerator {
    override fun generate(): Lotto {
        val randomNumbers: List<Int> = LottoNumber.LOTTO_NUMBER_RANGE.shuffled().take(6).sorted()
        val lottoNumbers: Set<LottoNumber> = randomNumbers.map { LottoNumber.of(it) }.toSet()
        return Lotto(lottoNumbers)
    }
}
