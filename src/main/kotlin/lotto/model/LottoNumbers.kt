package lotto.model

class LottoNumbers(
    val numberList: List<LottoNumber>,
) {
    fun include(number: LottoNumber): Boolean = numberList.contains(number)

    companion object {
        const val LOTTO_NUMBER_SIZE = 6

        fun create(numberList: List<LottoNumber>): LottoNumbers? {
            when {
                numberList.size != LOTTO_NUMBER_SIZE -> return null
                numberList.distinct().size != numberList.size -> return null
            }
            return LottoNumbers(numberList)
        }
    }
}
