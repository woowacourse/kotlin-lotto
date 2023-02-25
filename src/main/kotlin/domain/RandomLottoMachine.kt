package domain

class RandomLottoMachine : LottoMachine {
    override fun create(count: Int): List<Lotto> {
        val result = mutableListOf<Lotto>()
        repeat(count) { result.add(Lotto(LottoNumber.all().shuffled().take(6).toSet())) }
        return result
    }
}
