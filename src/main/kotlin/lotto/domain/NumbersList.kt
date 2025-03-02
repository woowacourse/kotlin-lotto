package lotto.domain

class NumbersList(private val numbersList: MutableList<List<Int>>) {
    fun removeFirst(): List<Int> {
        require(numbersList.isNotEmpty()) { BLANK_LIST_MESSAGE }
        return numbersList.removeFirst()
    }

    fun toLottos(): List<Lotto> {
        return numbersList.map { numbers ->
            Lotto(numbers.map { LottoNumber.of(it) })
        }
    }

    fun size(): Int {
        return numbersList.size
    }

    companion object {
        private const val BLANK_LIST_MESSAGE = "수동 로또 번호 리스트가 비어 있습니다."
    }
}
