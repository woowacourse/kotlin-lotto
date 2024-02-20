package model

class NumberMatchCounter (
    private val winningNumbers: LottoNumbers,
    private val userNumbers: LottoNumbers,
    ){
    fun countMatchNumber() :Int {
        return winningNumbers.lottoTicket.intersect(userNumbers.lottoTicket.toSet()).size
    }

}