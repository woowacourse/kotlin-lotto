package lotto.domain

class ManualLottoGenerator(
    private val numbers: List<List<Int>>,
) : LottoGenerator {
    override fun generate(): List<Lotto> =
        numbers.map { lottoNumbers: List<Int> ->
            Lotto(lottoNumbers)
        }
}

fun ManualLottoGenerator(numbers: List<Int>): ManualLottoGenerator = ManualLottoGenerator(listOf(numbers))
