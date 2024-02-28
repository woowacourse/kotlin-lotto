package lotto.model

object RandomLottosGenerator : LottosGenerator {
    override fun generate(numberOfLottos: Int): List<Lotto> =
        List(numberOfLottos) {
            Lotto(generateLottoNumbers())
        }

    private fun generateLottoNumbers() =
        LottoNumbers(
            generateRandomNumbers().map { number -> generateLottoNumber(number) },
        )

    private fun generateRandomNumbers() =
        LottoNumber.LOTTO_NUMBER_RANGE
            .shuffled()
            .take(LottoNumbers.LOTTO_NUMBERS_SIZE)
            .sorted()

    private fun generateLottoNumber(number: Int) = LottoNumber.of(number)
}
