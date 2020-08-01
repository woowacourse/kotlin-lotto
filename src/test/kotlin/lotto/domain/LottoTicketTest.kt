package lotto.domain

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatIllegalStateException
import org.junit.jupiter.api.Test

internal class LottoTicketTest {
    @Test
    internal fun `로또 티켓에 중복된 숫자가 있는 경우`() {
        val lottoNumbers: MutableList<LottoNumber> = (1..5).map { LottoNumber.of(it) }.toMutableList()
        lottoNumbers.add(LottoNumber.of(5))

        assertThatIllegalStateException().isThrownBy { LottoTicket(lottoNumbers) }
    }

    @Test
    internal fun `로또 숫자 정렬`() {
        val lottoNumbers: MutableList<LottoNumber> = (2..6).map { LottoNumber.of(it) }.toMutableList()
        lottoNumbers.add(LottoNumber.of(1))
        val lottoTicket = LottoTicket(lottoNumbers)

        assertThat(lottoTicket.lottoNumbers[0].number).isEqualTo(1)
    }

    @Test
    internal fun `당첨 번호와 겹치는 번호 개수`() {
        val lottoNumbers: List<LottoNumber> = (1..6).map { LottoNumber.of(it) }
        val winningNumbers: List<LottoNumber> = (2..7).map { LottoNumber.of(it) }
        val lottoTicket = LottoTicket(lottoNumbers)
        val winningTicket = LottoTicket(winningNumbers)

        assertThat(lottoTicket.countOfSameNumbers(winningTicket)).isEqualTo(5);
    }
}
