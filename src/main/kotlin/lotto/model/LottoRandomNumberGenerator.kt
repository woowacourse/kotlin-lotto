package lotto.model

class LottoRandomNumberGenerator : LottoNumberGenerator {
    override fun generate(): List<LottoNumber> =
        LottoNumber.LOTTO_NUMBER_RANGE.shuffled().take(Lotto.LOTTO_SIZE).sorted()
            .map { LottoNumber.of(it) }
}
