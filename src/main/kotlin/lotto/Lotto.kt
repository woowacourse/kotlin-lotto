package lotto

class Lotto private constructor(
    val numberList: List<LottoNumber>,
) {
    init {
        require(numberList.sortedBy { it.value } == numberList) { LOTTO_NOT_SORTED }
    }

    companion object {
        const val LOTTO_NUMBER_QUANTITY = 6
        const val LOTTO_NOT_SORTED = "[ERROR] 로또 번호가 정렬되지 않았습니다."

        fun createOrNull(numberList: List<LottoNumber>): Lotto? =
            when {
                numberList.size != LOTTO_NUMBER_QUANTITY -> null
                numberList.distinctBy { it.value }.size != numberList.size -> null
                else -> Lotto(numberList)
            }
    }
}
