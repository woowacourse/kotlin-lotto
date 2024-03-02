package lotto.model

class AutoLottoMachine(private val count: Int) : LottoMachine {
    override fun make(): List<UserLottoTicket> {
        return List(count) { makeUserLottoTicket() }
    }

    private fun makeUserLottoTicket(): UserLottoTicket {
        return UserLottoTicket(makeLottoNumbers())
    }

    private fun makeLottoNumbers(): List<LottoNumber> {
        return makeRandomNumbers().map { LottoNumber.makeLottoNumber(it) }
    }

    private fun makeRandomNumbers(): List<Int> {
        return (LottoNumber.MIN_LOTTO_NUMBER..LottoNumber.MAX_LOTTO_NUMBER)
            .shuffled()
            .take(6)
            .sorted()
    }
}
