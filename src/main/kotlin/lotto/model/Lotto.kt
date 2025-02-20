package lotto.model

class Lotto(
    private val numbers: List<LottoNumber> = generateLotto(),
    private val bonusNumber: LottoNumber? = null,
) {
    init {
        require(numbers.size == 6)
        require(numbers.size == numbers.toSet().size)
        require(!numbers.contains(bonusNumber))
    }

    fun getSize() = numbers.size

    fun getNumbers() = numbers.toList()

    fun getBonusNumber() = bonusNumber

    companion object {
        private fun generateLotto(): List<LottoNumber> =
            (1..45)
                .shuffled()
                .take(6)
                .sorted()
                .map { LottoNumber(it) }
    }
}
