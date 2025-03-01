package lotto.model

class ManualLottoGenerator(private val manualNumbersList: List<List<Int>>) : LottoGenerator {
    override fun generate(count: Int): List<Lotto> {
        require(manualNumbersList.size == count) { INVALID_DUPLICATED_MESSAGE }

        return manualNumbersList.map { numbers ->
            Lotto.from(numbers) ?: error(INVALID_LOTTO_NUMBER)
        }
    }

    companion object {
        const val INVALID_DUPLICATED_MESSAGE = "로또 개수와 입력된 번호 개수가 일치하지 않습니다."
        const val INVALID_LOTTO_NUMBER = "잘못된 로또 번호입니다."
    }
}
