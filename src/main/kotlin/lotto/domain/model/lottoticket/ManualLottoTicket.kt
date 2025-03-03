package lotto.domain.model.lottoticket

import lotto.domain.valueobject.LottoNumber

class ManualLottoTicket(
    private val _lottoNumbers: Collection<LottoNumber>,
) : LottoTicket {
    init {
        require(lottoNumbers.size == _lottoNumbers.size) {
            ERROR_DUPLICATE_NUMBER.format(fundDuplicateLottoNumbers().map { it.value }.joinToString())
        }
        require(lottoNumbers.size == LottoTicket.LOTTO_TICKET_SIZE) {
            ERROR_LOTTO_NUMBER_SIZE.format(lottoNumbers.size)
        }
    }

    private fun fundDuplicateLottoNumbers(): Collection<LottoNumber> =
        _lottoNumbers
            .groupingBy { it }
            .eachCount()
            .filter { it.value > 1 }
            .keys

    override val lottoNumbers: Set<LottoNumber>
        get() = _lottoNumbers.toSet()

    companion object {
        private const val ERROR_DUPLICATE_NUMBER = "입력값에 중복된 번호(%s)가 존재합니다."
        private const val ERROR_LOTTO_NUMBER_SIZE = "로또번호는 %d개가 아닌 ${LottoTicket.LOTTO_TICKET_SIZE}개로 입력해야 합니다."
    }
}
