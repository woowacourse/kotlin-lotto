package lotto.model

class FixedLottoNumbersGenerator(private val fixedNumbers: List<List<Int>>) : LottoNumbersGenerator {
    init {
        require(fixedNumbers.distinct().size == fixedNumbers.size) { DUPLICATE_LOTTOS_ERROR_MESSAGE }
    }

    override fun generate(numberOfLottos: Int): List<LottoNumbers> = fixedNumbers.map { LottoNumbers(it.map(LottoNumber::of)) }

    companion object {
        private const val DUPLICATE_LOTTOS_ERROR_MESSAGE = "구매할 로또는 중복이 허용되지 않습니다."
    }
}
