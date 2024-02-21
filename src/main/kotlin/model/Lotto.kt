package model

class Lotto(private val numbers: List<LottoNumber>) {
    init {
        require(validateCount(numbers)) { EXCEPTION_INVALID_COUNT }
        require(validateDuplicate(numbers)) { EXCEPTION_DUPLICATED_NUMBER }
    }

    private fun validateCount(numbers: List<LottoNumber>) = numbers.size == 6

    private fun validateDuplicate(numbers: List<LottoNumber>) = numbers.size == numbers.toSet().size

    fun getCountOfMatch(lotto: Lotto) = numbers.intersect(lotto.numbers).size

    fun hasBonus(bonus: Bonus) = numbers.contains(LottoNumber(bonus.number))


    override fun toString() = "[${numbers.joinToString(", ")}]\n"

    companion object {
        const val EXCEPTION_INVALID_COUNT = "로또 번호는 6개여야 합니다"
        const val EXCEPTION_DUPLICATED_NUMBER = "로또 번호에 중복이 없어야 합니다"

        fun fromList(numbers: List<Int>): Lotto {
            return Lotto(numbers.map { LottoNumber(it) })
        }
    }
}
