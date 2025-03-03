package lotto.model

class ManualLottoGenerator(private val manualNumbersList: List<Int>) : LottoGenerator {
    override fun generate(): Lotto {
        return Lotto.from(manualNumbersList) ?: error(INVALID_LOTTO_NUMBER)
    }

    companion object {
        const val INVALID_LOTTO_NUMBER = "잘못된 로또 번호입니다."
    }
}
