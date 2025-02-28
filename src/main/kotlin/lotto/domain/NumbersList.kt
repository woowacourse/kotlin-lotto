package lotto.domain

class NumbersList(private val numbersList: List<List<Int>>) {
    fun toLottos(): List<Lotto> {
        return numbersList.map { numbers ->
            Lotto(numbers.map { LottoNumber.of(it) })
        }
    }
}
