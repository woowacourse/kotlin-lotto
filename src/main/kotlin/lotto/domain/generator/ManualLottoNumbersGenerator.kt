package lotto.domain.generator

import lotto.domain.model.LottoNumber

class ManualLottoNumbersGenerator(private val numbers: List<List<Int>>) : LottoNumbersGenerator {
    private var count: Int = 0

    override fun generate(): List<LottoNumber> {
        require(count < numbers.size) { INVALID_MANUAL_LOTTO_NUMBERS_SIZE_MESSAGE }

        return numbers[count++].map { LottoNumber.from(it) }
    }

    companion object {
        private const val INVALID_MANUAL_LOTTO_NUMBERS_SIZE_MESSAGE = "수동 로또 구매 횟수 만큼만 생성 가능합니다."
    }
}
