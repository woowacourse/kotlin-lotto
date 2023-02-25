package domain

class ManualLottoMachine(private val lottoNumbers: List<Set<Int>>) : LottoMachine {
    override fun create(count: Int): List<Lotto> {
        return lottoNumbers.map { numbers ->
            Lotto(numbers.map { number -> LottoNumber.from(number) }.toSet())
        }
    }
}
