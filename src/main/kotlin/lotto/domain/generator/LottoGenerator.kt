package lotto.domain.generator

import lotto.domain.model.LottoTicket

interface LottoGenerator {
    fun generateLotto(): LottoTicket
}
