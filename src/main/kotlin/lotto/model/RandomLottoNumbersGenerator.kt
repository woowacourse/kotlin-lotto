package lotto.model

object RandomLottoNumbersGenerator : LottoNumbersGenerator {
    override fun generate(numberOfLottos: Int): List<LottoNumbers> =
        List(numberOfLottos) {
            LottoNumbers(
                LottoNumber.LOTTO_NUMBER_RANGE.shuffled()
                    .take(Lotto.LOTTO_SIZE)
                    .sorted()
                    .map { LottoNumber.of(it) },
            )
        }
}
