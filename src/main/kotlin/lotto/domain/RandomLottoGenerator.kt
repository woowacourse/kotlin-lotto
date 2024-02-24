package lotto.domain

class RandomLottoGenerator : LottoGenerator {
    override fun manipulate(numbers: List<Int>): List<Int> {
        return numbers.shuffled()
    }
}
