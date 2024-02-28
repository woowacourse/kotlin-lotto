package lotto.model

class FixedLottosGenerator(private val fixedNumbers: List<List<Int>>) : LottosGenerator {
    init {
        require(fixedNumbers.distinct().size == fixedNumbers.size) { DUPLICATE_LOTTOS_ERROR_MESSAGE }
    }

    override fun generate(numberOfLottos: Int): List<Lotto> =
        fixedNumbers.map { numbers ->
            Lotto(generateLottoNumbers(numbers))
        }

    private fun generateLottoNumbers(numbers: List<Int>) =
        LottoNumbers(
            numbers.sorted().map { number -> generateLottoNumber(number) },
        )

    private fun generateLottoNumber(number: Int) = LottoNumber.of(number)

    companion object {
        private const val DUPLICATE_LOTTOS_ERROR_MESSAGE = "구매할 로또는 중복이 허용되지 않습니다."
    }
}
