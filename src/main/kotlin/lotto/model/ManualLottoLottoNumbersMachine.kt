package lotto.model

class ManualLottoLottoNumbersMachine(
    private val numbersBundle: List<List<LottoNumber>>,
) : LottoNumbersGenerator {
    private var index: Int = 0

    override fun generate(): List<LottoNumber> = numbersBundle[index++]
}
