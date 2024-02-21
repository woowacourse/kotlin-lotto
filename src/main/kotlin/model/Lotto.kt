package model

class Lotto(val numbers: List<Int>) {
    init {
        require(validateCount(numbers)) { EXCEPTION_INVALID_COUNT }
        require(validateDuplicate(numbers)) { EXCEPTION_DUPLICATED_NUMBER }
    }

    private fun validateCount(numbers: List<Int>) = numbers.size == 6

    private fun validateDuplicate(numbers: List<Int>) = numbers.size == numbers.toSet().size

    fun getCountOfMatch(numbers: List<Int>) = this.numbers.intersect(numbers).size

    fun hasBonus(bonus: Int) = numbers.contains(bonus)

    companion object {
        const val EXCEPTION_INVALID_COUNT = "로또 번호는 6개여야 합니다"
        const val EXCEPTION_DUPLICATED_NUMBER = "로또 번호에 중복이 없어야 합니다"
    }
}
